/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

package com.maogu.rbac.action;

import java.util.Hashtable;
import java.util.List;

import javacommon.base.BaseStruts2Action;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.web.scope.Flash;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.maogu.rbac.model.Role;
import com.maogu.rbac.model.User;
import com.maogu.rbac.model.UserRole;
import com.maogu.rbac.service.RoleManager;
import com.maogu.rbac.service.UserManager;
import com.maogu.rbac.service.UserRoleManager;
import com.maogu.rbac.vo.query.UserQuery;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author liangmaogu email:liangmaogu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class UserAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/backend/User/query.jsp";
	protected static final String LIST_JSP= "/backend/User/list.jsp";
	protected static final String CREATE_JSP = "/backend/User/create.jsp";
	protected static final String EDIT_JSP = "/backend/User/edit.jsp";
	protected static final String SHOW_JSP = "/backend/User/show.jsp";
	protected static final String USER_ROLE_JSP = "/backend/User/user_role.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/backend/User/list.do";
	
	private UserManager userManager;
	private RoleManager roleManager;
	private UserRoleManager userRoleManager;
	
	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	private AuthenticationManager authenticationManager;
	
	private User user;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			user = new User();
		} else {
			user = (User)userManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setUserManager(UserManager manager) {
		this.userManager = manager;
	}	
	
	public void setRoleManager(RoleManager manager) {
		this.roleManager = manager;
	}
	
	public void setUserRoleManager(UserRoleManager manager) {
		this.userRoleManager = manager;
	}
	
	public Object getModel() {
		return user;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		UserQuery query = newQuery(UserQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = userManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		String encodePassword = new Md5PasswordEncoder().encodePassword(user.getPassword(), user.getUsername());
		user.setPassword(encodePassword);
		userManager.save(user);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		userManager.update(this.user);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			userManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	public String changeUserRole() {
		List<Role> roles = roleManager.findAll();
		List<UserRole> urs = userRoleManager.findAll();
		
		getRequest().setAttribute("roles", roles);
		getRequest().setAttribute("urs", urs);
		return USER_ROLE_JSP;
	}
	
	public String saveUserRole() {
		String type = getRequest().getParameter("type");
		String userId = getRequest().getParameter("userId");
		String roleId = getRequest().getParameter("roleId");
		
		if ("checked".equals(type)) {
			UserRole entity = new UserRole();
			entity.setUserId(Integer.parseInt(userId));
			entity.setRoleId(Integer.parseInt(roleId));
			userRoleManager.save(entity );
		} else {
			userRoleManager.deleteByUserIdAndRoleId(Integer.parseInt(userId), Integer.parseInt(roleId));
		}
		ajax_data = SUCCESS;
		
		return AJAX_RESULT;
	}
	
	/**用户注册*/
	public String register() {
		String username = getRequest().getParameter("username");
		String password = getRequest().getParameter("password");
		String repassword = getRequest().getParameter("repassword");
		
		if (StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(repassword) && password.equals(repassword)) {
			String encodePassword = new Md5PasswordEncoder().encodePassword(user.getPassword(), user.getUsername());
			user.setPassword(encodePassword);
			user.setUsername(username);
			user.setEnabled(1);
			userManager.save(user);
			
			try {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
				token.setDetails(new WebAuthenticationDetails(getRequest()));
				Authentication authentication = authenticationManager.authenticate(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				getRequest().getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
			} catch (Exception e) {
				e.printStackTrace();
				return "/register.jsp";
			}
			return "/backend/index.jsp";
		}
		
		return "/register.jsp";
	}
}

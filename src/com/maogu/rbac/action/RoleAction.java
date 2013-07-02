/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

package com.maogu.rbac.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.web.scope.Flash;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.maogu.rbac.model.*;
import com.maogu.rbac.dao.*;
import com.maogu.rbac.service.*;
import com.maogu.rbac.vo.query.*;

/**
 * @author liangmaogu email:liangmaogu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class RoleAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/backend/Role/query.jsp";
	protected static final String LIST_JSP= "/backend/Role/list.jsp";
	protected static final String CREATE_JSP = "/backend/Role/create.jsp";
	protected static final String EDIT_JSP = "/backend/Role/edit.jsp";
	protected static final String SHOW_JSP = "/backend/Role/show.jsp";
	protected static final String ROLE_AUTHORITY_JSP = "/backend/Role/role_authority.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/backend/Role/list.do";
	
	private RoleManager roleManager;
	private AuthorityManager authorityManager;
	private RoleAuthorityManager roleAuthorityManager;
	
	private Role role;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			role = new Role();
		} else {
			role = (Role)roleManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setRoleManager(RoleManager manager) {
		this.roleManager = manager;
	}	
	
	public void setAuthorityManager(AuthorityManager manager) {
		this.authorityManager = manager;
	}
	
	public void setRoleAuthorityManager(RoleAuthorityManager manager) {
		this.roleAuthorityManager = manager;
	}
	
	public Object getModel() {
		return role;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		RoleQuery query = newQuery(RoleQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = roleManager.findPage(query);
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
		roleManager.save(role);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		roleManager.update(this.role);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			roleManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/**修改功能操作权限*/
	public String changeRoleAuthority() {
		List<Role> roles = roleManager.findAll();
		List<Authority> authoritys = authorityManager.findAll();
		List<RoleAuthority> ras = roleAuthorityManager.findAll();
		
		getRequest().setAttribute("roles", roles);
		getRequest().setAttribute("authoritys", authoritys);
		getRequest().setAttribute("ras", ras);
		return ROLE_AUTHORITY_JSP;
	}
	
	/**保存修改功能操作权限*/
	public String saveRoleAuthority() {
		String type = getRequest().getParameter("type");
		String roleId = getRequest().getParameter("roleId");
		String authorityId = getRequest().getParameter("authorityId");
		
		if ("checked".equals(type)) {
			RoleAuthority entity = new RoleAuthority();
			entity.setRoleId(Integer.parseInt(roleId));
			entity.setAuthorityId(Integer.parseInt(authorityId));
			roleAuthorityManager.save(entity);
		} else {
			roleAuthorityManager.deleteByRoleIdAndAuthorityId(Integer.parseInt(roleId), Integer.parseInt(authorityId));
		}
		ajax_data = SUCCESS;
		
		return AJAX_RESULT;
	}
}

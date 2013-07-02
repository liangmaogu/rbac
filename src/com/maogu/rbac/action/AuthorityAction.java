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


public class AuthorityAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/backend/Authority/query.jsp";
	protected static final String LIST_JSP= "/backend/Authority/list.jsp";
	protected static final String CREATE_JSP = "/backend/Authority/create.jsp";
	protected static final String EDIT_JSP = "/backend/Authority/edit.jsp";
	protected static final String SHOW_JSP = "/backend/Authority/show.jsp";
	protected static final String RESOURCE_AUTHORITY_JSP = "/backend/Authority/resource_authority.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/backend/Authority/list.do";
	
	private AuthorityManager authorityManager;
	private ResourceManager resourceManager;
	private AuthorityResourceManager authorityResourceManager;
	
	private Authority authority;
	java.lang.Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			authority = new Authority();
		} else {
			authority = (Authority)authorityManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setAuthorityManager(AuthorityManager manager) {
		this.authorityManager = manager;
	}	
	
	public void setResourceManager(ResourceManager manager) {
		this.resourceManager = manager;
	}
	
	public void setAuthorityResourceManager(AuthorityResourceManager manager) {
		this.authorityResourceManager = manager;
	}
	
	public Object getModel() {
		return authority;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		AuthorityQuery query = newQuery(AuthorityQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = authorityManager.findPage(query);
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
		authorityManager.save(authority);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		authorityManager.update(this.authority);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("id"));
			authorityManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**修改功能操作权限*/
	public String changeResourceAuthority() {
		List<Resource> resources = resourceManager.findAll();
		List<Authority> authoritys = authorityManager.findAll();
		List<AuthorityResource> ars = authorityResourceManager.findAll();
		
		getRequest().setAttribute("resources", resources);
		getRequest().setAttribute("authoritys", authoritys);
		getRequest().setAttribute("ars", ars);
		return RESOURCE_AUTHORITY_JSP;
	}
	
	/**保存修改功能操作权限*/
	public String saveResourceAuthority() {
		String type = getRequest().getParameter("type");
		String resourceId = getRequest().getParameter("resourceId");
		String authorityId = getRequest().getParameter("authorityId");
		
		if ("checked".equals(type)) {
			AuthorityResource entity = new AuthorityResource();
			entity.setResourceId(Integer.parseInt(resourceId));
			entity.setAuthorityId(Integer.parseInt(authorityId));
			authorityResourceManager.save(entity);
		} else {
			authorityResourceManager.deleteByResourceIdAndAuthorityId(Integer.parseInt(resourceId), Integer.parseInt(authorityId));
		}
		ajax_data = SUCCESS;
		
		return AJAX_RESULT;
	}

}

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
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.web.scope.Flash;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.maogu.rbac.model.Resource;
import com.maogu.rbac.service.ResourceManager;
import com.maogu.rbac.vo.query.ResourceQuery;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author liangmaogu email:liangmaogu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class ResourceAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/backend/Resource/query.jsp";
	protected static final String LIST_JSP= "/backend/Resource/list.jsp";
	protected static final String CREATE_JSP = "/backend/Resource/create.jsp";
	protected static final String EDIT_JSP = "/backend/Resource/edit.jsp";
	protected static final String SHOW_JSP = "/backend/Resource/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/backend/Resource/list.do";
	
	private ResourceManager resourceManager;
	
	private Resource resource;
	Integer id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			resource = new Resource();
		} else {
			resource = (Resource)resourceManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setResourceManager(ResourceManager manager) {
		this.resourceManager = manager;
	}	
	
	public Object getModel() {
		return resource;
	}
	
	public void setId(Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		ResourceQuery query = newQuery(ResourceQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = resourceManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		getResourceByParent(0);
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		resourceManager.save(resource);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		getResourceByParent(0);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		resourceManager.update(this.resource);
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			Integer id = new Integer((String)params.get("id"));
			resourceManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**获得所有可用菜单*/
	public String getAllMenu() {
		String id = getRequest().getParameter("id");
		if (id != null) {
			ResourceQuery query = new ResourceQuery();
			query.setParent(Integer.parseInt(id));
			query.setEnabled(1);
			query.setType("menu");
			query.setPageSize(1000);
			
			Page<Resource> page = resourceManager.findPage(query);
			List<Resource> menus = page.getResult();
			int i = 0;
			StringBuilder json = new StringBuilder();
			json.append("[");
			for (Resource menu : menus) {
				if (i != 0) {
					json.append(",");
				}
				json.append("{");
					json.append("'metadata': {");
						json.append("'id': 'node_").append(menu.getId()).append("',");
						json.append("'url': '").append(menu.getUrl()).append("'");
					json.append("},");
					json.append("'data': '").append(menu.getName()).append("'");
				json.append("}");
				
				i++;
			}
			json.append("]");
			
			ajax_data = json.toString();
		}
		
		return AJAX_RESULT;
	}
	
	private void getResourceByParent(int parent) {
		ResourceQuery query = new ResourceQuery();
		query.setEnabled(1);
		query.setParent(parent);
		ajax_data = resourceManager.findPage(query );
	}

}

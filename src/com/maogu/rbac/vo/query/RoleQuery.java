/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

package com.maogu.rbac.vo.query;

import java.io.Serializable;

import javacommon.base.BaseQuery;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author liangmaogu email:liangmaogu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class RoleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 角色ID */
	private Integer id;
	/** 角色名称 */
	private String name;
	/** 是否可用 */
	private Integer enabled;
	/** 描述 */
	private String description;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public Integer getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(Integer value) {
		this.enabled = value;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


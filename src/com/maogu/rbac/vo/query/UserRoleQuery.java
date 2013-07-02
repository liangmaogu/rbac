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


public class UserRoleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private Integer id;
	/** 外键--用户ID */
	private Integer userId;
	/** 外键--角色ID */
	private Integer roleId;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public Integer getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(Integer value) {
		this.roleId = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


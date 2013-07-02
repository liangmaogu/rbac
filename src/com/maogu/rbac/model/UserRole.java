/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

package com.maogu.rbac.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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


@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "UserRole";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_ID = "外键--用户ID";
	public static final String ALIAS_ROLE_ID = "外键--角色ID";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * di       db_column: di 
     */ 	
	
	private Integer id;
    /**
     * 外键--用户ID       db_column: userId 
     */ 	
	@NotNull 
	private Integer userId;
    /**
     * 外键--角色ID       db_column: roleId 
     */ 	
	@NotNull 
	private Integer roleId;
	//columns END


	public UserRole(){
	}

	public UserRole(
		Integer di
	){
		this.id = di;
	}

	

	public void setId(Integer value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getId() {
		return this.id;
	}
	
	@Column(name = "userId", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	@Column(name = "roleId", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(Integer value) {
		this.roleId = value;
	}
	
	
	private Role role;
	public void setRole(Role role){
		this.role = role;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "roleId",nullable = false, insertable = false, updatable = false) 
	})
	public Role getRole() {
		return role;
	}
	
	private User user;
	public void setUser(User user){
		this.user = user;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "userId",nullable = false, insertable = false, updatable = false) 
	})
	public User getUser() {
		return user;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("RoleId",getRoleId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserRole == false) return false;
		if(this == obj) return true;
		UserRole other = (UserRole)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


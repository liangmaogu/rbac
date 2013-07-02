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
@Table(name = "role")
public class Role extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Role";
	public static final String ALIAS_ID = "角色ID";
	public static final String ALIAS_NAME = "角色名称";
	public static final String ALIAS_ENABLED = "是否可用";
	public static final String ALIAS_DESC = "描述";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 角色ID       db_column: id 
     */ 	
	
	private Integer id;
    /**
     * 角色名称       db_column: name 
     */ 	
	@NotBlank @Length(max=64)
	private String name;
    /**
     * 是否可用       db_column: enabled 
     */ 	
	@NotNull 
	private Integer enabled;
    /**
     * 描述       db_column: desc 
     */ 	
	@Length(max=128)
	private String description;
	//columns END


	public Role(){
	}

	public Role(
		Integer id
	){
		this.id = id;
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
	
	@Column(name = "name", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	@Column(name = "enabled", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(Integer value) {
		this.enabled = value;
	}
	
	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	
	private Set userRoles = new HashSet(0);
	public void setUserRoles(Set<UserRole> userRole){
		this.userRoles = userRole;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	
	private Set roleAuthoritys = new HashSet(0);
	public void setRoleAuthoritys(Set<RoleAuthority> roleAuthority){
		this.roleAuthoritys = roleAuthority;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<RoleAuthority> getRoleAuthoritys() {
		return roleAuthoritys;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Enabled",getEnabled())
			.append("Desc",getDescription())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Role == false) return false;
		if(this == obj) return true;
		Role other = (Role)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


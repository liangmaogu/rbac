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
@Table(name = "user")
public class User extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "User";
	public static final String ALIAS_ID = "用户ID";
	public static final String ALIAS_NAME = "用户名";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_ENABLED = "是否可用";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 用户ID       db_column: id 
     */ 	
	
	private Integer id;
    /**
     * 用户名       db_column: name 
     */ 	
	@NotBlank @Length(max=32)
	private String username;
    /**
     * 密码       db_column: password 
     */ 	
	@NotBlank @Length(max=64)
	private String password;
    /**
     * 是否可用       db_column: enabled 
     */ 	
	@NotNull 
	private Integer enabled;
	//columns END


	public User(){
	}

	public User(
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
	
	@Column(name = "username", unique = true, nullable = false, insertable = true, updatable = true, length = 256)
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	@Column(name = "password", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	@Column(name = "enabled", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(Integer value) {
		this.enabled = value;
	}
	
	
	private Set userRoles = new HashSet(0);
	public void setUserRoles(Set<UserRole> userRole){
		this.userRoles = userRole;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Username",getUsername())
			.append("Password",getPassword())
			.append("Enabled",getEnabled())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof User == false) return false;
		if(this == obj) return true;
		User other = (User)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


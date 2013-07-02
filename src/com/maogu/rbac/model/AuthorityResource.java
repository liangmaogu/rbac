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
@Table(name = "authority_resource")
public class AuthorityResource extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AuthorityResource";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_AUTHORITY_ID = "外键--权限ID";
	public static final String ALIAS_RESOURCE_ID = "外键--资源ID";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: id 
     */ 	
	
	private Integer id;
    /**
     * 外键--权限ID       db_column: authorityId 
     */ 	
	@NotNull 
	private Integer authorityId;
    /**
     * 外键--资源ID       db_column: resourceId 
     */ 	
	@NotNull 
	private Integer resourceId;
	//columns END


	public AuthorityResource(){
	}

	public AuthorityResource(
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
	
	@Column(name = "authorityId", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getAuthorityId() {
		return this.authorityId;
	}
	
	public void setAuthorityId(Integer value) {
		this.authorityId = value;
	}
	
	@Column(name = "resourceId", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getResourceId() {
		return this.resourceId;
	}
	
	public void setResourceId(Integer value) {
		this.resourceId = value;
	}
	
	
	private Authority authority;
	public void setAuthority(Authority authority){
		this.authority = authority;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "authorityId",nullable = false, insertable = false, updatable = false) 
	})
	public Authority getAuthority() {
		return authority;
	}
	
	private Resource resource;
	public void setResource(Resource resource){
		this.resource = resource;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "resourceId",nullable = false, insertable = false, updatable = false) 
	})
	public Resource getResource() {
		return resource;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AuthorityId",getAuthorityId())
			.append("ResourceId",getResourceId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AuthorityResource == false) return false;
		if(this == obj) return true;
		AuthorityResource other = (AuthorityResource)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


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
@Table(name = "resource")
public class Resource extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Resource";
	public static final String ALIAS_ID = "资源ID";
	public static final String ALIAS_NAME = "资源名称";
	public static final String ALIAS_TYPE = "资源类型";
	public static final String ALIAS_URL = "资源路径";
	public static final String ALIAS_ENABLED = "是否可用";
	public static final String ALIAS_DESC = "描述";
	public static final String ALIAS_PARENT = "父资源ID";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 资源ID       db_column: id 
     */ 	
	
	private Integer id;
    /**
     * 资源名称       db_column: name 
     */ 	
	@NotBlank @Length(max=128)
	private String name;
    /**
     * 资源类型       db_column: type 
     */ 	
	@Length(max=64)
	private String type;
    /**
     * 资源路径       db_column: url 
     */ 	
	@Length(max=256)
	private String url;
    /**
     * 是否可用       db_column: enabled 
     */ 	
	
	private Integer enabled;
    /**
     * 描述       db_column: desc 
     */ 	
	@Length(max=128)
	private String description;
    /**
     * 父资源ID       db_column: parent 
     */ 	
	
	private Integer parent;
	//columns END


	public Resource(){
	}

	public Resource(
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
	
	@Column(name = "name", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getType() {
		return this.type;
	}
	
	public void setType(String value) {
		this.type = value;
	}
	
	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String value) {
		this.url = value;
	}
	
	@Column(name = "enabled", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
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
	
	@Column(name = "parent", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getParent() {
		return this.parent;
	}
	
	public void setParent(Integer value) {
		this.parent = value;
	}
	
	
	private Set authorityResources = new HashSet(0);
	public void setAuthorityResources(Set<AuthorityResource> authorityResource){
		this.authorityResources = authorityResource;
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "resource")
	public Set<AuthorityResource> getAuthorityResources() {
		return authorityResources;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Type",getType())
			.append("Url",getUrl())
			.append("Enabled",getEnabled())
			.append("Desc",getDescription())
			.append("Parent",getParent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Resource == false) return false;
		if(this == obj) return true;
		Resource other = (Resource)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


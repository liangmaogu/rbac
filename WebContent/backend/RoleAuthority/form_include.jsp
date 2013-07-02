<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@com.maogu.rbac.model.RoleAuthority@ALIAS_ROLE_ID}" key="roleId" value="%{model.roleId}" cssClass="required validate-integer max-value-2147483647" required="true" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.RoleAuthority@ALIAS_AUTHORITY_ID}" key="authorityId" value="%{model.authorityId}" cssClass="required validate-integer max-value-2147483647" required="true" />
	

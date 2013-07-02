<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@com.maogu.rbac.model.AuthorityResource@ALIAS_AUTHORITY_ID}" key="authorityId" value="%{model.authorityId}" cssClass="required validate-integer max-value-2147483647" required="true" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.AuthorityResource@ALIAS_RESOURCE_ID}" key="resourceId" value="%{model.resourceId}" cssClass="required validate-integer max-value-2147483647" required="true" />
	

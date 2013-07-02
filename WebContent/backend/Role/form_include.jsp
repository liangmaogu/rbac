<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@com.maogu.rbac.model.Role@ALIAS_NAME}" key="name" value="%{model.name}" cssClass="required " required="true" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.Role@ALIAS_ENABLED}" key="enabled" value="%{model.enabled}" cssClass="required validate-integer max-value-2147483647" required="true" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.Role@ALIAS_DESC}" key="description" value="%{model.description}" cssClass="" required="false" />
	

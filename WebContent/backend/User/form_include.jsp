<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@com.maogu.rbac.model.User@ALIAS_NAME}" key="username" value="%{model.username}" cssClass="required " required="true" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.User@ALIAS_PASSWORD}" key="password" value="%{model.password}" cssClass="required " required="true" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.User@ALIAS_ENABLED}" key="enabled" value="%{model.enabled}" cssClass="required validate-integer max-value-2147483647" required="true" />
	

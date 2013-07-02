<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="di" name="di" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@com.maogu.rbac.model.UserRole@ALIAS_USER_ID}" key="userId" value="%{model.userId}" cssClass="required validate-integer max-value-2147483647" required="true" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.UserRole@ALIAS_ROLE_ID}" key="roleId" value="%{model.roleId}" cssClass="required validate-integer max-value-2147483647" required="true" />
	

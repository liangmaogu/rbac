<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@com.maogu.rbac.model.Authority@ALIAS_NAME}" key="name" value="%{model.name}" cssClass="required " required="true" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.Authority@ALIAS_ENABLED}" key="enabled" value="%{model.enabled}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.Authority@ALIAS_DESC}" key="description" value="%{model.description}" cssClass="" required="false" />
	

<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@com.maogu.rbac.model.Resource@ALIAS_NAME}" key="name" value="%{model.name}" cssClass="required " required="true" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.Resource@ALIAS_TYPE}" key="type" value="%{model.type}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.Resource@ALIAS_URL}" key="url" value="%{model.url}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.Resource@ALIAS_ENABLED}" key="enabled" value="%{model.enabled}" cssClass="validate-integer max-value-2147483647" required="false" />
	
	
	<s:textfield label="%{@com.maogu.rbac.model.Resource@ALIAS_DESC}" key="description" value="%{model.description}" cssClass="" required="false" />


	<s:select list="ajax_data.result"
		label="%{@com.maogu.rbac.model.Resource@ALIAS_PARENT}" listKey="id" listValue="name" 
		name="parent"></s:select>
	
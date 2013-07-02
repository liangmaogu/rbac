<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=UserRole.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/backend/UserRole/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/backend/UserRole/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="di" id="di" value="%{model.di}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=UserRole.ALIAS_USER_ID%></td>	
				<td><s:property value="%{model.userId}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=UserRole.ALIAS_ROLE_ID%></td>	
				<td><s:property value="%{model.roleId}" /></td>
			</tr>
		</table>
	</s:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>
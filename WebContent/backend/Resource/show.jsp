<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Resource.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/backend/Resource/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/backend/Resource/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="id" id="id" value="%{model.id}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=Resource.ALIAS_NAME%></td>	
				<td><s:property value="%{model.name}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Resource.ALIAS_TYPE%></td>	
				<td><s:property value="%{model.type}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Resource.ALIAS_URL%></td>	
				<td><s:property value="%{model.url}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Resource.ALIAS_ENABLED%></td>	
				<td><s:property value="%{model.enabled}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Resource.ALIAS_DESC%></td>	
				<td><s:property value="%{model.desc}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Resource.ALIAS_PARENT%></td>	
				<td><s:property value="%{model.parent}" /></td>
			</tr>
		</table>
	</s:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>
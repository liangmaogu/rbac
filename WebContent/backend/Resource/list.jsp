<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Resource.TABLE_ALIAS%> 维护</title>
	
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
</rapid:override>

<rapid:override name="content">
	<form id="queryForm" name="queryForm" action="<c:url value="/backend/Resource/list.do"/>" method="get" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=Resource.ALIAS_NAME%></td>		
					<td>
						<input value="${query.name}" id="name" name="name" maxlength="128"  class=""/>
					</td>
					<td class="tdLabel"><%=Resource.ALIAS_TYPE%></td>		
					<td>
						<input value="${query.type}" id="type" name="type" maxlength="64"  class=""/>
					</td>
					<td class="tdLabel"><%=Resource.ALIAS_URL%></td>		
					<td>
						<input value="${query.url}" id="url" name="url" maxlength="256"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel"><%=Resource.ALIAS_ENABLED%></td>		
					<td>
						<input value="${query.enabled}" id="enabled" name="enabled" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
					<td class="tdLabel"><%=Resource.ALIAS_DESC%></td>		
					<td>
						<input value="${query.description}" id="description" name="description" maxlength="128"  class=""/>
					</td>
					<td class="tdLabel"><%=Resource.ALIAS_PARENT%></td>		
					<td>
						<input value="${query.parent}" id="parent" name="parent" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/backend/Resource/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/backend/Resource/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/backend/Resource/delete.do','items',document.forms.queryForm)"/>
		<div>
	</div>
	
	<div class="gridTable">
	
		<table width="100%"  border="0" cellspacing="0" class="gridBody">
		  <thead>
			  
			  <tr>
				<th style="width:1px;"> </th>
				<th style="width:1px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="name" ><%=Resource.ALIAS_NAME%></th>
				<th sortColumn="type" ><%=Resource.ALIAS_TYPE%></th>
				<th sortColumn="url" ><%=Resource.ALIAS_URL%></th>
				<th sortColumn="enabled" ><%=Resource.ALIAS_ENABLED%></th>
				<th sortColumn="description" ><%=Resource.ALIAS_DESC%></th>
				<th sortColumn="parent" ><%=Resource.ALIAS_PARENT%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="id=${item.id}&"></td>
				
				<td><c:out value='${item.name}'/>&nbsp;</td>
				<td><c:out value='${item.type}'/>&nbsp;</td>
				<td><c:out value='${item.url}'/>&nbsp;</td>
				<td><c:out value='${item.enabled}'/>&nbsp;</td>
				<td><c:out value='${item.description}'/>&nbsp;</td>
				<td><c:out value='${item.parent}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/backend/Resource/show.do?id=${item.id}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/backend/Resource/edit.do?id=${item.id}&">修改</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	</div>
	</form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>


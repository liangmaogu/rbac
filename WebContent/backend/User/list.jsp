<%@page import="com.maogu.rbac.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=User.TABLE_ALIAS%> 维护</title>
	
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
	<form id="queryForm" name="queryForm" action="<c:url value="/backend/User/list.do"/>" method="get" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel"><%=User.ALIAS_NAME%></td>		
					<td>
						<input value="${query.username}" id="username" name="username" maxlength="32"  class=""/>
					</td>
					<td class="tdLabel"><%=User.ALIAS_PASSWORD%></td>		
					<td>
						<input value="${query.password}" id="password" name="password" maxlength="64"  class=""/>
					</td>
					<td class="tdLabel"><%=User.ALIAS_ENABLED%></td>		
					<td>
						<input value="${query.enabled}" id="enabled" name="enabled" maxlength="10"  class="validate-integer max-value-2147483647"/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/backend/User/list.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/backend/User/create.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="删除" onclick="batchDelete('${ctx}/backend/User/delete.do','items',document.forms.queryForm)"/>
		<div>
	</div>
	
	<div class="gridTable">
	
		<table width="100%"  border="0" cellspacing="0" class="gridBody">
		  <thead>
			  
			  <tr>
				<th style="width:1px;"> </th>
				<th style="width:1px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"></th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="username" ><%=User.ALIAS_NAME%></th>
				<th sortColumn="password" ><%=User.ALIAS_PASSWORD%></th>
				<th sortColumn="enabled" ><%=User.ALIAS_ENABLED%></th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="id=${item.id}&"></td>
				
				<td><c:out value='${item.username}'/>&nbsp;</td>
				<td><c:out value='${item.password}'/>&nbsp;</td>
				<td><c:out value='${item.enabled}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/backend/User/show.do?id=${item.id}&">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/backend/User/edit.do?id=${item.id}&">修改</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/backend/User/changeUserRole.do?id=${item.id}&">关联角色</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
	</div>
	</form>
</rapid:override>

<%-- jsp模板继承 --%>
<%@ include file="base.jsp" %>


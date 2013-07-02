<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>修改资源权限</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="#" method="post">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/backend/Authority/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<table border="1">
			<tr>
				<td width="15%">资源名称</td>
				<td width="15%">资源URL</td>
				<c:forEach items="${authoritys }" var="authority">
					<td width="15%">${authority.name }</td>
				</c:forEach>
			</tr>
			<c:forEach items="${resources }" var="resource">
				<tr>
					<td>${resource.name }</td>
					<td>${resource.url }</td>
					<c:forEach items="${authoritys }" var="authority">
						<td>
							<input type="checkbox" onclick="changeResourcePrivilege(this, ${resource.id}, ${authority.id});" ${rbac:checked(ars, resource.id, authority.id, 'resource') }>${authority.name }
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</s:form>
	
	<script type="text/javascript">
		function changeResourcePrivilege(obj, resourceId, authorityId) {
			var type = obj.checked ? "checked" : "unchecked";
			$.post("backend/Authority/saveResourceAuthority.do", {
				type: type,
				resourceId: resourceId,
				authorityId: authorityId
			}, function(data) {
				
			});
		}
	</script>
</rapid:override>



<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>
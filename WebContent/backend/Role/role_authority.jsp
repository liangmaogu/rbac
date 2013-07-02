<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>修改角色权限</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="#" method="post">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/backend/Role/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<table border="1">
			<tr>
				<td width="15%">角色名称</td>
				<c:forEach items="${authoritys }" var="authority">
					<td width="15%">${authority.name }</td>
				</c:forEach>
			</tr>
			<c:forEach items="${roles }" var="role">
				<tr>
					<td>${role.name }</td>
					<c:forEach items="${authoritys }" var="authority">
						<td>
							<input type="checkbox" 
							onclick="changeRolePrivilege(this, ${role.id}, ${authority.id});" ${rbac:checked(ras, role.id, authority.id, 'role') }>${authority.name }
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</s:form>
	
	<script type="text/javascript">
		function changeRolePrivilege(obj, roleId, authorityId) {
			var type = obj.checked ? "checked" : "unchecked";
			$.post("backend/Role/saveRoleAuthority.do", {
				type: type,
				roleId: roleId,
				authorityId: authorityId
			}, function(data) {
				
			});
		}
	</script>
</rapid:override>



<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>
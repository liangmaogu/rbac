<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>修改用户角色</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="#" method="post">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/backend/User/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<table border="1">
			<tr>
				<td width="15%">用户名</td>
				<c:forEach items="${roles }" var="role">
					<td width="15%"></td>
				</c:forEach>
			</tr>
			<tr>
				<td>${model.username }</td>
				<c:forEach items="${roles }" var="role">
					<td>
						<input type="checkbox" 
						onclick="changeUserRole(this, ${model.id}, ${role.id});" ${rbac:checked(urs, model.id, role.id, 'user') }>${role.name }
					</td>
				</c:forEach>
			</tr>
		</table>
	</s:form>
	
	<script type="text/javascript">
		function changeUserRole(obj, userId, roleId) {
			var type = obj.checked ? "checked" : "unchecked";
			$.post("backend/User/saveUserRole.do", {
				type: type,
				userId: userId,
				roleId: roleId
			}, function(data) {
				
			});
		}
	</script>
</rapid:override>



<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>
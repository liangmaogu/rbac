<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
	<%@ include file="/commons/meta.jsp" %>
	<title>用户注册</title>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>
	<div class="container">
		<div class="row-fluid">
			<div class="span4"></div>
			<div class="span4">
				<div>
					<c:if test="${not empty param.login_error }">
						<font color="red">
							Your register attempt was not successful, try again.<br/><br/>
        					Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
        				</font>
					</c:if>
				</div>
				
				<form name="f" action="${ctx }/backend/User/register.do" method="post" class="well form-vertical">
					<fieldset>
						<legend>用户注册</legend>
						<div class="control-group">
							<label class="control-label" for="username">用户名</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="username" name="username">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="password">密&nbsp;&nbsp;码</label>
							<div class="controls">
								<input type="password" class="input-xlarge" id="password" name="password">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="repassword">确认密码</label>
							<div class="controls">
								<input type="password" class="input-xlarge" id="repassword" name="repassword">
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-large btn-primary">注册</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="span4"></div>
		</div>
	</div>
</body>
</html>	
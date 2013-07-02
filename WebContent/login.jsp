<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
	<%@ include file="/commons/meta.jsp" %>
	<title>用户登录</title>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>
	<div class="container">
		<div class="row-fluid">
			<div class="span4"></div>
			<div class="span4">
				<form name="f" action="<c:url value='j_spring_security_check'/>" method="post" class="well form-vertical">
					<fieldset>
						<legend>用户登录</legend>
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
							<div class="controls">
								<input id="remember" type="checkbox" class="" id="remember-me" name="_spring_security_remember_me">记住密码
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-large btn-primary">登录</button>
								<a href="${ctx }/register.jsp">立即注册</a>
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
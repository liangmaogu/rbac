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
	<style type="text/css">
		a{
			font-weight:bold;
			font-family:"宋体";
			font-size:18px;
		}

    </style>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>
	<div class="container">
		<div class="row-fluid">
			<div class="span4"></div>
			<div class="span4">
				<div class="well">
					<div>
						<h2>您无权访问本页面，请联系系统管理员为您分配相应访问的权限后进行！</h2>
					</div>
				</div>
			</div>
			<div class="span4"></div>
		</div>
	</div>
</body>
</html>	
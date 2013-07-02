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
					您长时间未操作系统，为确保您的资料及数据信息安全，
					系统自动超时退出，请重新<a href="${ctx }">登录</a>系统！
				</div>
			</div>
			<div class="span4"></div>
		</div>
	</div>
</body>
</html>	
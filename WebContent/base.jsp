<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
	<%@ include file="/commons/meta.jsp" %>
	<base href="<%=basePath%>">
	<rapid:block name="head"/>
</head>
<body>
	<%@ include file="/commons/messages.jsp" %>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				
			</div>
			<div class="row-fluid">
				<div class="span2">
					<div id="menu-tree"></div>
				</div>
				<div class="span10">
					<rapid:block name="content"/>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="./scripts/jstree/jquery.jstree.js"></script>
	<script type="text/javascript" src="./scripts/menu.js"></script>
</body>
</html>	
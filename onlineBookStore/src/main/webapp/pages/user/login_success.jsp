<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>铛铛铛铛会员注册页面</title>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/DDlogoNEW.gif" >
				<%request.setCharacterEncoding("utf-8");%>
				<%@include file="/pages/common/login_sucess_menu.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>
				欢迎回来
				<span class="um_span">${sessionScope.user.username}</span>光临书城
				<a href="index.jsp">转到主页</a>
			</h1>
	
		</div>

		<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>
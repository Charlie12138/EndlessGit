<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>入口</title>
</head>
<body>
<center>
	<header>
		<div class="container" id="head">
			<ul class="ulheader">
				<h1><li><a
						href="${pageContext.request.contextPath}/toLogin">会员登录</a></li>
					<li><a
							href="${pageContext.request.contextPath}/toRegister">免费注册</a></li></h1>
			</ul>
		</div>
	</header>
	<h1><ul>
		<li><a
				href="/ListAllCuisine?pageNum=1&jump=1">主页</a></li>
	</ul></h1>
</center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员页面</title>
<link rel="stylesheet" type="text/css"
	href="/liqinglin_onlineOrderMealsSystem/css.admin/adminIndex.css">
</head>
<body>
	<c:if test="${not empty user}">
		<h2>管理员页面</h2>
	<nav class="nav">
		<ul>
			<li class="current"><a
				href="/liqinglin_onlineOrderMealsSystem/admin/adminIndex.jsp">首页</a></li>
			<li><a
			href="/liqinglin_onlineOrderMealsSystem/ExamineRegisterServlet">管理申请注册信息</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ExamineOpenStoreServlet">查看用户开店申请信息</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListAllStoresServlet?pageNum=1">管理店铺</a></li>
			<li ><a
				href="/liqinglin_onlineOrderMealsSystem/ExamineAddCuisinesServlet">审核店铺上架的食品</a></li>
			<li><a
				href="">退出</a></li>
		</ul>
	</nav>
		<hr width="100%">
		<p>欢迎管理员，在这里您可以对店主提交的审核进行处理以及管理店铺</p>
	</c:if>
	<c:if test="${empty user }">
		<h1>
			必须登录后才能访问该页面，<a
				href="/liqinglin_onlineOrderMealsSystem/user/userLogin.jsp">去登录</a>
		</h1>
	</c:if>
</body>
</html>
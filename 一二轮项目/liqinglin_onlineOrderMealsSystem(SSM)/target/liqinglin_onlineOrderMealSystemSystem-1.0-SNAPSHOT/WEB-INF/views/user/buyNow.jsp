<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="css/css.user/user.css">
<title>下单</title>
</head>
<body>
	<c:if test="${empty user }">
		<center>
			<h1>您还未登录，不能访问此页面</h1>
			<h3>
				可前往<a href="/toLogin">登录</a>或者<a
					href="/toRegister">注册</a>
			</h3>
		</center>
	</c:if>

	<c:if test="${not empty user }">
		<h2>您好：${user.username }用户</h2>
		<nav class="nav">
		<ul>
			<li><a
				href="/ListAllCuisinesServlet?pageNum=1&jump=2">首页</a></li>
			<li><a href="/ListShopCartInfoServlet?userId=${user.id}&pageNum=1">我的购物车</a></li>
			<li><a
				href="/userOrderIndex">我的订单</a></li>
			<li><a href="/modifyUserInfo">完善个人信息</a></li>
			<li><a href="/ModifyPasswordServlet">修改密码</a></li>
			<li><a
				href="/openStore">我要开店</a></li>
			<li class="current"><a>购买商品</a></li>
			<li><a href="/UserDropOutServlet">退出</a></li>
		</ul>
		</nav>
		
		<div id="main">
			<div>
				<center>
					<h2 style="color: red;">${message }</h2>
				</center>
				<center>
					<p>请在下方填写您想购买的商品数量</p>
					<c:remove var="message" scope="session" />
					<form action="/BuyCuisineServlet" method="post" class="myform" onsubmit="return check()">
						<input type="text" value="${param.cuisineName }" id="cuisineName" name="cuisineName" class="cuisineName" readOnly="true"><br>

						<input type="text" name=number id="number" class="number" placeholder="*数量" value="${param.number }">

						<input type="hidden" value="${param.storeId }" name="storeId"><br>
						<input type = "hidden" value="${param.price }" name = "price">
						<input type = "hidden" value="${param.cuisineId }" name= "cuisineId">
						<input type = "hidden" value="${user.id }" name= "userId">

						<br>
						<br>
						<button type="submit" class="button" id="submit">提交</button>
					</form>
				</center>
			</div>
		</div>		
	</c:if>
</body>
</html>
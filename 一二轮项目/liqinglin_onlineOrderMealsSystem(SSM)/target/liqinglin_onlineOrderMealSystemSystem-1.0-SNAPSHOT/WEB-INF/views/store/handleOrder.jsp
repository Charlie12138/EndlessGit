<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="css/css.store/index.css">
<title>处理订单</title>
</head>
<body>
	<c:if test="${empty user}">
		您还没有登录，请<a href = "/toLogin">登录</a>
	</c:if>
	<c:if test="${not empty user}">	
		<c:if test="${store.status == status0 }">
			<center>
				<h1>
					您的信息还在审核中，请耐心等待 <a href="">退出</a>
				</h1>
			</center>
		</c:if>
	
		<c:if test="${store.status == status2 }">
			<center>
				<h1>
					很对不起，您的开店请求已被拒绝！ <a href="">退出</a>
				</h1>
			</center>
		</c:if>
	</c:if>
	<c:if test="${store.status == status1 && not empty user}">
		<h2>您好：${store.shopkeeperName }店家</h2>
		
		<nav class="nav">
			<ul>
				<li class="current"><a
				href="/handlerOrder">处理订单首页</a></li>
			<li><a
				href="/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1&orderStatus=${orderStatus0}">未发货订单</a></li>
			<li><a
				href="/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1&orderStatus=${orderStatus1}">已发货订单</a></li>
			<li><a
				href="/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1&orderStatus=${orderStatus2}">已收货订单</a></li>
			<li><a
				href="/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1&orderStatus=${orderStatus3}">已取消订单</a></li>
			<li><a
				href="/ListCuisine?storeId=${store.storeId}&pageNum=1&jump=1">返回主页面</a></li>
			</ul>
		</nav>
		<h2></h2>
		<p>您可以对您店铺的订单进行相应的操作！</p>
	</c:if>	
</body>
</html>
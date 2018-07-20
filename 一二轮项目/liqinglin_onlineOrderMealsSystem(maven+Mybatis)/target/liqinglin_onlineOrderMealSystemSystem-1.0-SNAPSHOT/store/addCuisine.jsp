<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/liqinglin_onlineOrderMealsSystem/css.store/store.css">
<title>上架美食</title>
<script type="text/javascript" src="/liqinglin_onlineOrderMealsSystem/store.js/addCuisine.js"></script>

</head>
<body>

	<c:if test="${empty user}">
		<center>
			<h3>
				您还没有登录，请<a href = "/liqinglin_onlineOrderMealsSystem/user/userLogin.jsp">登录</a>
				或者<a href = "/liqinglin_onlineOrderMealsSystem/store/openStore.jsp">开店</a>
			</h3>
		</center>
	</c:if>
	
	<c:if test="${not empty store }">
		<h2>您好：${store.shopkeeperName }店家</h2>
		<nav class="nav">
			<ul>
				<li><a
					href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=1">首页</a></li>
				<li class="current"><a
					href="/liqinglin_onlineOrderMealsSystem/store/addCuisine.jsp">上架美食</a></li>
				<li><a
					href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=2">下架美食</a></li>
				<li><a
					href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=3">修改菜肴信息</a></li>
				<li><a
					href="/liqinglin_onlineOrderMealsSystem/store/modifyStoreInfo.jsp">完善/修改店铺信息</a></li>
				<li><a
					href="/liqinglin_onlineOrderMealsSystem/store/handleOrder.jsp">处理订单</a></li>
				<li><a
					href="/liqinglin_onlineOrderMealsSystem/UserDropOutServlet">退出</a></li>
			</ul>
		</nav>
		
		<center>
			<h2>请填写商品的相关信息，带*号为必填项</h2>
		</center>
		<div id="main">
			<div>
				<center>
					<h3 style="color: red;">${message }</h3>
					
					<form action="/liqinglin_onlineOrderMealsSystem/AddCuisinesServlet" method="post" class="myform" enctype="multipart/form-data" onsubmit="return check()">

						<input type = "hidden" name = "storeId" value = "${store.storeId}">

						<input type="text" name="cuisineName" class="cuisineName" id="cuisineName" maxlength="30" placeholder="*美食名称"
						value="${param.cuisineName }" /><br>

						<textarea rows="5" cols="50" name="description" class="description" id="description" placeholder="美食描述"
						value="${param.description }" /></textarea><br>
						
						<input type="text" name="price" class="price" id="price" maxlength="30" placeholder="*价格：非负整数或小数" 
						value="${param.price }" /><br>
																
						<font size="5">图片：</font><input type="file" name="file" class="file" id="file" /><br>
						
						<p>支持.jpg，.gif，.png，.jpeg格式的图片</p>
						
						<button type="submit" class="button" id="submit">添加</button>

					</form>
				</center>
			</div>
		</div>
	</c:if>
</body>
</html>
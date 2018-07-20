<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="/liqinglin_onlineOrderMealsSystem/css.store/store.css">
<title>修改菜肴信息</title>
</head>
<body>
	<c:if test="${empty user}">
		您还没有登录，请<a href="/liqinglin_onlineOrderMealsSystem/user/userLogin.jsp">登录</a>
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
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=1">首页</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/store/addCuisine.jsp">上架美食</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=2">下架美食</a></li>
			<li class="current"><a
				href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=3">修改菜肴信息</a></li>
			<li><a href="">查看被拒绝上架菜肴</a></li>
			<li><a href="/liqinglin_onlineOrderMealsSystem/store/modifyStoreInfo.jsp">完善/修改店铺信息</a></li>
			<li><a href="/liqinglin_onlineOrderMealsSystem/store/handleOrder.jsp">处理订单</a></li>
			<li><a href="">修改密码</a></li>
			<li><a href="">退出</a></li>
		</ul>
		</nav>

		<center>
			<h3 style="color: red;">${message }</h3>
			<form action="/liqinglin_onlineOrderMealsSystem/SubmitCuisineInfoServlet" method="post" class="myform"
				enctype="multipart/form-data" onsubmit="return check()">

				<font size="5">菜肴名称：</font>
				
				<input type="text" name="cuisineName" class="cuisineName" id="cuisineName" maxlength="30" placeholder="*菜肴名称" value="${cuisine.cuisineName }" /><br> 
				
				<font size="5">价格：</font>
				
				<input type="text" name="price" class="price" id="price" maxlength="30" placeholder="*价格：非负整数或小数" value="${cuisine.price }" /><br> 
				
				<font size="5">商品描述：</font>
				
				<textarea rows="5" cols="50" name="description" class="description" id="description" placeholder="美食描述">
					${cuisine.description}
				</textarea><br>
				
				<br> <font size="5">图片：</font><img src="${cuisine.picturePath}" width="100" height="100"> <br> 
				
				<font size="5">修改图片：</font>
				
				<input type="file" name="file" class="file" id="file">
				
				<p>支持.jpg，.gif，.png，.jpeg格式的图片</p>
				
				<input type = "hidden" class = "id" id = "id" name = "id" value = "${cuisine.id}" >
				
				<button type="submit" class="button" id="submit">提交</button>

			</form>
		</center>
	</c:if>
</body>
</html>
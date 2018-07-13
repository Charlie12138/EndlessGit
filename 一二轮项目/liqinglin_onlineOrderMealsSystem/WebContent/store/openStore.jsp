<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/liqinglin_onlineOrderMealsSystem/css.store/openStore.css">
<script type="text/javascript" src="/liqinglin_onlineOrderMealsSystem/store.js/openStore.js"></script>

<title>申请开店</title>
</head>
<body>
	<c:if test="${not empty user}">

		<div class="content">
			<center>
				<h2 style="color: red;">${message}</h2>
			</center>
			
			<form action="/liqinglin_onlineOrderMealsSystem/UserOpenStoreServlet"
				method="post" class="myform" onsubmit="return check()">
				<h1>开店需填写以下信息（带*为必填）</h1>
				
				<input type = "hidden" name = "username" value = "${user.username}"></h1><!-- ${user.username} 等价于  -->
	
				<input type="text" name="storeName" class="storeName" id="storeName" maxlength="32" placeholder="*请为您的店铺起名" value="${param.storeName }"><span id="checkStoreName"></span>
		
				<input type="text" name="phone" class="phone" id="phone" maxlength="30" placeholder="*店铺电话(手机)" value="${param.phone }"/><span id="checkPhone"></span>
							
				<input type="text" name="address" class="address" id="address" maxlength="30" placeholder="*店铺地址" value="${param.address}"/><span id="checkAddress"></span>			
										
				<input type="text" name="shopkeeperName" class="shopkeeperName" id="shopkeeperName" maxlength="20" placeholder="*您的真实姓名" value="${param.shopkeeperName }"/><span id="checkShopkeeperName"></span>
											
				<textarea rows="5" cols="40" name="storeDescription" class="storeDescription" id="storeDescription" placeholder="描述您的店铺" value="${param.storeDescription }"></textarea><span id="checkStoreDescription"></span>
						
				<br>
				<button type="submit" class="button" id="submit">注册</button>
				<button type="reset" class="reset" id="reset">重置</button>
			</form>
		</div>
	
	</c:if>
	<center>
		<c:if test="${empty user }">
			<h1>
				必须登录后才能访问该页面，<a href="/liqinglin_onlineOrderMealsSystem/user/userLogin.jsp">去登录</a>
			</h1>
		</c:if>
	</center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="css/css.store/store.css">
<title>修改菜肴信息</title>
</head>
<body>
	<c:if test="${empty user}">
		您还没有登录，请<a href="/toLogin">登录</a>
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
				href="/ListCuisine?storeId=${store.storeId}&pageNum=1&jump=1">首页</a></li>
			<li><a
				href="/addCuisine">上架美食</a></li>
			<li><a
				href="/ListCuisine?storeId=${store.storeId}&pageNum=1&jump=2">下架美食</a></li>
			<li><a
				href="/ListCuisine?storeId=${store.storeId}&pageNum=1&jump=3">修改菜肴信息</a></li>
			<li class="current"><a
				href="/modifyStoreInfo">完善/修改店铺信息</a></li>
			<li><a
				href="/handlerOrder">处理订单</a></li>
			<li><a href="/UserDropOutServlet">退出</a></li>
		</ul>
		</nav>
	</c:if>

	<c:if test="${not empty user}">

		<div class="content">
			<center>
				<h2 style="color: red;">${message}</h2>
			</center>
			<center>
				<form
					action="/SubmitStoreInfoServlet"
					method="post" class="myform" onsubmit="return check()">
					<h1>开店需填写以下信息（带*为必填）</h1>

					<input type="hidden" name="storeId" value="${store.storeId}">
					</h1>

					<font size="5">*店铺名称：</font> <input type="text" name="storeName"
						class="storeName" id="storeName" maxlength="32"
						placeholder="*请为您的店铺起名" value="${store.storeName}"><span
						id="checkStoreName"></span><br> <font size="5">*店铺电话(手机)：</font>

					<input type="text" name="phone" class="phone" id="phone"
						maxlength="30" placeholder="*店铺电话(手机)" value="${store.phone }" /><span
						id="checkPhone"></span><br> <font size="5">*地址：</font> <input
						type="text" name="address" class="address" id="address"
						maxlength="30" placeholder="*店铺地址" value="${store.address}" /><span
						id="checkAddress"></span><br> <font size="5">*真实姓名：</font> <input
						type="text" name="shopkeeperName" class="shopkeeperName"
						id="shopkeeperName" maxlength="20" placeholder="*您的真实姓名"
						value="${store.shopkeeperName }" /><span id="checkShopkeeperName"></span><br>
					<br> <font size="5">描述您的店铺：</font>

					<textarea rows="5" cols="40" name="storeDescription"
						class="storeDescription" id="storeDescription"
						placeholder="描述您的店铺">${store.storeDescription}</textarea>
					<span id="checkStoreDescription"></span><br> <br>
					<button type="submit" class="button" id="submit">提交</button>
					<button type="reset" class="button" id="reset">重置</button>
				</form>
			</center>
		</div>

	</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="css/css.user/user.css">
<title>填写购物车订单</title>
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
		<center>
			<h1 style="color: red;">${msg}</h1><!-- ${message} 等价于 getAttribute("message") -->
		</center>
	<c:if test="${not empty user }">
		<h2>您好：${user.username }用户</h2>
		<nav class="nav">
		<ul>
			<li class="current"><a
				href="/ListAllCuisinesServlet?pageNum=1&jump=2">首页</a></li>
			<li><a
				href="/ListShopCartInfoServlet?userId=${user.id}&pageNum=1">我的购物车</a></li>
			<li class="current"><a>填写订单</a></li>
			<li><a
				href="/userOrderIndex">我的订单</a></li>
			<li><a href="/modifyUserInfo">完善个人信息</a></li>
			<li><a href="/modifyPw">修改密码</a></li>
			<li><a
				href="/openStore">我要开店</a></li>
			<li class="current"><a>填写订单</a></li>
			<li><a href="/UserDropOutServlet">退出</a></li>

			<c:if test="${not empty stores }">

				<div class="nav">

					<ul>

						<li><a href="#">我的店铺</a>

							<ul>

								<c:forEach items="${stores}" var="store">

									<li><a
										href="/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=1">${store.storeName}</a>
								</c:forEach>

							</ul></li>

					</ul>

				</div>

			</c:if>

		</ul>
		</nav>
		<center>
			<h1 style="color: red;">${msg}</h1>
		</center>
		<center>
			<h2>填写订单</h2>
			<p>您此次共有${totalOrderAmount }条购物订单，请您校对完无误后填写相关信息</p>
			<c:forEach items="${totalOrders }" var="totalOrder">
				<p>订单号：${totalOrder.orderNum}，下单时间：${totalOrder.createTime}</p>
				<table bgcolor="#F5F5DC" align="center" border="1" cellspacing="0"
					width="60%">
					<tr>
						<th align="center">商品名称</th>
						<th align="center">商品价格</th>
						<th align="center">商品数量</th>
						<th align="center">总价格</th>
					</tr>
				</table>

				<c:forEach items="${totalOrder.singleOrders }" var="singleOrder">

					<table bgcolor="#F5F5DC" align="center" border="1" cellspacing="0"
						width="60%">
						<tr>
							<td align="center">${singleOrder.cuisine.cuisineName }</td>
							<td align="center">￥${singleOrder.singlePrice }</td>
							<td align="center">${singleOrder.number }</td>
							<td align="center">￥${singleOrder.totalPrice }</td>
						</tr>
					</table>

				</c:forEach>

			</c:forEach>
			<h2>请在下面填写相关信息，带*为必填</h2>
			<div id="main">
				<div>
					<form
						action="/SubmitCartOrderInfoServlet"
						method="post" class="myform" onsubmit="return check()">

						<input type="text" name="receiver" id="receiver" class="receiver"
							placeholder="*收货人"><br> <input type="text"
							name="address" id="address" class="address" placeholder="*收货地址"><br>

						<input type="text" name="phone" id="phone" class="phone"
							placeholder="*联系电话"><br>

						<textarea rows="5" cols="50" name="message" id="message"
							class="message" placeholder="留言、备注"></textarea>
						<br>
						<button type="submit" class="button" id="submit">确认支付</button>
					</form>
				</div>
			</div>
		</center>
	</c:if>
</body>
</html>
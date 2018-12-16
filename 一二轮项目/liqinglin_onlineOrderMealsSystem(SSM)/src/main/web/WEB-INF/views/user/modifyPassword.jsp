<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="css/css.user/user.css">
<script type="text/javascript"
	src="js/user.js/register.js"></script>
<title>个人主页</title>
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
		<h1 style="color: red;">${message}</h1>
		<!-- ${message} 等价于 getAttribute("message") -->
	</center>

	<c:if test="${not empty user }">
		<h2>您好：${user.username }用户</h2>
		<nav class="nav">
		<ul>
			<li class="current"><a
				href="/ListAllCuisinesServlet?pageNum=1&jump=2">首页</a></li>
			<li><a
				href="/ListShopCartInfoServlet?userId=${user.id}&pageNum=1">我的购物车</a></li>
			<li><a
				href="/userOrderIndex">我的订单</a></li>
			<li><a
				href="/modifyUserInfo">完善个人信息</a></li>
			<li><a href="/modifyPw">修改密码</a></li>
			<li><a
				href="/openStore">我要开店</a></li>
			<li><a
				href="/UserDropOutServlet">退出</a></li>

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
			<form
				action="/ModifyPasswordServlet"
				method="post" class="myform" onsubmit="return check()">

				新密码：<input type="text" name="password" class="password"
					id="password" maxlength="16" placeholder="密码:6到16位的数字或字母"
					value="${user.password }" /> <span id="checkPassword"></span> 
					
				<input
					type="password" name="repassword" class="repassword"
					id="repassword" maxlength="16" placeholder="请确认密码"
					value="${param.repassword }" /> <span id="checkRepassword"></span><br>
				<button type="submit" class="button" id="submit">提交</button>
				<button type="reset" class="button" id="reset">重置</button>
			</form>
		</center>
</c:if>
</body>
</html>
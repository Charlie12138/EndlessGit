<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="/liqinglin_onlineOrderMealsSystem/css.user/user.css">
<script type="text/javascript">
	function check() {
		var phone = document.getElementById("phone");
		var email = document.getElementById("email");
		var realname = document.getElementById("realname");
		if (phone.value == "" || realname.value == "" || eamil.value == "") {
			alert("必填信息不能为空");
			return false;
		}
		return true;
	}
	//实时监听用户的输入是否正确
	function inprove(attr) {
		var flag = document.getElementById(attr);

		if (attr == "email"){
			var emailRegex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if (!(flag.value.match(emailRegex))) {
				document.getElementById("checkEmail").innerText = "邮箱格式不对";
			} else {
				document.getElementById("checkEmail").innerText = "√";
			}
		}else if (attr == "phone") {
			var mobileRegex = /^1[34578]\d{9}$/;
			if (!(flag.value.match(mobileRegex))) {
				document.getElementById("checkPhone").innerText = "手机格式不对";
			} else {
				document.getElementById("checkPhone").innerText = "√";
			}
		}
	}

	window.onload = function() {
		var phone = document.getElementById("phone");
		var eamil = document.getElementById("email");
		phone.onkeyup = function() {
			inprove("phone");
		}
		eamil.onkeyup = function() {
			inprove("email");
		}
	}
</script>
<title>修改/完善个人信息</title>
</head>
<body>
	<c:if test="${empty user }">
		<center>
			<h1>您还未登录，不能访问此页面</h1>
			<h3>
				可前往<a href="/liqinglin_onlineOrderMealsSystem/user/userLogin.jsp">登录</a>或者<a
					href="/liqinglin_onlineOrderMealsSystem/user/userRegister.jsp">注册</a>
			</h3>
		</center>
	</c:if>

	<c:if test="${not empty user }">
		<h2>您好：${user.username }用户</h2>
		<nav class="nav">
		<ul>
			<li class="current"><a
				href="/liqinglin_onlineOrderMealsSystem/ListAllCuisinesServlet?pageNum=1&jump=2">首页</a></li>
			<li><a href="">我的购物车</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/user/userOrderIndex.jsp">我的订单</a></li>
			<li><a href="/liqinglin_onlineOrderMealsSystem/user/modifyUserInfo.jsp">完善个人信息</a></li>
			<li><a href="/liqinglin_onlineOrderMealsSystem/user/modifyPassword.jsp">修改密码</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/store/openStore.jsp">我要开店</a></li>
			<li><a href="/liqinglin_onlineOrderMealsSystem/UserDropOutServlet">退出</a></li>

			<c:if test="${not empty stores }">

				<div class="nav">

					<ul>

						<li><a href="#">我的店铺</a>

							<ul>

								<c:forEach items="${stores}" var="store">

									<li><a
										href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=1">${store.storeName}</a>
								</c:forEach>

							</ul></li>

					</ul>

				</div>

			</c:if>

		</ul>
		</nav>
		
		
		<div class="content">
			<center>
				<h2 style="color: red;">${message}</h2>
			</center>
		<center>	
			<form action="/liqinglin_onlineOrderMealsSystem/SubmitUserInfoServlet" method="post" class="myform" onsubmit="return check()">
				<h1>个人信息（带*为必填）</h1>
				
				<input type = "hidden" name = "userId" value = "${user.id}"></h1>
	
				<font size="5">*真实姓名：</font>
	
				<input type="text" name="realname" class="realname" id="realname" maxlength="32" placeholder="*您的真实姓名" value="${user.realname}"><br>
		
				<font size="5">*电话(手机)：</font>
		
				<input type="text" name="phone" class="phone" id="phone" maxlength="30" placeholder="*电话(手机)" value="${user.phone }"/><span id="checkPhone"></span><br>
														
											
				<font size="5">*邮箱：</font>
				
				<input type="text" name="email" class="email" id="email" placeholder="邮箱" value ="${user.email}"/><span id="checkEmail"></span><br>
						
				<br>
				<button type="submit" class="button" id="submit">提交</button>
				<button type="reset" class="button" id="reset">重置</button>
			</form>
		</center>
		</div>
		
	</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/liqinglin_onlineOrderMealsSystem/css.user/register.css">
<title>用户注册</title>  
<script type="text/javascript" src="/liqinglin_onlineOrderMealsSystem/user.js/register.js"></script>
</head>
<body>
	<div class = "content">
		<center>
			<h2 style="color: red;">${message}</h2>
		</center>
		<form action = "/liqinglin_onlineOrderMealsSystem/UserRegisterServlet" method = "post" class = "myform" onsubmit = "return check();">
			
			<h1>用户注册</h1>
			
			<input type="text" name="username" class="username" id="username" maxlength="30" placeholder="用户名：手机号码" value="${param.username }"/>
			<span id="checkUsername"></span>
				
			<input type="password" name="password" class="password" id="password" maxlength="16" placeholder="密码:6到16位的数字或字母" value="${param.password }"/>
			<span id="checkPassword"></span>
				
			<input type="password" name="repassword" class="repassword" id="repassword" maxlength="16" placeholder="请确认密码" value="${param.repassword }" />
			<span id="checkRepassword"></span><br>

			<button type="submit" class="button" id="submit">注册</button>
	
			<input type="reset" class="reset" id="reset" />
		</form>
	</div>
</body>
</html>
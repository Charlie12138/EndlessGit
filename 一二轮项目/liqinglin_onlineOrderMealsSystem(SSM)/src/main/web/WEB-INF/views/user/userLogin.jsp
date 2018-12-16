<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/css.user/login.css">
<title>登录</title>
<script type="text/javascript" src="js/user.js/login.js" charset="GBK"></script>

</head>
<body>
	<div class="content">
		<center>
			<h1 style="color: red;">${message}</h1><!-- ${message} 等价于 getAttribute("message") -->
		</center>
		
		<a href="/toRegister">还未注册？    </a>
		
		<form  action="/userLogin" class = "myform" method = "post"  onsubmit = "return check()" >
		
			<h1>登录</h1>
			
			<input type="text" name="username" class="username" id="username" maxlength="30" placeholder="用户名：手机号码" value="${param.username }" />  <!-- param.xxx获得本页面中xxx的值  -->
			<span id="checkUsername"></span>
					
			<input type="password" name="password" class="password" id="password" maxlength="16" placeholder="密码:6到16位的数字或字母" value="${param.password }"/>
			<span id="checkPassword"></span>
			
			<br>
			
			用户<input type = "radio" class = "radio" id = "radio1" name = "role" value = "1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			店家<input type = "radio" class = "radio" id = "radio2" name = "role" value = "2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			管理员<input type = "radio" class = "radio" id = "radio3" name = "role" value = "3"><br>
			
			<button type="submit" class="button" id="submit">登录</button><br>
			
		</form>
	</div>
</body>
</html>
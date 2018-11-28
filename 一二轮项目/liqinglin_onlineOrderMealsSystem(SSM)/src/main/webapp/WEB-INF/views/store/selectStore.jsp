<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="css/css.user/user.css">
<title>选择店铺</title>
</head>
<body>
	<c:if test="${empty user }">
			<center>
				<h1>您还未登录，不能访问此页面</h1>
				<h3>
					可前往<a
						href="/toLogin">登录</a>或者<a
						href="/toRegister">注册</a>
				</h3>
			</center>
	</c:if>
	<center>
	<c:if test="${not empty stores }">	
			
				<div class="nav" >
					
						<h1><ul>
						    						
    						<li><a href="#">我的店铺</a>   	
    							
       							<ul>
       							
	       							<c:forEach items="${stores}" var="store">
	       								
		           			 			<li><a href="/ListCuisine?storeId=${store.storeId}&pageNum=1&jump=1">${store.storeName}</a>
		                   			
		                   			</c:forEach>
	                   			
	                   			</ul>
	                   			
           					</li>     
           					     				
        				</ul></h1>
        				
				</div>
				
	</c:if> 
	</center>
</body>
</html>
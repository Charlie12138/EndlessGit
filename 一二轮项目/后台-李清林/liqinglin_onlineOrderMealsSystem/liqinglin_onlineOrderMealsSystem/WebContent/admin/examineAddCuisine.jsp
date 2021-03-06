<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核食品</title>
<link rel="stylesheet" type="text/css"
	href="/liqinglin_onlineOrderMealsSystem/css.admin/adminIndex.css">
</head>
<body>
	<c:if test="${not empty user}">
		<h2>管理员页面</h2>
		<nav class="nav">
			<ul>
				<li><a
					href="/liqinglin_onlineOrderMealsSystem/admin/adminIndex.jsp">首页</a></li>
				<li><a
					href="/liqinglin_onlineOrderMealsSystem/ExamineRegisterServlet">管理注册申请信息</a></li>
				<li ><a
					href="/liqinglin_onlineOrderMealsSystem/ExamineOpenStoreServlet">查看用户开店申请信息</a></li>
				<li><a
					href="/liqinglin_onlineOrderMealsSystem/ListAllStoresServlet?pageNum=1">管理店铺</a></li>
				<li class="current"><a
					href="/liqinglin_onlineOrderMealsSystem/ExamineAddCuisinesServlet">审核店铺上架的食品</a></li>
				<li><a
					href="">退出</a></li>
			</ul>
		</nav>
		<hr width="100%">
		<c:if test="${empty cuisines }">
			<h3>没有任何上架申请的消息</h3>
		</c:if>
		
		<center>
		
			<c:if test="${not empty cuisines }">
				
				<table id="newspaper-a">
					
					<thead>
					
						<tr>
							<th align="center">店铺</th>
							<th align="center">菜名</th>
							<th align="center">价格</th>
							<th align="center">描述</th>
							<th align="center">图片</th>
							<th align="center">申请时间</th>
							<th align="center">操作</th>
						</tr>
						
					</thead>
	
					<c:forEach items="${cuisines}" var="cuisine">
					
						<tbody>
						
							<tr>
								<td align="center"><font size="2">${cuisine.storeName}</font></td>
								<td align="center"><font size="2">${cuisine.cuisineName}</font></td>
								<td align="center"><font size="2">${cuisine.price}</font></td>
								<td align="center"><font size="2">${cuisine.description}</font></td>
								<td align="center"><img src="${cuisine.picturePath}" width="200" height="120" /></td>
								<td align="center"><font size="2">${cuisine.createTime}</font></td>					
								<td align="center"><font size="2">
									
									<form action = "/liqinglin_onlineOrderMealsSystem/AgreeAddCuisinesServlet" method = "post">
										<input type = "hidden" name = "cuisineId" value = "${cuisine.id}">
										<input type = "hidden" name = "storeName" value = "${store.storeName}">
										<input type="submit" name="submit" value="同意">
									</form>	
									
									<form action="/liqinglin_onlineOrderMealsSystem/RejectAddCuisinesServlet" method="post">
											<input type = "hidden" name = "cuisineId" value = "${cuisine.id}">
											<input type = "hidden" name = "storeName" value = "${store.storeName}">
											<input type="submit" name="submit" value="拒绝">
									</form>
									
								</font></td>
								
							</tr>
							
						</tbody>
						
					</c:forEach>
	
				</table>
			
			</c:if>
		</center>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核开店</title>
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
			<li  class="current"><a
				href="/liqinglin_onlineOrderMealsSystem/ExamineOpenStoreServlet">查看用户开店申请信息</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListAllStoresServlet?pageNum=1">管理店铺</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ExamineAddCuisinesServlet">审核店铺上架的食品</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/UserDropOutServlet">退出</a></li>
		</ul>
	</nav>
	<hr width="100%">

	<c:if test="${empty stores }">
			<h3>没有任何申请开店的消息</h3>
	</c:if>
	
	<center>
	
			<c:if test="${not empty stores }">
			
				<table id="newspaper-a">
				
					<thead>
					
						<tr>
							<th align="center">店铺名</th>
							<th align="center">店主姓名</th>
							<th align="center">地址</th>
							<th align="center">店铺电话</th>
							<th align="center">店铺描述</th>
							<th align="center">申请时间</th>
							<th align="center">操作</th>
						</tr>
						
					</thead>
					
					<c:forEach items="${stores}" var="store">
					
						<tbody>
						
							<tr>
								<td align="center"><font size="2">${store.storeName}</font></td>
								<td align="center"><font size="2">${store.shopkeeperName}</font></td>
								<td align="center"><font size="2">${store.address}</font></td>
								<td align="center"><font size="2">${store.phone}</font></td>
								<td align="center"><font size="2">${store.storeDescription}</font></td>
								<td align="center"><font size="2">${store.createTime}</font></td>					
								<td align="center"><font size="2">
									
									<form action = "/liqinglin_onlineOrderMealsSystem/AgreeOpenStoreServlet" method = "post">
										<input type = "hidden" name = "storeName" value = "${store.storeName}">
										<input type="submit" name="submit" value="同意">
									</form>	
									
									<form action="/liqinglin_onlineOrderMealsSystem/RejectOpenStoreServlet" method="post">
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
	
	<c:if test="${empty user }">
		<h1>
			必须登录后才能访问该页面，<a
				href="/liqinglin_onlineOrderMealsSystem/user/userLogin.jsp">去登录</a>
		</h1>
	</c:if>
</body>
</html>
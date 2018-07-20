<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="/liqinglin_onlineOrderMealsSystem/css.store/index.css">
<title>我的购物车</title>
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
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListAllCuisinesServlet?pageNum=1&jump=2">首页</a></li>
			<li class="current"><a href="/liqinglin_onlineOrderMealsSystem/ListShopCartInfoServlet?userId=${user.id}&pageNum=1">我的购物车</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/user/userOrderIndex.jsp">我的订单</a></li>
			<li><a href="/liqinglin_onlineOrderMealsSystem/user/modifyUserInfo.jsp">完善个人信息</a></li>
			<li><a href="/liqinglin_onlineOrderMealsSystem/user/modifyPassword.jsp">修改密码</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/store/openStore.jsp">我要开店</a></li>
			<li><a href="/liqinglin_onlineOrderMealsSystem/UserDropOutServlet">退出</a></li>

		</ul>
		</nav>

		<c:if test="${empty cartInfos }">
			<center>
				<p>购物车为空</p>
			</center>
		</c:if>

		<c:if test="${not empty cartInfos }">
			<center>
				<p>您的购物车有以下商品</p>
				<table id="newspaper-a">
					<thead>
						<tr>
							<th align="center">美食名称</th>
							<th align="center">美食图片</th>
							<th align="center">美食描述</th>
							<th align="center">美食单价</th>
							<th align="center">美食数量</th>
							<th align="center">美食总价</th>
						</tr>
					</thead>
					<c:forEach items="${cartInfos}" var="cartInfo">
						<tbody>
							<tr>
								<td align="center"><font size="2">${cartInfo.cuisine.cuisineName }</font></td>
								<td align="center"><img src="${cartInfo.cuisine.picturePath}" width="100" height="100"></td>
								<td align="center"><font size="2">${cartInfo.cuisine.description}</font></td>								
								<td align="center">￥${cartInfo.cuisine.price}<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;		
								<td align="center"><font size="2">${cartInfo.number}</font></td>
								<td align="center"><font size="2">￥${cartInfo.totalPrice}</font></td>
							</tr>
						</tbody>
					</c:forEach>
				
				<h3>
					<a
						href="/liqinglin_onlineOrderMealsSystem/ClearCartServlet">清空购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="/liqinglin_onlineOrderMealsSystem/PayCartOrderServlet">下单</a>
				</h3>
				
				<c:if test="${totalPage == 1 }">
				<tbody>
					<tr>
						<td colspan="9" align="right">共${totalRecord }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${pageNum }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPage }页</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<c:if test="${totalPage > 1 }">
				<form action="/liqinglin_onlineOrderMealsSystem/ListShopCartInfoServlet"
					method="get" onsubmit="return check2()">

					<tbody>

						<tr>

							<td colspan="9" align="right">

								共${totalRecord}条数据&nbsp;&nbsp;&nbsp;&nbsp;第${pageNum}页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;&nbsp;&nbsp;

								<c:if test="${pageNum == 1}">
									<a
										href="/liqinglin_onlineOrderMealsSystem/ListShopCartInfoServlet?userId=${user.id}&pageNum=${pageNum + 1}&jump=2">下一页</a>

								</c:if> <c:if test="${pageNum == totalPage }">
									<a
										href="/liqinglin_onlineOrderMealsSystem/ListShopCartInfoServlet?userId=${user.id}&pageNum=${pageNum - 1}&jump=2">上一页</a>

								</c:if> <c:if test="${pageNum > 1 && pageNum < totalPage }">
									<a
										href="/liqinglin_onlineOrderMealsSystem/ListShopCartInfoServlet?userId=${user.id}&pageNum=${pageNum - 1}&jump=2">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
												<a
										href="/liqinglin_onlineOrderMealsSystem/ListShopCartInfoServlet?userId=${user.id}&pageNum=${pageNum + 1}&jump=2">下一页</a>
								</c:if> 
								<input type="hidden" id="userId" name="userId" value="${user.id}"> 
								<input type="hidden" id="jump" name="jump" value="2"> 
								<input type="text" id="pageNum" size="2" name="pageNum" value="${param.pageNum}"> 
								<input type="submit" name="go" value="跳转">

							</td>

						</tr>

					</tbody>

				</form>
			</c:if>
			</center>
		</c:if>



	</c:if>




</body>
</html>
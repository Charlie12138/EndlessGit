<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="/liqinglin_onlineOrderMealsSystem/css.store/index.css">
<title>订单详情</title>
</head>
<body>
	<c:if test="${empty user }">
		<center>
			<h1>您还未登录，不能访问此页面</h1>
			<h3>
				可前往<a
					href="/liqinglin_onlineOrderMealsSystem/user/userLogin.jsp">登录</a>或者<a
					href="/liqinglin_onlineOrderMealsSystem/user/userRegister.jsp">注册</a>
			</h3>
		</center>
	</c:if>
	<c:if test="${not empty user }">
		<h2>您好：${user.username }用户</h2>
		<nav class="nav">
		<ul>
			<li><a href="/liqinglin_onlineOrderMealsSystem/user/userOrderIndex.jsp">处理订单首页</a></li>
			<c:if test="${orderStatus == orderStatus0 }">
				<li class="current"><a href="/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?userId=${user.id}&pageNum=1&jump=2">待发货</a></li>
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListSendedOrderServlet?userId=${user.id}&pageNum=1&jump=2">待收货</a></li>
				<li><a href="">已收货订单</a></li>
				<li><a href="">已取消订单</a></li>
			</c:if>
			<c:if test="${orderStatus == orderStatus1 }">
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?userId=${user.id}&pageNum=1&jump=2">待发货</a></li>
				<li class="current"><a href="/liqinglin_onlineOrderMealsSystem/ListSendedOrderServlet?userId=${user.id}&pageNum=1&jump=2">待收货</a></li>
				<li><a href="">已收货订单</a></li>
				<li><a href="">已取消订单</a></li>
			</c:if>
			<c:if test="${orderStatus == orderStatus2 }">
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?userId=${user.id}&pageNum=1&jump=2">待发货</a></li>
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListSendedOrderServlet?userId=${user.id}&pageNum=1&jump=2">待收货</a></li>
				<li class="current"><a href="">已收货订单</a></li>
				<li><a href="">已取消订单</a></li>
			</c:if>
			<c:if test="${orderStatus == orderStatus3 }">
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?userId=${user.id}&pageNum=1&jump=2">待发货</a></li>
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListSendedOrderServlet?userId=${user.id}&pageNum=1&jump=2">待收货</a></li>
				<li><a href="">已收货订单</a></li>
				<li class="current"><a href="">已取消订单</a></li>
			</c:if>
			<li><a href="/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?userId=${user.id}&pageNum=1&jump=2">返回主页面</a></li>
		</ul>
		</nav>
		<center>
			<h2>订单详细信息</h2>
			<table id="newspaper-a">
				<thead>
					<tr>
						<th align="center">商品名称</th>
						<th align="center">商品价格</th>
						<th align="center">商品数量</th>
					</tr>
				</thead>
				<c:forEach items="${singleOrders}" var="singleOrder">
					<tbody>
						<tr>
							<td align="center"><font size="2">${singleOrder.cuisineName }</font></td>
							<td align="center"><font size="2">￥${singleOrder.singlePrice }</font></td>
							<td align="center"><font size="2">${singleOrder.number }</font></td>
						</tr>
					</tbody>
				</c:forEach>
				<c:if test="${totalPage == 1 }">
					<tbody>
						<tr>
							<td colspan="9" align="right">共${totalRecord }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${pageNum }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPage }页</td>
						</tr>
					</tbody>
				</c:if>

				<c:if test="${totalPage > 1 }">
					<form
						action="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet"
						method="get" onsubmit="return check2()">

						<tbody>

							<tr>

								<td colspan="9" align="right">

									共${totalRecord}条数据&nbsp;&nbsp;&nbsp;&nbsp;第${pageNum}页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;&nbsp;&nbsp;

									<c:if test="${pageNum == 1}">
										<a
											href="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet?orderId=${totalOrder.id}&pageNum=${pageNum + 1}&orderStatus=${totalOrder.status}&jump=2">下一页</a>

									</c:if> <c:if test="${pageNum == totalPage }">
										<a
											href="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet?orderId=${totalOrder.id}&pageNum=${pageNum-1}&orderStatus=${totalOrder.status}&jump=2">上一页</a>

									</c:if> <c:if test="${pageNum > 1 && pageNum < totalPage }">
										<a
											href="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet?orderId=${totalOrder.id}&pageNum=${pageNum - 1}&orderStatus=${totalOrder.status}&jump=2">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
												<a
											href="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet?orderId=${totalOrder.id}&pageNum=${pageNum + 1}&orderStatus=${totalOrder.status}&jump=2">下一页</a>
									</c:if> 
									<input type="hidden" id="orderId" name="orderId" value="${totalOrder.id}"> 
									<input type="hidden" id="jump" name="jump" value="2"> 
									<input type="text" id="pageNum" size="2" name="pageNum" value="${param.pageNum}">
									<input type="hidden" name ="orderStatus" value="${totalOrder.status}">
									<input type="submit" name="go" value="跳转">
								</td>

							</tr>

						</tbody>

					</form>
				</c:if>
			</table>
		</center>
	</c:if>
</body>
</html>
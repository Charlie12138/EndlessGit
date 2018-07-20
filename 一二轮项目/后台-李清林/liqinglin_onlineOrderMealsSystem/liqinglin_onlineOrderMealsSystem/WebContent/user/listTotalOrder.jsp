<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="/liqinglin_onlineOrderMealsSystem/css.store/index.css">
<title>订单</title>
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
				href="/liqinglin_onlineOrderMealsSystem/user/userOrderIndex.jsp">处理订单首页</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?userId=${user.id}&pageNum=1&jump=2&orderStatus=${orderStatus0}">待发货</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?userId=${user.id}&pageNum=1&jump=2&orderStatus=${orderStatus1}">待收货</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?userId=${user.id}&pageNum=1&jump=2&orderStatus=${orderStatus2}">已收货订单</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?userId=${user.id}&pageNum=1&jump=2&orderStatus=${orderStatus3}">已取消订单</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListAllCuisinesServlet?pageNum=1&jump=2">返回主页面</a></li>
		</ul>

		</nav>
		<h2></h2>

		<c:if test="${empty totalOrders }">
			<center>
				<p>没有相应的记录，可能原因为：</p>
				<p>1、搜索页数没有在页数范围之内</p>
				<p>2、暂时没有未发货的订单</p>
			</center>
		</c:if>

		<c:if test="${not empty totalOrders }">
			<center>
				<table id="newspaper-a">
					<thead>
						<tr>
							<th align="center">订单号</th>
							<th align="center">店铺名</th>
							<th align="center">收货人</th>
							<th align="center">收货地址</th>
							<th align="center">收货人电话</th>
							<th align="center">订单总价格</th>
							<th align="center">买家留言</th>
							<th align="center">下单时间</th>
							<th align="center">操作</th>
						</tr>
					</thead>
					<c:forEach items="${totalOrders}" var="totalOrder">
						<tbody>
							<tr>
								<td align="center"><font size="2">${totalOrder.orderNum }</font></td>
								<td align="center"><font size="2">${totalOrder.storeName}</font></td>
								<td align="center"><font size="2">${totalOrder.receiver }</font></td>
								<td align="center"><font size="2">${totalOrder.address }</font></td>
								<td align="center"><font size="2">${totalOrder.phone }</font></td>
								<td align="center"><font size="2">￥${totalOrder.totalPrice }</font></td>
								<td align="center"><font size="2">${totalOrder.message }</font></td>
								<td align="center"><font size="2">${totalOrder.createTime }</font></td>
								<td align="center"><font size="2"> 
								
										<c:if test="${totalOrder.status == orderStatus0}">
											<form action="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet" method="post">
												<input type="hidden" name="orderId" value="${totalOrder.id }"> 
												<input type="hidden" name="pageNum" value="1"> 
												<input type="hidden" name="jump" value="2"> 
												<input type="hidden" name="orderStatus" value="0"> 
												<input type="submit" value="查看订单详细信息">
											</form>

											<form
												action="/liqinglin_onlineOrderMealsSystem/CancelOrderServlet"
												method="post">
												<input type="hidden" name="orderId" value="${totalOrder.id }"> 
												<input type="hidden" name="jump" value="2"> 
												<input type="hidden" name ="orderStatus" value="${totalOrder.status}">
												<input type="hidden" name="orderStatus" value="0"> 
												<input type="hidden" name="id" value="${user.id }"> 
												<input type="submit" value="取消订单">
											</form>
										</c:if> 
										
										<c:if test="${totalOrder.status == orderStatus1}">
											<form action="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet" method="post">
												<input type="hidden" name="orderId" value="${totalOrder.id }"> 
												<input type="hidden" name="pageNum" value="1"> 
												<input type="hidden" name="jump" value="2"> 
												<input type="hidden" name="orderStatus" value="1"> 
												<input type="submit" value="查看订单详细信息">
											</form>

											<form action="/liqinglin_onlineOrderMealsSystem/ConfirmReceiveServlet" method="post">
												<input type="hidden" name="orderId" value="${totalOrder.id }"> 
												<input type="hidden" name="pageNum" value="1"> 
												<input type="hidden" name="orderStatus" value="1"> 
												<input type="submit" value="确认收货">
											</form>


											<form action="/liqinglin_onlineOrderMealsSystem/CancelOrderServlet" method="post">
												<input type="hidden" name="orderId" value="${totalOrder.id }"> 
												<input type="hidden" name="jump" value="2"> 
												<input type="hidden" name ="orderStatus" value="${totalOrder.status}">
												<input type="hidden" name="orderStatus" value="1"> 
												<input type="hidden" name="id" value="${user.id }"> 
												<input type="submit" value="取消订单">
											</form>


										</c:if> <c:if test="${totalOrder.status == orderStatus2}">
											<form action="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet" method="post">
												<input type="hidden" name="orderId" value="${totalOrder.id }"> 
												<input type="hidden" name="pageNum" value="1"> 
												<input type="hidden" name="jump" value="2"> 
												<input type="hidden" name="orderStatus" value="2"> 
												<input type="submit" value="查看订单详细信息">
											</form>

											<form
												action="/liqinglin_onlineOrderMealsSystem/DeleteOrderServlet"
												method="post">
												<input type="hidden" name="orderId" value="${totalOrder.id }"> 
												<input type="hidden" name="pageNum" value="1">
												<input type="hidden" name="orderStatus" value="2"> 
												<input type="submit" value="删除订单">
											</form>
										</c:if> 
										
										<c:if test="${totalOrder.status == orderStatus3}">
											<form action="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet" method="post">
												<input type="hidden" name="orderId" value="${totalOrder.id }"> 
												<input type="hidden" name="pageNum" value="1"> 
												<input type="hidden" name="jump" value="2"> 
												<input type="hidden" name="orderStatus" value="3"> 
												<input type="submit" value="查看订单详细信息">
											</form>

											<form action="/liqinglin_onlineOrderMealsSystem/DeleteOrderServlet" method="post">
												<input type="hidden" name="orderId" value="${totalOrder.id }"> 
												<input type="hidden" name="pageNum" value="1"> 
												<input type="hidden" name="orderStatus" value="3"> 
												<input type="submit" value="删除订单">
											</form>
										</c:if>
								</font></td>
							</tr>
						</tbody>
					</c:forEach>
					<c:if test="${totalPage == 1 }">
						<tbody>
							<tr>
								<td colspan="8" align="right">共${totalRecord }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${pageNum }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPage}页</td>
							</tr>
						</tbody>
					</c:if>
					<c:if test="${totalPage > 1 }">
						<form
							action="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet"
							method="get" onsubmit="return check2()">

							<tbody>

								<tr>

									<td colspan="9" align="right">

										共${totalRecord}条数据&nbsp;&nbsp;&nbsp;&nbsp;第${pageNum}页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;&nbsp;&nbsp;

										<c:if test="${pageNum == 1}">
											<a
												href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?userId=${user.id}&pageNum=${pageNum + 1}&jump=2&orderStatus=${totalOrders[0].status}">下一页</a>

										</c:if> <c:if test="${pageNum == totalPage }">
											<a
												href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?userId=${user.id}&pageNum=${pageNum - 1}&jump=2&orderStatus=${totalOrders[0].status}">上一页</a>

										</c:if> <c:if test="${pageNum > 1 && pageNum < totalPage }">
											<a
												href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?userId=${user.id}&pageNum=${pageNum - 1}&jump=2&orderStatus=${totalOrders[0].status}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
												<a
												href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?userId=${user.id}&jump=2&orderStatus=${totalOrder.status}&pageNum=${pageNum + 1}">下一页</a>
										</c:if> 
										<input type="hidden" id="userId" name="userId" value="${user.id}"> 
										<input type="text" id="pageNum" size="2" name="pageNum" value="${param.pageNum}">
										<input type="hidden" name ="orderStatus" value="${totalOrders[0].status}">
										<input type="submit" name="go" value="跳转">
										<input type="hidden" id="jump" name="jump" value="2"> 
									</td>

								</tr>

							</tbody>

						</form>
					</c:if>
				</table>
			</center>
		</c:if>
	</c:if>
</body>
</html>
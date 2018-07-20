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
	<c:if test="${empty user}">
		您还没有登录，请<a href="/liqinglin_onlineOrderMealsSystem/user/userLogin.jsp">登录</a>
	</c:if>
	<c:if test="${not empty user}">
		<c:if test="${store.status == status0 }">
			<center>
				<h1>
					您的信息还在审核中，请耐心等待 <a href="">退出</a>
				</h1>
			</center>
		</c:if>

		<c:if test="${store.status == status2 }">
			<center>
				<h1>
					很对不起，您的开店请求已被拒绝！ <a href="">退出</a>
				</h1>
			</center>
		</c:if>
	</c:if>
	<c:if test="${store.status == status1 && not empty user}">
		<h2>您好：${store.shopkeeperName }店家</h2>

		<nav class="nav">
		<ul>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/store/handleOrder.jsp">处理订单首页</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1&orderStatus=${orderStatus0}">未发货订单</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1&orderStatus=${orderStatus1}">已发货订单</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1&orderStatus=${orderStatus2}">已收货订单</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1&orderStatus=${orderStatus3}">已取消订单</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=1">返回主页面</a></li>
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
								<td align="center"><font size="2">${totalOrder.receiver }</font></td>
								<td align="center"><font size="2">${totalOrder.address }</font></td>
								<td align="center"><font size="2">${totalOrder.phone }</font></td>
								<td align="center"><font size="2">￥${totalOrder.totalPrice }</font></td>
								<td align="center"><font size="2">${totalOrder.message }</font></td>
								<td align="center"><font size="2">${totalOrder.createTime }</font></td>
								<td align="center"><font size="2"> <c:if
											test="${totalOrder.status == orderStatus0}">
											<form
												action="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet"
												method="post">
												<input type="hidden" name="orderId"
													value="${totalOrder.id }"> <input type="hidden"
													name="pageNum" value="1"> <input type="hidden"
													name="jump" value="1"> <input type="hidden"
													name="orderStatus" value="0"> <input type="submit"
													value="查看订单详细信息">
											</form>
											<form
												action="/liqinglin_onlineOrderMealsSystem/SendCuisineServlet"
												method="post">
												<input type="hidden" name="storeId" value=${store.storeId }>
												<input type="hidden" name="jump" value="1"> <input
													type="hidden" name="orderId" value="${totalOrder.id }">
												<input type="submit" value="送餐">
											</form>
											<form
												action="/liqinglin_onlineOrderMealsSystem/CancelOrderServlet"
												method="post">
												<input type="hidden" name="jump" value="1"}> 
												<input type="hidden" name="orderId" value="${totalOrder.id }">
												<input type="hidden" name="id" value="${store.storeId }">
												<input type="hidden" name="orderStatus" value="0">
												<input type="submit" value="取消订单">
											</form>
										</c:if> <c:if test="${totalOrder.status == orderStatus1}">
											<form
												action="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet"
												method="post">
												<input type="hidden" name="orderId"
													value="${totalOrder.id }"> <input type="hidden"
													name="pageNum" value="1"> <input type="hidden"
													name="jump" value="1"> <input type="hidden"
													name="orderStatus" value="1"> <input type="submit"
													value="查看订单详细信息">
											</form>
											<form
												action="/liqinglin_onlineOrderMealsSystem/CancelOrderServlet"
												method="post">
												<input type="hidden" name="orderId" value="${totalOrder.id }">
												<input type="hidden" name="id" value="${store.storeId }"> 
												<input type="hidden" name="jump" value="1"> 
												<input type="submit" value="取消订单">
											</form>
										</c:if> <c:if test="${totalOrder.status == orderStatus2}">
											<form
												action="/liqinglin_onlineOrderMealsSystem/GetOrderDetailServlet"
												method="post">
												<input type="hidden" name="orderId"
													value="${totalOrder.id }"> <input type="hidden"
													name="pageNum" value="1"> <input type="hidden"
													name="jump" value="1"> <input type="hidden"
													name="orderStatus" value="2"> <input type="submit"
													value="查看订单详细信息">
											</form>
										</c:if>



								</font></td>
							</tr>
						</tbody>
					</c:forEach>
					<c:if test="${totalPage == 1 }">
						<tbody>
							<tr>
								<td colspan="8" align="right">共${totalRecord }条数据&nbsp;&nbsp;&nbsp;&nbsp;第${pageNum }页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPageNum }页</td>
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
												href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=${pageNum + 1}&jump=1&orderStatus=${orderStatus}">下一页</a>

										</c:if> <c:if test="${pageNum == totalPage }">
											<a
												href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=${pageNum - 1}&jump=1&orderStatus=${orderStatus}">上一页</a>

										</c:if> <c:if test="${pageNum > 1 && pageNum < totalPage }">
											<a
												href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=${pageNum - 1}&jump=1&orderStatus=${orderStatus}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
												<a
												href="/liqinglin_onlineOrderMealsSystem/ListTotalOrderServlet?storeId=${store.storeId}&pageNum=${pageNum + 1}&jump=1&orderStatus=${orderStatus}">下一页</a>
										</c:if> 
										<input type="hidden" id="storeId" name="storeId" value="${store.storeId}"> 
										<input type="text" id="pageNum" size="2" name="pageNum" value="${param.pageNum}">
										<input type="hidden" name ="orderStatus" value="${orderStatus}">
										<input type="hidden" id="jump" name="jump" value="1">
										<input type="submit" name="go" value="跳转">

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
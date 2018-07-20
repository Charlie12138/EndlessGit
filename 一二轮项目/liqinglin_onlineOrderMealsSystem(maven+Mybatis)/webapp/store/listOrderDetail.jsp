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
	<c:if test="${not empty store }">
		<h2>您好：${store.shopkeeperName }店家</h2>
		<nav class="nav">
		<ul>
			<li><a href="/liqinglin_onlineOrderMealsSystem/store/handleOrder.jsp">处理订单首页</a></li>
			<c:if test="${orderStatus == orderStatus0 }">
				<li class="current"><a href="/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1">未发货订单</a></li>
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListSendedOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1">已发货订单</a></li>
				<li><a href="">已收货订单</a></li>
				<li><a href="">已取消订单</a></li>
			</c:if>
			<c:if test="${orderStatus == orderStatus1 }">
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1">未发货订单</a></li>
				<li class="current"><a href="/liqinglin_onlineOrderMealsSystem/ListSendedOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1">已发货订单</a></li>
				<li><a href="">已收货订单</a></li>
				<li><a href="">已取消订单</a></li>
			</c:if>
			<c:if test="${orderStatus == orderStatus2 }">
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1">未发货订单</a></li>
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListSendedOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1">已发货订单</a></li>
				<li class="current"><a href="">已收货订单</a></li>
				<li><a href="">已取消订单</a></li>
			</c:if>
			<c:if test="${orderStatus == orderStatus3 }">
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1">未发货订单</a></li>
				<li><a href="/liqinglin_onlineOrderMealsSystem/ListSendedOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1">已发货订单</a></li>
				<li><a href="">已收货订单</a></li>
				<li class="current"><a href="">已取消订单</a></li>
			</c:if>
			<li><a href="/liqinglin_onlineOrderMealsSystem/ListNotSendOrderServlet?storeId=${store.storeId}&pageNum=1&jump=1">返回主页面</a></li>
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
							<td align="center"><font size="2">${singleOrder.cuisine.cuisineName }</font></td>
							<td align="center"><font size="2">￥${singleOrder.totalPrice }</font></td>
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
											href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=${pageNum + 1}&jump=1">下一页</a>

									</c:if> <c:if test="${pageNum == totalPage }">
										<a
											href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=${pageNum - 1}&jump=1">上一页</a>

									</c:if> <c:if test="${pageNum > 1 && pageNum < totalPage }">
										<a
											href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=${pageNum - 1}&jump=1">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
												<a
											href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=${pageNum + 1}&jump=1">下一页</a>
									</c:if> <input type="hidden" id="storeId" name="storeId"
									value="${store.storeId}"> <input type="hidden"
									id="jump" name="jump" value="1"> <input type="text"
									id="pageNum" size="2" name="pageNum" value="${param.pageNum}">

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
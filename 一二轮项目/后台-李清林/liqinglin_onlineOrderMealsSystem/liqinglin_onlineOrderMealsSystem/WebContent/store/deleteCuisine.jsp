<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="/liqinglin_onlineOrderMealsSystem/css.store/index.css">
<title>下架美食</title>
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
				href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=1">首页</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/store/addCuisine.jsp">上架美食</a></li>
			<li class="current"><a
				href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=2">下架美食</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=1&jump=3">修改菜肴信息</a></li>
			<li><a href="">查看菜肴状态</a></li>
			<li><a href="/liqinglin_onlineOrderMealsSystem/store/modifyStoreInfo.jsp">完善/修改店铺信息</a></li>
			<li><a href="/liqinglin_onlineOrderMealsSystem/store/handleOrder.jsp">处理订单</a></li>
			<li><a href="">修改密码</a></li>
			<li><a href="">退出</a></li>
		</ul>
		</nav>

		<center>
			<h2></h2>

			<c:if test="${empty cuisines }">
				<center>
					<p>没有相应的记录，可能原因为：</p>
					<p>1、搜索页数没有在页数范围之内</p>
					<p>2、您的店铺还没有商品</p>
					<p>3、搜索的商品不存在</p>
				</center>
			</c:if>
			<c:if test="${not empty cuisines }">

				<table align=center>
					<tr>
						<td>搜索店铺商品</td>
						<td>

							<form action="" method="get" onsubmit="return check()">
								<input type="text" id="key" name="key" size="20%"> <input
									type="submit" name="query" value="搜索">
							</form>

						</td>
					</tr>
				</table>

				<table id="newspaper-a">
					<thead>
						<tr>
							<th align="center">菜名</th>
							<th align="center">美食描述</th>
							<th align="center">价格</th>
							<th align="center">图片</th>
							<th align="center">上架时间</th>
							<th align="center">操作</th>
						</tr>
					</thead>

					<c:forEach items="${cuisines}" var="cuisine">
						<tbody>
							<tr>
								<td align="center"><font size="2">${cuisine.cuisineName }</font></td>
								<td align="center"><font size="2">${cuisine.description }</font></td>
								<td align="center"><font size="2">￥${cuisine.price}</font></td>
								<td align="center"><img src="${cuisine.picturePath}"
									width="100" height="100" /></td>
								<td align="center"><font size="2">${cuisine.createTime }</font></td>
								<td align="center"><font size="2">

										<form
											action="/liqinglin_onlineOrderMealsSystem/DeleteCuisinesServlet"
											method="post">
											<input type="hidden" name="cuisineId" value="${cuisine.id}">
											<input type="submit" name="submit" value="下架">
										</form>

								</font></td>
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
												href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=${pageNum + 1}&jump=2">下一页</a>

										</c:if> <c:if test="${pageNum == totalPage }">
											<a
												href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=${pageNum - 1}&jump=2">上一页</a>

										</c:if> <c:if test="${pageNum > 1 && pageNum < totalPage }">
											<a
												href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=${pageNum - 1}&jump=2">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
												<a
												href="/liqinglin_onlineOrderMealsSystem/ListCuisinesServlet?storeId=${store.storeId}&pageNum=${pageNum + 1}&jump=2">下一页</a>
										</c:if> 
										
										<input type="hidden" id="storeId" name="storeId" value="${store.storeId}"> 
										<input type="hidden" id="jump" name="jump" value="2"> 
										<input type="text" id="pageNum" size="2" name="pageNum" value="${param.pageNum}">
										<input type="submit" name="go" value="跳转">

									</td>

								</tr>

							</tbody>

						</form>
					</c:if>
				</table>
			</c:if>
		</center>

	</c:if>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理店铺</title>
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
				href="/liqinglin_onlineOrderMealsSystem/ExamineRegisterServlet">管理申请注册信息</a></li>
			<li><a
				href="/liqinglin_onlineOrderMealsSystem/ExamineOpenStoreServlet">查看用户开店申请信息</a></li>
			<li class="current"><a
				href="/liqinglin_onlineOrderMealsSystem/ListAllStoresServlet?pageNum=1"">管理店铺</a></li>
			<li ><a
				href="/liqinglin_onlineOrderMealsSystem/ExamineAddCuisinesServlet">审核店铺上架的食品</a></li>
			<li><a
				href="">退出</a></li>
		</ul>
		</nav>
		<hr width="100%">
	
		<c:if test="${empty stores }">
			<h3>没有店铺</h3>
		</c:if>
		
		<c:if test="${not empty stores }">
			<table id="newspaper-a">
				<thead>
					<tr>
						<th align="center">店主姓名</th>
						<th align="center">店铺电话</th>
						<th align="center">店铺名</th>
						<th align="center">店铺描述</th>
						<th align="center">申请开店的时间</th>
						<th align="center">店铺状态</th>
						<th align="center">操作</th>
					</tr>
				</thead>
				
				<c:forEach items="${stores }" var="store">
					<tbody>
						<tr>
							<td align="center"><font size="2">${store.shopkeeperName }</font></td>
							<td align="center"><font size="2">${store.phone }</font></td>
							<td align="center"><font size="2">${store.storeName }</font></td>
							<td align="center"><font size="2">${store.storeDescription }</font></td>
							<td align="center"><font size="2">${store.createTime }</font></td>
							<td align="center"><font size="2">
								<c:if test="${store.status == status1}">
									正常开店														
								</c:if>
								<c:if test="${store.status == status2}">
									拒绝开店														
								</c:if>
								<c:if test="${store.status == status3}">
									被强制关闭														
								</c:if>
							</font></td>
							
							<td align="center"><font size="2">	
										
							<c:if test="${store.status == status1 }">	
								<form action="/liqinglin_onlineOrderMealsSystem/ManagerStoreServlet?status=${status3}" method="post">
									<input type = "hidden" name = "storeName" value = "${store.storeName}">
									<input type="submit" name="submit" value="关闭店铺">
								</form>
							</c:if>
							
							<c:if test="${store.status == status2 }">
								<form action="/liqinglin_onlineOrderMealsSystem/ManagerStoreServlet?status=${status1}" method="post">
									<input type = "hidden" name = "storeName" value = "${store.storeName}">
									<input type="submit" name="submit" value="开启店铺">
								</form>
							 </c:if>
							 		
							<c:if test="${store.status == status3 }">
								<form action="/liqinglin_onlineOrderMealsSystem/ManagerStoreServlet?status=${status1}" method="post">
									<input type = "hidden" name = "storeName" value = "${store.storeName}">
									<input type="submit" name="submit" value="开启店铺">
								</form>
							</c:if>	
													
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
					
						<form action="/liqinglin_onlineOrderMealsSystem/ListAllStoresServlet" method="get" onsubmit="return check2()">
						
							<tbody>
							
								<tr>
								
								<td colspan="9" align="right">
									
									共${totalRecord}条数据&nbsp;&nbsp;&nbsp;&nbsp;第${pageNum}页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;&nbsp;&nbsp;
									
									<c:if test="${pageNum == 1}">
											
										<a href="/liqinglin_onlineOrderMealsSystem/ListAllStoresServlet?pageNum=${pageNum + 1}">下一页</a>
												
									</c:if>
										
								    <c:if test="${pageNum == totalPage }">
										
										<a href="/liqinglin_onlineOrderMealsSystem/ListAllStoresServlet?pageNum=${pageNum - 1}">上一页</a>
												
									</c:if> 
										
									<c:if test="${pageNum > 1 && pageNum < totalPage }">
										<a href="/liqinglin_onlineOrderMealsSystem/ListAllStoresServlet?pageNum=${pageNum - 1}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="/liqinglin_onlineOrderMealsSystem/ListAllStoresServlet?pageNum=${pageNum + 1}">下一页</a>
									</c:if> 
										
									<input type = "hidden" id = "storeId" name = "storeId" value = "${store.storeId}">
										
									<input type="text" id="pageNum" size="2" name="pageNum" value="${param.pageNum}">
										
									<input type="submit" name="go" value="跳转">
										
								 </td>
										
							</tr>
								
						</tbody>
							
					</form>
					
				</c:if>	
				
			</table>
		</c:if>
	</c:if>
	
	<c:if test="${empty user }">
		<h1>
			必须登录后才能访问该页面，<a
				href="/liqinglin_onlineOrderMealsSystem/user/userLogin.jsp">去登录</a>
		</h1>
	</c:if>
	
</body>
</html>
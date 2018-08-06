<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核注册</title>
<link rel="stylesheet" type="text/css"
	href="css/css.admin/adminIndex.css">
</head>
<body>
	<h2>管理员页面</h2>
	<nav class="nav">
		<ul>
			<li><a
				href="/toAdminIndex">首页</a></li>
			<li class="current"><a
				href="/ExamineRegisterServlet">管理注册申请信息</a></li>
			<li><a
				href="/ExamineOpenStoreServlet">查看用户开店申请信息</a></li>
			<li><a
				href="/ListAllStoresServlet?pageNum=1">管理店铺</a></li>
			<li><a
				href="/ExamineAddCuisinesServlet">审核店铺上架的食品</a></li>
			<li><a
				href="/UserDropOutServlet">退出</a></li>
		</ul>
	</nav>
	<hr width="100%">
	<c:if test="${empty users }">
			<h3>没有任何注册申请消息</h3>
	</c:if>
	
	<center>
	
			<c:if test="${ not empty users }">
			
				<table id="newspaper-a">
				
					<thead>
					
						<tr>
							<th align="center">用户名</th>
							<th align="center">申请时间</th>
							<th align="center">操作</th>
						</tr>
						
					</thead>
					
					<c:forEach items="${users}" var="user">
					
						<tbody>
						
							<tr>
								<td align="center"><font size="2">${user.username }</font></td>
								<td align="center"><font size="2">${user.createTime}</font></td>
								<td align="center"><font size="2">
									
									<form action = "/AgreeRegisterServlet" method = "post">
										<input type = "hidden" name = "username" value = "${user.username }">
										<input type="submit" name="submit" value="同意">
									</form>	
	
								</font></td>
								
							</tr>
							
						</tbody>
						
					</c:forEach>
					
				</table>
				
			</c:if>
			
		</center>
				
</body>
</html>
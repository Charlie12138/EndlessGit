<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="css/css.store/index.css">
<script type="text/javascript">

function check() {
	var query = document.getElementById("query").value;
	if (query == "") {
		alert("输入不能为空");
		return false;
	}
}

</script>	
	
<title>点餐系统主页</title>
</head>
<body >
	
	<center>
		<header>
		<div class="container" id="head">
			<ul class="ulheader">
				<h1><li><a
					href="${pageContext.request.contextPath}/toLogin">会员登录</a></li>
				<li><a
					href="${pageContext.request.contextPath}/toRegister">免费注册</a></li></h1>
			</ul>
		</div>
		</header>
	</center>	
		
	<br>
	<br>
	<br>
	
	<center>
		<h1>美食总览</h1>
	</center>
	
	<center>
		<form action="/SearchServlet" method="get" onsubmit="return check()">
			<input type="text" id="query" name="key" size="20%" placeholder="输入关键字"><br> 
			<input type="radio" name="choose" class="choose" value="cuisine" checked="checked">美食
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<input type="radio" name="choose" class="choose" value="store">店铺<br>
			<input type="submit" name="query" value="搜索">
			<input type = "hidden" name = "pageNum" value=1>
			<input type = "hidden" name = "jump" value=1>
		</form>
	</center>
		
		<center>
			<h2></h2>
			
			<c:if test="${empty cuisines || cuisineStatus == 0}">
				<center>
					<p>没有相应的记录，可能原因为：</p>
					<p>1、搜索页数没有在页数范围之内</p>
					<p>2、您的店铺还没有商品</p>
					<p>3、搜索的商品不存在</p>
				</center>
			</c:if>
			
			<c:if test="${not empty cuisines && cuisineStatus != 0}">
				
				<table id="newspaper-a">
					<thead>
						<tr>
							<th align="center">店铺</th>
							<th align="center">菜名</th>
							<th align="center">美食描述</th>
							<th align="center">价格</th>
							<th align="center">上架时间</th>
							<th align="center">图片</th>
						</tr>
					</thead>

					<c:forEach items="${cuisines}" var="cuisine">
							<tbody>
								<tr>
									<td align="center"><font size="2">${cuisine.store.storeName }</font></td>
									<td align="center"><font size="2">${cuisine.cuisineName }</font></td>
									<td align="center"><font size="2">${cuisine.description }</font></td>
									<td align="center"><font size="2">￥${cuisine.price}</font></td>
									<td align="center"><font size="2">${cuisine.createTime}</font></td>
									<td align="center"><img src="${cuisine.picturePath}" width="100" height="100" /></td>
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
						<form action="/ListAllCuisine" method="get" onsubmit="return check2()">
						
							<tbody>
							
								<tr>
								
									<td colspan="9" align="right">
									
										共${totalRecord}条数据&nbsp;&nbsp;&nbsp;&nbsp;第${pageNum}页&nbsp;&nbsp;&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;&nbsp;&nbsp;
									
										<c:if test="${pageNum == 1}">
												<a href="/ListAllCuisine?pageNum=${pageNum + 1}&jump=1">下一页</a>
												
										</c:if>
										
										 <c:if test="${pageNum == totalPage }">
												<a href="/ListAllCuisine?pageNum=${pageNum - 1}&jump=1">上一页</a>
												
										</c:if> 
										
										<c:if test="${pageNum > 1 && pageNum < totalPage }">
												<a href="/ListAllCuisine?pageNum=${pageNum - 1}&jump=1">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="/ListAllCuisine?pageNum=${pageNum + 1}&jump=1">下一页</a>
										</c:if> 
										
										<input type = "hidden" id = "storeId" name = "storeId" value = "${store.storeId}">
																				
										<input type = "hidden" id = "jump" name = "jump" value = "1">
										
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
</body>
</html>
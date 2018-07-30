<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: clear
  Date: 2018/7/28
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="testConversionServiceConverter" method="post">
        Employee:<input type="text" name="employee"/>
        <input type="submit" value="Submit"/>
    </form>
    <br><br>
<!--
		1. WHY 使用 form 标签呢 ?
		可以更快速的开发出表单页面, 而且可以更方便的进行表单值的回显
		2. 注意:
		可以通过 modelAttribute 属性指定绑定的模型属性,
		若没有指定该属性，则默认从 request 域对象中读取 command 的表单 bean
		如果该属性值也不存在，则会发生错误。
	-->

    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute = "employee" >
        <c:if test="${employee.id == null}">
            LastName:LastName:<form:input path="lastName" />
        </c:if>
        <%-- 对于 _method 不能使用 form:hidden 标签, 因为 modelAttribute 对应的 bean 中没有 _method 这个属性 --%>
        <%--
        <form:hidden path="_method" value="PUT"/>
        --%>
        <c:if test="${employee.id != null}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT"/>
        </c:if>
        <br>
        Email:<form:input path="email"/>
        <br>
        <%
            Map<String, String> genders = new HashMap<String, String>();
            genders.put("1", "Male");
            genders.put("0", "Female");
            request.setAttribute("genders", genders);
        %>
        Gender:<br>
        <form:radiobuttons path="gender" items="${genders}" delimiter="<br>" />
        <br>
        Department:<form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"></form:select>
        <br>
        Birth:<form:input path="birth"/>
        <br>
        Salary:<form:input path="salary"/>
        <br>
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: clear
  Date: 2018/7/25
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>success page</h1>
    time:${time}

    <br>

    names:${names}

    <br>

    request user:${requestScope.user}
    <br>
    session user:${sessionScope.user}

    <br>
    request string:${requestScope.hhh}
    <br>
    session string:${sessionScope.hhh}

    <br><br>
    username:<fmt:message key="i18n.username"></fmt:message>
    <br><br>
    password:<fmt:message key="i18n.password"></fmt:message>
</body>
</html>

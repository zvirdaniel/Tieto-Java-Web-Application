<%--
  Created by IntelliJ IDEA.
  User: zvird
  Date: 20.07.2016
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>WebApp</title>
</head>
<body>

<jsp:useBean id="hello" class="com.tieto.webapp.HelloBean"/>
<% pageContext.setAttribute("hello", hello); %>
<% pageContext.setAttribute("request", request); %>

<jsp:useBean id="now" class="java.util.Date"/>
Hello, <c:out value="${hello.getName(request)}" default="name not specified"/> it's <c:out value="${now}"/>

<h2>Headers:</h2>
<table>
    <c:forEach items="${hello.getHeaders(request)}" var="property">
        <tr>
            <td><c:out value="${property.key}:"/></td>
            <td><c:out value="${property.value}"/></td>
        </tr>
    </c:forEach>
</table>
<h2>Parameters:</h2>
<table>
    <c:forEach items="${hello.getParameters(request)}" var="property">
        <td><c:out value="${property.key}:"/></td>
        <td><c:out value="${property.value}"/></td>
    </c:forEach>
</table>
<table>
    <h2>Attributes:</h2>
    <c:forEach items="${hello.getAttributes(request)}" var="property">
        <td><c:out value="${property.key}:"/></td>
        <td><c:out value="${property.value}"/></td>
    </c:forEach>
</table>
<table>
    <h2>Session attributes:</h2>
    <c:forEach items="${hello.getSessionAttributes(request)}" var="property">
        <td><c:out value="${property.value}:"/></td>
        <td><c:out value="${property.key}"/></td>
    </c:forEach>
</table>
</body>
</html>

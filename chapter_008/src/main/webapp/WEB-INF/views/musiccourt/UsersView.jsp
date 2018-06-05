<%@ page import="rzaharov.servlets.servlet.User" %>
<%@ page import="rzaharov.servlets.servlet.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users data</title>
    <style>
        <jsp:directive.include file="/static/css/style.css" />
    </style>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<H1>Users data</H1>
<table border="1"><caption>Users data</caption>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Login</th>
        <c:if test="${role == 'Admin'}">
            <th>Password</th>
        </c:if>
        <th>Role</th>
        <th>Music Type</th>
        <th>City</th>
        <th>District</th>
        <th>Street</th>
        <th>House</th>
        <th>Apartment</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.name}"></c:out> </td>
            <td><c:out value="${user.surname}"></c:out> </td>
            <td><c:out value="${user.login}"></c:out> </td>
            <c:if test="${role == 'Admin'}">
                <td><c:out value="${user.password}"></c:out> </td>
            </c:if>
            <td><c:out value="${user.role.name}"></c:out> </td>
            <td><c:forEach items="${user.musicTypes}" var="mt">
            <c:out value="${mt.name}"></c:out>
            </c:forEach></td>
            <td><c:out value="${user.address.city}"></c:out> </td>
            <td><c:out value="${user.address.district}"></c:out> </td>
            <td><c:out value="${user.address.street}"></c:out> </td>
            <td><c:out value="${user.address.house}"></c:out> </td>
            <td><c:out value="${user.address.apartment}"></c:out> </td>
            <td><form action="${pageContext.servletContext.contextPath}/edit" method="get">
                <input type="hidden" name="login" value="${user.login}"/>
                <button type="submit">Update</button>
            </form></td>
            <td><form action="${pageContext.servletContext.contextPath}/delete" method="get">
                <input type="hidden" name="login" value="${user.login}"/>
                <button type="submit">Delete</button>
            </form></td></tr>
    </c:forEach>
</table><br>
<c:if test="${role == 'Admin'}">
    <form action="${pageContext.servletContext.contextPath}/createUser" method="get">
        <button type="submit">Create</button>
    </form>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signout" method="post">
    <button type="submit">Sign Out</button>
</form>
</body>
</html>


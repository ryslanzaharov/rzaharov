<%@ page import="rzaharov.servlets.servlet.User" %>
<%@ page import="rzaharov.servlets.servlet.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1"><caption>Users data</caption>
        <tr>
            <th>Email</th>
           <th>Name</th>
            <th>Login</th>
           <th>Date</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.name}"></c:out> </td>
            <td><c:out value="${user.login}"></c:out> </td>
            <td><c:out value="${user.createDate}"></c:out> </td>
            <td><form action="${pageContext.servletContext.contextPath}/edit" method="get">
            <input type="hidden" name="email" value="${user.email}"/>
            <button type="submit">Update</button>
            </form></td>
            <td><form action="${pageContext.servletContext.contextPath}/delete" method="post">
            <input type="hidden" name="email" value="${user.email}"/>
            <button type="submit">Delete</button>
            </form></td></tr>
    </c:forEach>
    </table>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    <button type="submit">Create</button>
    </form>
</body>
</html>

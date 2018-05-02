<%@ page import="rzaharov.servlets.servlet.User" %>
<%@ page import="rzaharov.servlets.servlet.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<table border="1"><caption>Users data</caption>
        <tr>
            <th>Email</th>
           <th>Name</th>
            <th>Login</th>
           <th>Date</th>
            <c:if test="${role == 'Admin'}">
                <th>Role</th>
            </c:if>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.name}"></c:out> </td>
            <td><c:out value="${user.login}"></c:out> </td>
            <td><c:out value="${user.createDate}"></c:out> </td>
            <c:if test="${role == 'Admin'}">
                <td><c:out value="${user.role}"></c:out> </td>
            </c:if>
            <td><form action="${pageContext.servletContext.contextPath}/edit" method="get">
            <input type="hidden" name="email" value="${user.email}"/>
            <button type="submit">Update</button>
            </form></td>
            <td><form action="${pageContext.servletContext.contextPath}/delete" method="get">
            <input type="hidden" name="email" value="${user.email}"/>
            <button type="submit">Delete</button>
            </form></td></tr>
    </c:forEach>
    </table>
<c:if test="${role == 'Admin'}">
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <button type="submit">Create</button>
    </form>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signout" method="post">
    <button type="submit">Sign Out</button>
</form>
</body>
</html>

<%@ page import="rzaharov.servlets.servlet.User" %>
<%@ page import="rzaharov.servlets.servlet.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="user" value="${users}" scope="request"/>
    <form action="${pageContext.servletContext.contextPath}/edit" method="post">
                <input type="hidden" name="oldEmail" value="${user.email}">
        Email : <input type="text" name="email" value="${user.email}">
        Name :  <input type="text" name="name" value="${user.name}">
        Login : <input type="text" name="login" value="${user.login}">
        Password : <input type="text" name="password" value="${user.password}">
        <c:if test="${role == 'Admin'}">
            <select name="role">
                <option value="User"> User </option>
                <option value="Admin"> Admin </option>
            </select>

        </c:if>
        <c:if test="${role != 'Admin'}">
            <input type="hidden" name="role" value="${user.role}">
        </c:if>
        <input type="submit" value="Edit">
    </form>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="Back">
</form>
<form action="${pageContext.servletContext.contextPath}/signout" method="post">
    <button type="submit">Sign Out</button>
</form>
</body>
</html>

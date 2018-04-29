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
        <input type="submit" value="Edit">
    </form>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="Back">
</form>
</body>
</html>

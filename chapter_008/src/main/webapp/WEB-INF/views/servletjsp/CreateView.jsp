<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Edit users data:<br/>
    <form action="<%=request.getContextPath()%>/create" method="post">
        Email : <input type="text" name="email">
        Name :  <input type="text" name="name">
        Login : <input type="text" name="login">
        Password : <input type="text" name="password">
        <c:if test="${role == 'Admin'}">
            <select name="role">
                <option value="User"> User </option>
                <option value="Admin"> Admin </option>
            </select>

        </c:if>
                <input type="submit" value="Create">
    </form>
    <form action="${pageContext.servletContext.contextPath}/" >
        <input type="submit" value="Back">
    </form>
    <form action="${pageContext.servletContext.contextPath}/signout" method="post">
        <button type="submit">Sign Out</button>
    </form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Руслан
  Date: 12.06.2018
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Create user data:</h1>
<form action="<%=request.getContextPath()%>/createUser" method="post" onsubmit="return validate();">
    Login : <input type="text" name="login"><br><br>
    Password : <input type="password" name="password"><br><br>
    <input type="submit" value="Create">
</form>
<form action="${pageContext.servletContext.contextPath}/" >
    <input type="submit" value="Home">
</form>
</body>
</html>

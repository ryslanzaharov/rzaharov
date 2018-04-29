<%@ page import="rzaharov.servlets.servlet.User" %>
<%@ page import="rzaharov.servlets.servlet.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% UserStore users = UserStore.UserStoreSingleton.INSTANCE.getInstance();
    User user = users.select(request.getParameter("email"));
%>
    <form action="<%=request.getContextPath()%>/edit" method="post">
                <input type="hidden" name="oldEmail" value="<%=user.getEmail()%>">
        Email : <input type="text" name="email" value="<%=user.getEmail()%>">
        Name :  <input type="text" name="name" value="<%=user.getName()%>">
        Login : <input type="text" name="login" value="<%=user.getLogin()%>">
        <input type="submit" value="Edit">
    </form>
<form action="<%=request.getContextPath()%>/servletjsp/list.jsp" method="get">
    <input type="submit" value="Back">
</form>
</body>
</html>

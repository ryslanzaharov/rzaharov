<%@ page import="rzaharov.servlets.servlet.User" %>
<%@ page import="rzaharov.servlets.servlet.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <% for (User user : UserStore.UserStoreSingleton.INSTANCE.getInstance().select()) {%>
        <tr>
            <td><%=user.getEmail()%></td>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getCreateDate()%></td>
            <td><form action="<%=request.getContextPath()%>/servletjsp/edit.jsp">
            <input type="hidden" name="email" value="<%=user.getEmail()%>"/>
            <button type="submit">Update</button>
            </form></td>
            <td><form action="<%=request.getContextPath()%>/delete" method="post">
            <input type="hidden" name="email" value="<%=user.getEmail()%>"/>
            <button type="submit">Delete</button>
            </form></td></tr>
    <%}%>
    </table>
<form action="<%=request.getContextPath()%>/servletjsp/create.jsp" method="post">
    <button type="submit">Create</button>
    </form>
</body>
</html>

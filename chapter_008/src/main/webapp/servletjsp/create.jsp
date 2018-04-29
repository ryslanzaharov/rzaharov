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
                <input type="submit" value="Create">
    </form>
    <form action="<%=request.getContextPath()%>/servletjsp/list.jsp" >
        <input type="submit" value="Back">
    </form>
</body>
</html>

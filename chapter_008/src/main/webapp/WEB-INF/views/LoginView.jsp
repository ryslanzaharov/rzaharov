<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign In</title>
    <style>
        <jsp:directive.include file="/static/css/style.css" />
    </style>
    <script>
        function validate() {
            var result = true;
            var login = document.getElementsByName("login")[0].value;
            var password = document.getElementsByName("password")[0].value;
            console.log()
            if (login == "" || password == "") {
                result = false;
            }
            if (!result) {
                alert("Please correct input date.");
            }
            return result;
        }
    </script>
</head>
<body>
<h1>Enter your email and password</h1>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post"  onsubmit="return validate();">
    Your login : <input type="text" name="login"><br/><br/>
    Password : <input type="password" name="password"><br/>
    <input type="submit" value="Sign in">
</form>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <input type="submit" value="Registration">
</form>

</body>
</html>

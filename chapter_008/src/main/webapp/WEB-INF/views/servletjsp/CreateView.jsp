<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create data</title>
    <style>
        <jsp:directive.include file="/static/css/style.css" />
    </style>
    <script src="http://code.jquery.com/jquery-1.7.js"
            type="text/javascript"></script>
    <script
            src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"
            type="text/javascript"></script>
    <link
            href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
            rel="stylesheet" type="text/css" />
    <script>

        $(document).ready(function() {
            $("input#autoText").autocomplete({
                width: 300,
                max: 10,
                delay: 100,
                minLength: 1,
                autoFocus: true,
                cacheLength: 1,
                scroll: true,
                highlight: false,
                source: function(request, response) {
                    $.ajax({
                        url: "./ajax",
                        dataType: "json",
                        data: request,
                        success: function( data, textStatus, jqXHR) {
                            console.log( data);
                            var items = data;
                            response(items);
                        },
                        error: function(jqXHR, textStatus, errorThrown){
                            console.log( textStatus);
                        }
                    });
                }

            });
        });

        function validate() {
            var result = true;
            var email = document.getElementsByName("email")[0].value;
            var name = document.getElementsByName("name")[0].value;
            var login = document.getElementsByName("login")[0].value;
            var password = document.getElementsByName("password")[0].value;
            var country = document.getElementsByName("country")[0].value;
            var city = document.getElementsByName("city")[0].value;
            console.log()
            if (email == "" || password == "" || name == "" || login == "" || country == "" || city == "") {
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
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
    <h1>Create user data:</h1>
    <form action="<%=request.getContextPath()%>/create" method="post" onsubmit="return validate();">
        Email : <input type="text" name="email"><br><br>
        Name :  <input type="text" name="name"><br><br>
        Login : <input type="text" name="login"><br><br>
        Password : <input type="password" name="password"><br><br>
        Country : <input type="text" name="country"><br><br>
        City : <input id="autoText" maxlength="20" name="city"/><br><br>


        <c:if test="${role == 'Admin'}">
            <select name="role">
                <option value="User"> User </option>
                <option value="Admin"> Admin </option>
            </select>
        </c:if><br><br>
        <c:if test="${role =! 'Admin'}">
            <input type="hidden" name="role" value="User"><br><br>
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

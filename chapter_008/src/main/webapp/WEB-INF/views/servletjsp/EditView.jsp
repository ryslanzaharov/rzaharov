<%@ page import="rzaharov.servlets.servlet.User" %>
<%@ page import="rzaharov.servlets.servlet.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit users data</title>
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
<h1>Edit users data</h1>
<c:set var="user" value="${users}" scope="request"/>
    <form action="${pageContext.servletContext.contextPath}/edit" method="post" onsubmit="return validate();">
                <input type="hidden" name="oldEmail" value="${user.email}">
        Email : <input type="text" name="email" value="${user.email}"><br><br>
        Name :  <input type="text" name="name" value="${user.name}"><br><br>
        Login : <input type="text" name="login" value="${user.login}"><br><br>
        Password : <input type="text" name="password" value="${user.password}"><br><br>
        Country : <input type="text" name="country" value="${user.country}"><br><br>
        City : <input id="autoText" maxlength="20" name="city" value="${user.city}"><br><br>
        <c:if test="${role == 'Admin'}">
            <select name="role">
                <option value="User"> User </option>
                <option value="Admin"> Admin </option>
            </select>
        </c:if><br><br>
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

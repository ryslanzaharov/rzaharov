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
            var name = document.getElementsByName("name")[0].value;
            var surname = document.getElementsByName("surname")[0].value;
            var login = document.getElementsByName("login")[0].value;
            var password = document.getElementsByName("password")[0].value;
            var role = document.getElementsByName("role")[0].value;
            var musictype = document.getElementsByName("musictype")[0].value;
            var city = document.getElementsByName("city")[0].value;
            var district = document.getElementsByName("district")[0].value;
            var street = document.getElementsByName("street")[0].value;
            var house = document.getElementsByName("house")[0].value;
            var apartment = document.getElementsByName("apartment")[0].value;
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
<form action="${pageContext.servletContext.contextPath}/editUser" method="post" onsubmit="return validate();">
    <input type="hidden" name="oldLogin" value="${user.login}">
    Name :  <input type="text" name="name" value="${user.name}"><br><br>
    Surname :  <input type="text" name="surname" value="${user.surname}"><br><br>
    Login : <input type="text" name="login" value="${user.login}"><br><br>
    Password : <input type="text" name="password" value="${user.password}"><br><br>

    <%--Role : <input type="text" name="role" value="${user.role.name}"><br><br>--%>
    Role :  <select name="role">
    <option value="1"> User </option>
    <c:if test="${role == 'Admin'}">
        <option value="2"> Admin </option>
    </c:if>
    <option value="3"> Mandator </option>
</select>
    <br><br>
    Music Type : <select name="musictype" multiple>
    <option value="1"> RAP </option>
    <option value="2"> JAZZ </option>
    <option value="3"> ROCK </option>
    <option value="4"> CLASSIC </option>
</select>
    <br><br>
    City : <input type="text" name="city" value="${user.address.city}"><br><br>
    District : <input type="text" name="district" value="${user.address.district}"><br><br>
    Street : <input type="text" name="street" value="${user.address.street}"><br><br>
    House : <input type="text" name="house" value="${user.address.house}"><br><br>
    Apartment : <input type="text" name="apartment" value="${user.address.apartment}"><br><br>

    <select name="role">
        <option value="User"> User </option>
        <c:if test="${role == 'Admin'}">
            <option value="Admin"> Admin </option>
        </c:if>
        <option value="Mandator"> Mandator </option>
    </select>
    <br><br>
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

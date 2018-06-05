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
            if (password == "" || name == "" || login == "" || surname == "" || city == ""
                || role == "" || musictype == "" || district == "" || street == ""
                || house == "" || apartment == "") {
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
<form action="<%=request.getContextPath()%>/createUser" method="post" onsubmit="return validate();">
    Name :  <input type="text" name="name"><br><br>
    Surname :  <input type="text" name="surname"><br><br>
    Login : <input type="text" name="login"><br><br>
    Password : <input type="password" name="password"><br><br>
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
    City : <input type="text" name="city"><br><br>
    District : <input type="text" name="district"><br><br>
    Street : <input type="text" name="street"><br><br>
    House : <input type="text" name="house"><br><br>
    Apartment : <input type="text" name="apartment"/><br><br>


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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script>
        function validate() {
            var result = true;
            var photo = document.getElementsByName("mark")[0].value;
            var mark = document.getElementsByName("mark")[0].value;
            var model = document.getElementsByName("model")[0].value;
            var body_type = document.getElementsByName("body_type")[0].value;
            var engine_name = document.getElementsByName("engine_name")[0].value;
            var type_engine = document.getElementsByName("type_engine")[0].value;
            var engine_condition = document.getElementsByName("engine_condition")[0].value;
            var condition_condition = document.getElementsByName("condition_condition")[0].value;
            var year = document.getElementsByName("year")[0].value;
            var mileage = document.getElementsByName("mileage")[0].value;
            var price = document.getElementsByName("price")[0].value;
            var sale = document.getElementsByName("sale")[0].value;
            console.log()
            if (photo == "" || mark == "" || model == "" || body_type == "" || engine_name == "" || type_engine == ""
                || engine_condition == "" || condition_condition == "" || year == "" || mileage == "" || price == "" || sale == "") {
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
<h1>Edit users data</h1>

<table border="1"><caption>Users data</caption>
    <tr>
        <th>Photo</th>
        <th>Producer</th>
        <th>Model</th>
        <th>Body</th>
        <th>Engine</th>
        <th>Type_of_engine</th>
        <th>Engine_condition</th>
        <th>Car_condition</th>
        <th>Year</th>
        <th>Mileage</th>
        <th>Price</th>
        <th>Sale</th>
    </tr>

    <c:forEach items="${cars}" var="car">
        <form action="${pageContext.servletContext.contextPath}/editCar" method="post">
        <tr>
            <td><input type="text" name="photo" value="${car.mark}"></td>
            <td><input type="text" name="mark" value="${car.mark}"> </td>
            <td><input type="text" name="model" value="${car.model}"> </td>
            <td><input type="text" name="body_type" value="${car.body_type}"></td>
            <td><input type="text" name="name" value="${car.engine.name}"></td>
            <td><input type="text" name="type_engine" value="${car.engine.type_engine}"></td>
            <td><input type="text" name="conditionEng" value="${car.engine.conditionEng}"></td>
            <td><input type="text" name="conditionCond" value="${car.condition.conditionCond}"></td>
            <td><input type="text" name="year" value="${car.condition.year}"></td>
            <td><input type="text" name="mileage" value="${car.condition.mileage}"></td>
            <td><input type="text" name="price" value="${car.price}"></td>
            <td><select name="sale">
                <option value="sales">Sales</option>
                <option value="not_sale">Not sale</option>
            </select></td>

            <td>
                <input type="hidden" name="car_id" value="${car.id}"/>
                <input type="hidden" name="engId" value="${car.engine.id}"/>
                <input type="hidden" name="condId" value="${car.condition.id}"/>
                <button type="submit">Update</button>
            </td></tr>
    </form>
    </c:forEach>

</table><br>
<form action="${pageContext.servletContext.contextPath}/signout" method="post">
    <button type="submit">Sign Out</button>
</form>
<form action="${pageContext.servletContext.contextPath}/" >
    <input type="submit" value="Home">
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<form action="<%=request.getContextPath()%>/createCar"  method="post" onsubmit="return validate();">

    Photo :  <input formenctype="multipart/form-data" type="file" name="photo"><br><br>
    Producer :  <input type="text" name="mark"><br><br>
    Model : <input type="text" name="model"><br><br>
    Body : <input type="text" name="body_type"><br><br>
    Engine :<input type="text" name="engine_name"><br><br>
    Type_of_engine :<input type="text" name="type_engine"><br><br>
    Engine_condition :<input type="text" name="engine_condition"><br><br>
    Car_condition :<input type="text" name="condition_condition"><br><br>
    Year :<input type="text" name="year"><br><br>
    Mileage :<input type="text" name="mileage"><br><br>
    Price :<input type="text" name="price"><br><br>
    Sale : <input type="text" name="sale"><br><br>

    <input type="submit" value="Create">
</form>
<form action="${pageContext.servletContext.contextPath}/signout" method="post">
    <button type="submit">Sign Out</button>
</form>
<form action="${pageContext.servletContext.contextPath}/" >
    <input type="submit" value="Home">
</form>
</body>
</html>

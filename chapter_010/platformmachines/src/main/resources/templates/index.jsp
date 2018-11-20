<%--
  Created by IntelliJ IDEA.
  User: Руслан
  Date: 12.09.2018
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>Simple To Do App</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
            integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
            integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
            integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
            crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <script src="js/index.js"></script>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<form id="mark">
    Mark : <select name="mark">
    <option value="">--Выберите--</option>
    <option value="honda">Honda</option>
    <option value="mazda">Mazda</option>
    <option value="opel">Opel</option>
    <option value="lada">Lada</option>
</select>
    <!--Show for last day : <input type="checkbox" id="last" name="data"><br/><br/>-->
    Show for last day <input type="checkbox" id="last" />
    <input type="button"  onclick="updatePage()" value="Search"></input>
</form>


<div class="row">
    <div class="container">
        <div class="row">
            <div class="col-6">
                <form action="./editCar" method="get">
                    <input type="submit" value="Update Car">
                </form>
                <form action="./createCar" method="get">
                    <button type="submit">Create</button>
                </form>
                <table id="list" class="table">
                    <tr>
                        <th>ID</th>
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
                        <th>Date</th>
                    </tr>
                </table>
            </div>
            <div class="col"></div>
        </div>
    </div>
</div>
</body>
</html>

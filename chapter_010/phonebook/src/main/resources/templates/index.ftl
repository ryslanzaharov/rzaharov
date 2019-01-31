<!DOCTYPE html>
<html lang="en"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
    <#--<script src="js/statistic.js"></script>-->
</head>
<body>
<form action="/addcontacts" method="get">
    <button type="submit">Add contacts</button>
    </form>
<form action="/editcontacts" method="get">
    <button type="submit">Edit contacts</button>
</form>
<form action="/deletecontacts" method="get">
    <button type="submit">Delete contacts</button>
</form>
Phone Book
<form  action="/index" method="get">
     <input name="filter" type="radio" id="fn" value="firstname" />Show for firstname
     <input name="filter" type="radio" id="ln" value="lastname" />Show for lastname
     <input name="filter" type="radio" id="tn" value="telephone_number" />Show for telephone_number
    <button type="submit"  >Search</button>
</form>
<table id="list" border="1" ><caption>Users data</caption>
<tr>
<th>ID</th>
<th>Firstname</th>
<th>Lastname</th>
<th>Patronymic</th>
<th>Telephone number</th>
<th>Home phone number</th>
<th>Address</th>
<th>E-mail</th>
</tr>
<#list contacts as contact>
<form action="/editcontacts" method="get">
<tr>
    <td><input  name="id" value="${contact.id}"></td>
    <td><input  name="firstname" value="${contact.firstname}"></td>
    <td><input name="lastname" value="${contact.lastname}"> </td>
    <td><input  name="patronymic" value="${contact.patronymic}"></td>
    <td><input  name="telephone_number" value="${contact.telephone_number}"></td>
    <td><input name="home_phone_number" value="${contact.home_phone_number}"> </td>
    <td><input  name="address" value="${contact.address}"></td>
    <td><input  name="e_mail" value="${contact.e_mail}"></td>
    </tr>
</form>
    </#list>
    </table>

    </body>
    </html>
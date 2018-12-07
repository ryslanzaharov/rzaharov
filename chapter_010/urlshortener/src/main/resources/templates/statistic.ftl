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
<form th:action="@{/register}" method="get">
    <div><input type="button" value="Add URL"/></div>
</form>
Statistic
<table border="1" ><caption>Users data</caption>
    <tr>
        <th>ID</th>
        <th>URL</th>
        <th>Short URL</th>
    </tr>
<#list urls as url>
    <tr>
        <td><input  name="url" value="${url.id}"></td>
        <td><input  name="url" value="${url.url}"></td>
        <td><input name="short_url" value="${url.short_url}"> </td>
      </tr>
</#list>
</table>

</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/index" method="get">
    <button type="submit">Back</button>
</form>
<#list contacts as contact>
<form action="/deletecontacts" method="post">
    <tr>
        <td><input type="hidden" name="id" value="${contact.id}"></td>
        <td><input type="text" name="firstname" value="${contact.firstname}"></td>
        <td><input type="text" name="lastname" value="${contact.lastname}"> </td>
        <td><input type="text" name="patronymic" value="${contact.patronymic}"></td>
        <td><input type="text" name="telephone_number" value="${contact.telephone_number}"></td>
        <td><input type="text" name="home_phone_number" value="${contact.home_phone_number}"> </td>
        <td><input type="text" name="address" value="${contact.address}"></td>
        <td><input type="text" name="e_mail" value="${contact.e_mail}"></td>
        <td>
            <#--<button type="submit"  >Remove</button>-->
        <input type="submit"   value="Delete contact"></input>
        </td>
    </tr>
</form>
</#list>
</body>
</html>

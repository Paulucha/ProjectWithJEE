<!doctype html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Find User By ID</title>
</head>
<body>
<#if errorMessage??>
    <span style="color: red">${errorMessage}</span><br/>
</#if>
<h1>
    <strong> ID użytkownika:</strong> ${user.id}<br/>
    <strong> Imię:</strong> ${user.name}<br/>
    <strong> Login: </strong>${user.login}<br/>
    <strong> Wiek: </strong>${user.age}<br/>
    <#--<strong> Płeć: </strong>${gender}-->
    <#--<strong> Puls: </strong>${pulse}-->

</h1>

</body>
</html>
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
    <span style="color: #ff0000;">${errorMessage}</span><br/><br/>
</#if>

<#if user??>
    <ul>
        <li>ID: ${user.id}</li>
        <li>Name: ${user.name}</li>
        <li>Login: ${user.login}</li>
        <li>Age: ${user.age}</li>
        <li>Gender: ${user.gender}</li>
        <li>Pulse: ${pulse}</li>
    </ul>
</#if>

</body>
</html>
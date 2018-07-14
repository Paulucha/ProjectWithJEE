<!doctype html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link type="text/css" rel="stylesheet" href="/styles/styles.css"/>

    <title>Lista Userow</title>
</head>
<body>

<#list users>
<ul>
        <#items as user>
            <#if user.age < 21 >
                <#assign class="blue">
            <#elseif user.age gt 20 && user.age < 41 >
                <#assign class="green">
            <#else>
                <#assign class="red">
            </#if>

            <li><p> <strong> Użytkownik: </strong>  ${user.id}
                                                    ${user.name}
                                                    ${user.login}
                                                    ${user.age}
                                                    <#sep > , </#sep>

            </p></li>
        </#items>
</ul>
</#list>
<#include "footer.ftlh">
</body>
</html>
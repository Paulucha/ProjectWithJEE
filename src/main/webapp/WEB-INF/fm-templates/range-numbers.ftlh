<!DOCTYPE html>
<html>
<head>
    <title>Range Numbers</title>
</head>
<body>

<#if errorMessage??>
<span style="color: #ff0000;">${errorMessage}</span><br/><br/>
<#else>
    <#assign myRange = 0..<(maxNumber / 2.0)>
    <#list myRange as number>
        ${number}<#sep><br/></#sep>
    </#list>
</#if>

</body>
</html>
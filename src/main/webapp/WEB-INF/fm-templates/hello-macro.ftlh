<!DOCTYPE html>
<html>
<head>
    <title>Hello Macro</title>
</head>
<body>

<#import "macros/hello-size-macro.ftlh" as hsm/>

<@hsm.hello person="Jan" size="10px"/><br/>
<@hsm.hello person="Adam" size="13px"/><br/>
<@hsm.hello person="Anna" size="16px"/><br/>

</body>
</html>
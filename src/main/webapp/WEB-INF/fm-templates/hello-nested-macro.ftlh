<!DOCTYPE html>
<html>
<head>
    <title>Hello Nested Macro</title>
</head>
<body>

<#import "macros/hello-size-nestet-greet-macro.ftlh" as hsnm/>

<@hsnm.hello person="Jan" size="10px">Hello </@hsnm.hello><br/>
<@hsnm.hello person="Adam" size="13px">Hi </@hsnm.hello><br/>
<@hsnm.hello person="Anna" size="16px">Good morning </@hsnm.hello><br/>

</body>
</html>
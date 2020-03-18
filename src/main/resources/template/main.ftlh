<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>${name}</h1>
<ul>
    <#list items as item>
        <li>${item_index}</li>

        <#if !item_has_next>
            <li>the fucking end</li>
        </#if>
    </#list>
</ul>
</body>
</html>
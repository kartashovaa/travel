<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Страны</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<#if isModerator>
    <p><a href="/countries/add">Добавить страну</a></p>
</#if>
<h1>Страны</h1>

<ul>
    <#list countries as country>
        <div>
            <h3><a href="/countries/${country.id}">${country.title}</a></h3>

            <p>${country.description}</p>
        </div>
    </#list>
</ul>
</BODY>

</HTML>
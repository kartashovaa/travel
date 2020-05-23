<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Страны</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h1>Страны</h1>

<ul>
    <#list countries as country>
        <div>
            <a href="/countries/${country.id}">${country.title}</a>

            <p>${country.description}</p>
        </div>
    </#list>
</ul>

<p><a href="/countries/add">Добавить страну</a></p>
</BODY>

</HTML>
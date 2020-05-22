<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="STYLESHEET.css">
    <title>Страны</title>
</HEAD>
<BODY>
<h1>Страны</h1>

<ul>
    <#list countries as country>
        <div>
            <a href="/countries/${country.id}">${country.title}</a>

            <p>${country.description}</p>

            <p>Города:</p>
            <ul>
                <li><a>!Название города!</a></li>
            </ul>
        </div>
    </#list>
</ul>

<p><a href="/addCountry">Добавить страну</a></p>
</BODY>

</HTML>
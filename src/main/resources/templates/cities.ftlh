<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Города</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>

<#if isModerator>
    <p><a href="/cities/add">Добавить город</a></p>
</#if>

<h1>Города:</h1>

<ul>
    <#list cities as city>
        <div>
            <h3><a href="/cities/${city.id}">${city.title}</a></h3>
            <p><a href="/countries/${city.country.id}">Страна: ${city.country.title}</a></p>
            <p>Список развлечений:</p>
            <ul>
                <#list city.entertainments as entertainment>
                    ${entertainment.title},
                </#list>
            </ul>
        </div>
    </#list>
</ul>
</BODY>

</HTML>
<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="STYLESHEET.css">
    <title>Города</title>
</HEAD>
<BODY>
<h1>Города:</h1>

<ul>
    <#list cities as city>
        <div>
            <a href="/countries/${city.country.id}">${city.country.title}</a>
            <a href="/cities/${city.id}">${city.title}</a>
            <p>Список развлечений:</p>
            <ul>
                <#list city.entertainments as entertainment>
                    ${entertainment.title},
                </#list>
            </ul>
            <p>Гостиницы:</p>
            <ul>
                <li>!Название гостиницы!</li>
            </ul>
        </div>
    </#list>
</ul>

<p><a href="/addCity">Добавить город</a></p>

</BODY>

</HTML>
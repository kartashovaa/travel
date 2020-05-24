<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${country.title}</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h2>${country.title}</h2>

<p>${country.description}</p>

<a>Города:</a>
<ul>
    <#list cities as city>
        <li><a href="/cities/${city.id}">${city.title}</a></li>
    </#list>
</ul>

<p>Доступные курорты в эту страну:</p>
<ul>
    <#list resorts as resort>
        <li>
            <ul>
                <li><h3><a href="/resorts/${resort.id}">${resort.title}</a></h3></li>
                <li>${resort.description}</li>
                <li>Теги:
                    <ul>
                        <#list resort.tags as tag>
                            <li>${tag.title}</li>
                        </#list>
                    </ul>
                </li>
                <li><a href="/hotels/${resort.hotel.id}">${resort.hotel.title}</a></li>
                <li>Дата начала курорта: ${resort.startDate?date}</li>
                <li>Дата окончания курорта: ${resort.endDate?date}</li>
            </ul>
        </li>
    </#list>
</ul>

</BODY>

</HTML>
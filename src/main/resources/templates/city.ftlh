<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${city.title}</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h2>${city.title}</h2>

<p>Список развлечений:</p>
<ul>
    <#list city.entertainments as entertainment>
        <li>${entertainment.title}</li>
    </#list>
</ul>
<p>Гостиницы:</p>
<ul>
    <#list hotels as hotel>
        <li>${hotel.title}</li>
    </#list>
</ul>

<p>дата начала курорта</p>
<p><input type="date" min="2020-05-16" max="2025-12-31" value="2020-05-16"></p>
<!--в min и value надо указать сегодняшнюю дату-->

<p>Доступные курорты в этот город:</p>
<ul>
    <#list resorts as resort>
        <li><a>
                <ul>
                    <li>${resort.title}</li>
                    <li>${resort.description}</li>
                    <li>Теги:
                        <ul>
                            <#list resort.tags as tag>
                                <li>${tag.title}</li>
                            </#list>
                        </ul>
                    </li>
                    <li><a>${resort.hotel.title}</a></li>
                    <li>Дата начала курорта: ${resort.startDate?date}</li>
                    <li>Дата окончания курорта: ${resort.endDate?date}</li>
                </ul>
            </a></li>
    </#list>
</ul>

</BODY>

</HTML>
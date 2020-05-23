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
    <li><a>!Название города!</a></li>
</ul>

<p>дата начала курорта</p>
<p><input type="date" min="2020-05-16" max="2025-12-31" value="2020-05-16"></p>
<!--в min и value надо указать сегодняшнюю дату-->

<p>Доступные курорты в эту страну:</p>
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
                    <li><a>${resort.hotel}</a></li>
                    <li>Дата начала курорта: ${resort.startDate?date}</li>
                    <li>Дата окончания курорта: ${resort.endDate?date}</li>
                </ul>
            </a></li>
    </#list>
</ul>

</BODY>

</HTML>
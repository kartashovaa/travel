<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${resort.title}</title>
</HEAD>
<BODY>

<p>${resort.title}</p>

<p><a>Гостиница ${resort.hotel.title} ${resort.hotel.stars} звезд</a></p>

<p><a>${resort.hotel.city.country.title}</a></p>
<p><a>${resort.hotel.city.title}</a></p>
<p>${resort.description}</p>
<p>${resort.durationInDays}</p>
<p>Стоимость: ${resort.cost}</p>
<a>Теги:</a>
<ul>
    <#list resort.tags as tag>
        <li><a>${tag}</a></li>
    </#list >
</ul>

<p>Дата отправления: ${resort.startDate?date}</p>
<p>Дата прибытия: ${resort.endDate?string('dd.MM.yyyy')}</p>

<button class="BuyingBtn">Купить билеты</button>
</BODY>

</HTML>
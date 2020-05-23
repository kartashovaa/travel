<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${resort.title}</title>
</HEAD>
<BODY>
<h3><a href="/main">Вернуться на главную</a></h3>
<h2>${resort.title}</h2>

<p><a href="/hotels/${resort.hotel.id}">Гостиница ${resort.hotel.title} ${resort.hotel.stars} звезд</a></p>

<p><a href="/countries/${resort.hotel.city.country.id}">Страна: ${resort.hotel.city.country.title}</a></p>
<p><a href="/cities/${resort.arrivalCity.id}">Город: ${resort.arrivalCity.title}</a></p>
<p>Описание курорта: ${resort.description}</p>
<p>Длительность (в днях): ${resort.durationInDays} </p>
<p>Стоимость: ${resort.cost}</p>
<p>Теги:</p>
<ul>
    <#list resort.tags as tag>
        <li>${tag.title}</li>
    </#list >
</ul>

<p>Дата отправления: ${resort.startDate?string('dd.MM.yyyy')}</p>
<p>Дата прибытия: ${resort.endDate?string('dd.MM.yyyy')}</p>

<!--<button class="BuyingBtn" >Купить билеты</button>-->
<a href="/buyingResort/${resort.id}">Купить билеты</a>
</BODY>

</HTML>
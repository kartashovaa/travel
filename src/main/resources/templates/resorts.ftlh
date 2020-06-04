<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Курорты</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<#if isModerator>
    <p><a href="/resorts/add">Добавить курорт</a></p>
</#if>

<form method="get" action="/resorts/search">
    <input type="text" name="search">
    <button type="submit">Поиск по названию</button>
</form>

<h1>Курорты</h1>
<ul>
    <#list resorts as resort>
        <div>
            <h3><a href="/resorts/${resort.id}">${resort.title}</a></h3>

            <p><a href="/hotels/${resort.hotel.id}">Гостиница: ${resort.hotel.title} ${resort.hotel.stars} звезд</a></p>

            <p><a href="/countries/${resort.arrivalCity.country.id}">Страна: ${resort.arrivalCity.country.title}</a></p>
            <p><a href="/cities/${resort.arrivalCity.id}">Город: ${resort.arrivalCity.title}</a></p>
            <p>Описание: ${resort.description}</p>
            <p>Длительность курорта: ${resort.durationInDays}</p>
            <p>Стоимость: ${resort.cost}</p>
            <p>Теги:</p>
            <ul>
                <#list resort.tags as tag>${tag.title}, </#list>
            </ul>

            <p>Дата отправления: ${resort.startDate?string('dd.MM.yyyy')}</p>
            <p>Дата прибытия: ${resort.endDate?string('dd.MM.yyyy')}</p>

            <a href="/resorts/${resort.id}/buy">Купить билеты</a>
        </div>
    </#list>
</ul>

</BODY>

</HTML>
<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Курорты</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h1>Курорты</h1>

<ul>
    <#list resorts as resort>
        <div>
            <p><a href="/resorts/${resort.id}">${resort.title}</a></p>

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

<p><a href="/resorts/add">Добавить курорт</a></p>
</BODY>

</HTML>
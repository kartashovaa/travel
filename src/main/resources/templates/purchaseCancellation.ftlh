<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${resort.title}</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<#if isSuccessful??>
    <h3 style="color: #00ff00">Покупка успешно отменена</h3>
</#if>

<#if errorMessage??>
    <h3 style="color: #ff0000">${errorMessage}</h3>
</#if>
<#if resort.purchased>
    <h1>Отмена покупки:</h1>
    <h2>Данные о курорте:</h2>
    <h3>${resort.title}</h3>

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
    <#if !errorMessage??>
        <div>
            <form method="post" action="/resorts/${transaction.id}/cancellation">
                <button type="submit">Отменить покупку</button>
            </form>
        </div>
    </#if>
</#if >
</BODY>

</HTML>
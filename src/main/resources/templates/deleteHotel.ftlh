<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${hotel.title}</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h3><a href="/hotels">Вернуться к списку отелей</a></h3>
<#if isSuccessful??>
    <h3 style="color: #00ff00">Отель успешно удален</h3>
</#if>

<#if errorMessage??>
    <h3 style="color: #ff0000">${errorMessage}</h3>
</#if>
<#if !isSuccessful?? && !errorMessage??>
    <h1>Удаление гостиницы</h1>
    <h2 style="color: red ; margin-bottom: 20px">Внимание! При удалении отеля удалятся также все курорты в этот
        отель</h2>
    <h2>${hotel.title}</h2>

    <p><a href="/cities/${hotel.city.id}">Город: ${hotel.city.title}</a></p>
    <p>Адрес гостиницы: ${hotel.address}</p>
    <p>Количество звезд: ${hotel.stars}<p>
    <p>Номера в гостинице:</p>

    <#list hotel.rooms as room>
        <div>
            <a href="/hotels/${hotel.id}/rooms/${room.id}">
                <ul>
                    <li>Количество спальных мест: ${room.numberOfSleepingPlaces}</li>
                    <li>Удобства:
                        <ul>
                            <#list room.facilities as facility>
                                <li>${facility.title}</li>
                            </#list>
                        </ul>
                    </li>
                    <li>Стоимость: ${room.cost}</li>
                </ul>
            </a>
        </div>
    </#list>
    <#list resorts as resort>
        <div>
            <a href="/resorts/${resort.id}">${resort.title}</a>
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
        </div>
    </#list>

    <#if !errorMessage??>
        <form method="post" action="/hotels/${hotel.id}/delete">
            <p>
                <button type="submit">Удалить отель</button>
            </p>
        </form>
    </#if>
</#if>

</BODY>

</HTML>
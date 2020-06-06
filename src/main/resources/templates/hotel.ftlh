<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${hotel.title}</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h3><a href="/hotels">Вернуться к списку отелей</a></h3>
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
        <!--<a href="/buyingHotelRoom/{room.id}">Забронировать номер</a>-->
    </div>
</#list>

<#if isModerator>
    <p><a href="/hotels/${hotel.id}/delete">
            <button type="submit">Удалить отель</button>
        </a></p>
</#if>
</BODY>

</HTML>
<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>!Название гостиницы!</title>
</HEAD>
<BODY>
<h3><a href="/main">Вернуться на главную</a></h3>
<p>!Название гостиницы!</p>
<ul>
    <li>Количество спальных мест: ${hotelRoom.numberOfSleepingPlaces}</li>
    <li>Удобства:
        <ul>
            <#list hotelRoom.facilities as facilities>
                <li>${facilities}</li>
            </#list>
        </ul>
    </li>
    <li>Стоимость: ${hotelRoom.cost}</li>
</ul>

<a href="/buyingHotelRoom/${hotelRoom.id}">Забронировать номер</a>

</BODY>

</HTML>
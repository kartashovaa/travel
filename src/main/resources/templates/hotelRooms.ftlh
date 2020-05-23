<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Номера в гостинице</title>
</HEAD>
<BODY>
<#if isModerator>
    <p><a href="/addHotelRoom">Добавить номер</a></p>
</#if>
<h3><a href="/">Вернуться на главную</a></h3>
<h2>Номера в гостинице</h2>
<ul>
    <#list hotelRooms as hotelRoom>
        <div>
            <li>Количество спальных мест: ${hotelRoom.numberOfSleepingPlaces}</li>
            <li>Удобства:
                <ul>
                    <#list hotelRoom.facilities as facility>
                        <li>${facility.title}</li>
                    </#list>
                </ul>
            </li>
            <li>Стоимость: ${hotelRoom.cost}</li>
        </div>
    </#list>
</ul>
</BODY>

</HTML>
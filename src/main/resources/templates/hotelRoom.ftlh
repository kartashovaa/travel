<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>!Название гостиницы!</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<ul>
    <li>Количество спальных мест: ${hotelRoom.numberOfSleepingPlaces}</li>
    <li>Удобства:
        <ul>
            <#list hotelRoom.facilities as facility>
                <li>${facility.title}</li>
            </#list>
        </ul>
    </li>
    <li>Стоимость: ${hotelRoom.cost}</li>
</ul>

</BODY>

</HTML>
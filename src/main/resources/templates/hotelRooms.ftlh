<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="STYLESHEET.CSS">
    <title>Номера в !Название гостиницы!</title>
</HEAD>
<BODY>

<h2>Номера в !Название гостиницы!</h2>
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
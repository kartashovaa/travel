<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>!Название гостиницы!</title>
</HEAD>
<BODY>

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

<!--Как вариант, здесь можно сделать галерею с фотографиями номеров-->

</BODY>

</HTML>
<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="STYLESHEET.css">
    <title>Гостиницы</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<p>Гостиницы</p>

<ul>
    <#list hotels as hotel>
        <div>
            <p><a href="/hotels/${hotel.id}">Гостиница ${hotel.title}</a></p>

            <p><a href="/cities/${hotel.city.id}">Город: ${hotel.city.title}</a></p>
            <p>Адрес гостиницы: ${hotel.address}</p>
            <p>Количество звезд: ${hotel.stars}<p>
            <p>Количество номеров: ${hotel.getRoomsCount()}</p>
            <p>Средняя стоимость номера: ${hotel.getAvgRoomPrice()}</p>
        </div>
    </#list>
</ul>

<p><a href="/addHotel">Добавить отели</a></p>

</BODY>

</HTML>
<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Гостиницы</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<#if isModerator>
    <p><a href="/hotels/add">Добавить отель</a></p>
    <p><a href="/hotelRooms/add">Добавить номер</a></p>
</#if>

<form method="get" action="/hotels/search">
    <input type="text" name="search">
    <button type="submit">Поиск по названию</button>
</form>

<h1>Гостиницы</h1>
<ul>
    <#list hotels as hotel>
        <div>
            <h3><a href="/hotels/${hotel.id}">Гостиница ${hotel.title}</a></h3>

            <p><a href="/cities/${hotel.city.id}">Город: ${hotel.city.title}</a></p>
            <p>Адрес гостиницы: ${hotel.address}</p>
            <p>Количество звезд: ${hotel.stars}<p>
            <p>Количество номеров: ${hotel.getRoomsCount()}</p>
            <p>Средняя стоимость номера: ${hotel.getAvgRoomPrice()}</p>
        </div>
    </#list>
</ul>


</BODY>

</HTML>
<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${hotel.title}</title>
</HEAD>
<BODY>

<h2>${hotel.title}</h2>

<p><a href="/cities/${hotel.city.id}">Город: ${hotel.city.title}</a></p>
<p>Адрес гостиницы: ${hotel.address}</p>
<p>Количество звезд: ${hotel.stars}<p>
    <!--Как вариант, вместо этого можно подставлять одну из картинок со звездами в зависимости от их количества-->

<p>Номера в гостинице:</p>
<ul>
    <#list hotel.rooms as room>
        <div>
            <li>
                <a href="/hotels/hotelRooms/${room.id}">
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
            </li>
        </div>
    </#list>
</ul>

</BODY>

</HTML>
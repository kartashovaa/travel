<HTML lang="en_EN">
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${hotel.title}</title>
</HEAD>
<BODY>

<p>${hotel.title}</p>

<p><a href="/cities/${hotel.city.id}">${hotel.city.title}</a></p>
<p>Адрес гостиницы: ${hotel.address}</p>
<p>Количество звезд: ${hotel.stars}<p>
    <!--Как вариант, вместо этого можно подставлять одну из картинок со звездами в зависимости от их количества-->

<p>Номера в гостинице:</p>
<ul>
    <#list hotel.rooms as room>
        <li><a>
                <ul>
                    <li>Количество спальных мест: ${room.numberOfSleepingPlaces}</li>
                    <#--					<li>!Описание курорта!</li>-->
                    <li>Удобства:
                        <ul>
                            <#list room.facilities as facility>
                                <li>${facility}</li>
                            </#list>
                        </ul>
                    </li>
                    <li>Стоимость: ${room.cost}</li>
                </ul>
            </a></li>
    </#list>
</ul>

</BODY>

</HTML>
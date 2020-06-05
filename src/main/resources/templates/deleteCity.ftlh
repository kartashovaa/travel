<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${city.title}</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h3><a href="/cities">Вернуться к списку городов</a></h3>
<#if isSuccessful??>
    <h3 style="color: #00ff00">Город успешно удален</h3>
</#if>

<#if errorMessage??>
    <h3 style="color: #ff0000">${errorMessage}</h3>
</#if>
<#if !isSuccessful??>
    <h1>Удаление города</h1>
    <h2 style="color: red ; margin-bottom: 20px">Внимание! При удалении города удалятся также все гостиницы в этом
        городе и курорты в этот город и из
        этого города</h2>
    <h2>${city.title}</h2>

    <p>Список развлечений:</p>
    <ul>
        <#list city.entertainments as entertainment>
            <li>${entertainment.title}</li>
        </#list>
    </ul>
    <p>Гостиницы:</p>
    <ul>
        <#list hotels as hotel>
            <li><a href="/hotels/${hotel.id}">${hotel.title}</a></li>
        </#list>
    </ul>

    <p>Доступные курорты в этот город:</p>
    <ul>
        <#list resorts as resort>
            <li>
                <ul>
                    <li><h3><a href="/resorts/${resort.id}">${resort.title}</a></h3></li>
                    <li>${resort.description}</li>
                    <li>Теги:
                        <ul>
                            <#list resort.tags as tag>
                                <li>${tag.title}</li>
                            </#list>
                        </ul>
                    </li>
                    <li><a href="/hotels/${resort.hotel.id}">${resort.hotel.title}</a></li>
                    <li>Дата начала курорта: ${resort.startDate?date}</li>
                    <li>Дата окончания курорта: ${resort.endDate?date}</li>
                </ul>
            </li>
        </#list>
    </ul>

    <#if !errorMessage??>
        <form method="post" action="/cities/${city.id}/delete">
            <p>
                <button type="submit">Удалить город</button>
            </p>
        </form>
    </#if>
</#if>

</BODY>

</HTML>
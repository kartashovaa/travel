<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${country.title}</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h3><a href="/countries">Вернуться к списку стран</a></h3>
<#if isSuccessful??>
    <h3 style="color: #00ff00">Страна успешно удалена</h3>
</#if>

<#if errorMessage??>
    <h3 style="color: #ff0000">${errorMessage}</h3>
</#if>
<#if !isSuccessful??>
    <h2 style="color: red ; margin-bottom: 20px">Внимание! При удалении страны удалятся также все города и гостиницы в
        этой стране, а также все курорты
        в эту страну и из этой страны</h2>
    <h2>${country.title}</h2>
    <p>${country.description}</p>

    <a>Города:</a>
    <ul>
        <#list cities as city>
            <li><a href="/cities/${city.id}">${city.title}</a></li>
        </#list>
    </ul>

    <p>Доступные курорты в эту страну:</p>
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
        <form method="post" action="/countries/${country.id}/delete">
            <p>
                <button type="submit">Удалить страну</button>
            </p>
        </form>
    </#if>
</#if>


</BODY>

</HTML>
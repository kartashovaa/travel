<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Результаты поиска</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<div>
    <#if isUserAuthenticated>
        <a href="/logout" class="ExitBtn">Выйти</a>
        <h3><a href="/profile">Личный кабинет</a></h3>
    <#else>
        <p><a href="/signin">Войти</a></p>
        <p><a href="/signup">Зарегистрироваться</a></p>
    </#if>
</div>

<div>
    <input type="text"/>
    <input type="button" value="поиск">
</div>

<div>
    <h3>Подобрать курорт:</h3>
    <form method="post" action="/search">
        <ul class="searchPanelList">
            <li>
                <p><span>Количество человек</span>
                    <input type="number" size="2" name="personCount" min="1" max="20"
                           value="#{personCount!1}"></p>
            </li>
            <li>
                <p><span>Минимальная стоимость</span>
                    <input type="number" size="5" name="minCost" min="0" max="10000000"
                           value="#{minCost!0}"></p>
            </li>
            <li>
                <p><span>Максимальная стоимость</span>
                    <input type="number" size="5" name="maxCost" min="0" max="10000000"
                           value="#{maxCost!9999999}"></p>
            </li>
            <li>
                <p><span>Минимальная длительность (в днях)</span>
                    <input type="number" size="3" name="minDuration" min="0" max="100"
                           value="#{minDuration!1}"></p>
            </li>
            <li>
                <p><span>Максимальная длительность (в днях)</span>
                    <input type="number" size="3" name="maxDuration" min="0" max="100"
                           value="#{maxDuration!100}"></p>
            </li>
            <li>
                <p><span>дата начала курорта</span>
                    <input type="date" name="startDate" min="2020-05-16" max="2025-12-31"
                           value="${startDate?string("yyyy-MM-dd")!"2020-05-16"}"></p>
            </li>
            <li>
                <p><span>Сортировать по:</span>
                    <select name="sortType">
                        <option value="COST_UP" name="COST_UP"
                                <#if sortType?? && sortType=="COST_UP">selected</#if>>Возрастанию цены
                        </option>
                        <option value="COST_DOWN" name="COST_DOWN"
                                <#if sortType?? && sortType=="COST_DOWN">selected</#if>>Уменьшению цены
                        </option>
                        <option value="DURATION_UP" name="DURATION_UP"
                                <#if sortType?? && sortType=="DURATION_UP">selected</#if>>Возрастанию длительности
                        </option>
                        <option value="DURATION_DOWN" name="DURATION_DOWN"
                                <#if sortType?? && sortType=="DURATION_DOWN">selected</#if>>Уменьшению длительности
                        </option>
                    </select></p>
            </li>
            <li>
                <!--в min и value надо указать сегодняшнюю дату-->
            </li>
            <li>
                <p><span>Необходимые теги</span>
                    <#list tags as tag>
                        <input type="checkbox" name="tag${tag.data.id}"
                               <#if tag.isSelected()>checked</#if>>${tag.data.title}</input>
                    </#list>
                </p>
            </li>

        </ul>
        <ul class="searchPanelList">
            <li>
                <p><span>Допустимые страны</span>
                    <#list countries as country>
                        <input type="checkbox" name="country${country.data.id}"
                               <#if country.selected>checked</#if> >${country.data.title}</input>
                    </#list>
                </p>
            </li>
            <li>
                <p><span>Допустимые города</span>
                    <#list cities as city>
                        <input type="checkbox" name="city${city.data.id}"
                               <#if city.selected>checked</#if>>${city.data.title}</input>
                    </#list>
                </p>
            </li>
            <li>
                <p><span>Необходимые развлечения</span>
                    <#list entertainments as entertainment>
                        <input type="checkbox"
                               name="entertainment${entertainment.data.id}"
                               <#if entertainment.selected>checked</#if>>${entertainment.data.title}</input>
                    </#list>
                </p>
            </li>
            <li>
                <p><span>Минимальное количество звезд в гостинице</span>
                    <input type="number" size="3" name="minStar" min="0" max="5"
                           value="#{minStar!1}"></p>
            </li>
            <li>
                <p><span>Необходимые удобства</span>
                    <#list facilities as facility>
                        <input type="checkbox" name="facility${facility.data.id}"
                               <#if facility.selected>checked</#if>>${facility.data.title}</input>
                    </#list>
                </p>
            </li>
            <li>
                <p>
                    <button type="submit">найти курорт</button>
                </p>
            </li>
        </ul>
    </form>
</div>

<div>
    <p>Подходящие курорты:</p>
    <ul>
        <#list resorts as resort>
            <p>${resort.title}</p>

            <p><a href="/hotels/${resort.hotel.id}">Гостиница ${resort.hotel.title} ${resort.hotel.stars} звезд</a></p>

            <p><a href="/countries/${resort.arrivalCity.id}">Страна: ${resort.arrivalCity.country.title}</a></p>
            <p><a href="/cities/${resort.arrivalCity.id}">Город: ${resort.arrivalCity.title}</a></p>
            <p>Описание курорта: ${resort.description}</p>
            <p>Длительность курорта: ${resort.durationInDays}</p>
            <p>Стоимость: ${resort.cost}</p>
            <a>Теги:</a>
            <ul>
                <#list resort.tags as tag><a>${tag.title}</a></#list>
            </ul>

            <p>Дата отправления: ${resort.startDate?string('dd.MM.yyyy')}</p>
            <p>Дата прибытия: ${resort.endDate?string('dd.MM.yyyy')}</p>

            <a href="/resorts/${resort.id}/buy">Купить билеты</a>
        </#list >
    </ul>
</div>

</BODY>

</HTML>
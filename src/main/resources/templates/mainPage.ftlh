<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Главная страница</title>
</HEAD>
<BODY>

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
    <input type="text">
    <input type="button" value="поиск">
</div>

<div>
    <h3><a href="/resorts">Посмотреть все курорты</a></h3>
    <h3><a href="/countries">Посмотреть все страны</a></h3>
    <h3><a href="/cities">Посмотреть все города</a></h3>
    <h3><a href="/hotels">Посмотреть все отели</a></h3>
</div>

<div>
    <h3>Подобрать курорт:</h3>
    <form method="post" action="/searchResult">
        <ul class="searchPanelList">
            <li>
                <p><span>Количество человек</span>
                    <input type="number" size="2" name="personCount" min="1" max="20" value="1"></p>
            </li>
            <li>
                <p><span>Минимальная стоимость</span>
                    <input type="number" size="5" name="minCost" min="0" max="10000000" value="0"></p>
            </li>
            <li>
                <p><span>Максимальная стоимость</span>
                    <input type="number" size="5" name="maxCost" min="0" max="10000000" value="10000000"></p>
            </li>
            <li>
                <p><span>Минимальная длительность (в днях)</span>
                    <input type="number" size="3" name="minDuration" min="0" max="100" value="0"></p>
            </li>
            <li>
                <p><span>Максимальная длительность (в днях)</span>
                    <input type="number" size="3" name="maxDuration" min="0" max="100" value="100"></p>
            </li>
            <li>
                <p>
                    <span>дата начала курорта</span>
                    <input type="date" name="startDate" min="${today?date}" max="2025-12-31" value="${today?date}">
                </p>
            </li>
            <li>
                <p><span>Сортировать по:</span>
                    <select name="sortType">
                        <option value="COST_UP">Возрастанию цены</option>
                        <option value="COST_DOWN">Уменьшению цены</option>
                        <option value="DURATION_UP">Возрастанию длительности</option>
                        <option value="DURATION_DOWN">Уменьшению длительности</option>
                    </select></p>
            </li>
            <li>
                <!--  <p><input type="button" value="расширенный подбор"></p>-->
            </li>
            <li>
                <p><span>Необходимые теги</span>
                    <#list tags as tag>
                        <input type="checkbox" name="tag${tag.id}">${tag.title}</input>
                    </#list>
                </p>
            </li>

        </ul>
        <ul class="searchPanelList">
            <li>
                <p><span>Допустимые страны</span>
                    <#list countries as country>
                        <input type="checkbox" name="country${country.id}" checked>${country.title}</input>
                    </#list>
                </p>
            </li>
            <li>
                <p><span>Допустимые города</span>
                    <#list cities as city>
                        <input type="checkbox" name="city${city.id}" checked>${city.title}</input>
                    </#list>
                </p>
            </li>
            <li>
                <p><span>Необходимые развлечения</span>
                    <#list entertainments as entertainment>
                        <input type="checkbox"
                               name="entertainment${entertainment.id}">${entertainment.title}</input>
                    </#list>
                </p>
            </li>
            <li>
                <p><span>Минимальное количество звезд в гостинице</span>
                    <input type="number" size="3" name="minStar" min="0" max="5" value="0"></p>
            </li>
            <li>
                <p><span>Необходимые удобства</span>
                    <#list facilities as facility>
                        <input type="checkbox" name="facility${facility.id}">${facility.title}</input>
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

</BODY>

</HTML>
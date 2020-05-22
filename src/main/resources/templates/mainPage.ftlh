<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Главная страница</title>
</HEAD>
<BODY>

<div>
    <!--Добавить условие если пользователь не вошел, то-->
    <p><a href="/logIn">Войти</a></p>
    <p><a href="/registration">Зарегистрироваться</a></p>
    <!--Иначе:-->
    <button class="ExitBtn">Выйти</button><!--Добавить функционал-->
    <h3><a href="/personalAccount">Личный кабинет</a></h3>
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
                <p><span>дата начала курорта</span>
                    <input type="date" name="startDate" min="2020-05-16" max="2025-12-31" value="2020-05-16"></p>
            </li>
            <li>
                <p><span>Сортировать по:</span>
                    <select name="sortType">
                        <option value="COST_UP" name="COST_UP">Возрастанию цены</option>
                        <option value="COST_DOWN" name="COST_DOWN">Уменьшению цены</option>
                        <option value="DURATION_UP" name="DURATION_UP">Возрастанию длительности</option>
                        <option value="DURATION_DOWN" name="DURATION_DOWN">Уменьшению длительности</option>
                    </select></p>
            </li>
            <li>
                <!--в min и value надо указать сегодняшнюю дату-->
                <!--  <p><input type="button" value="расширенный подбор"></p>-->
            </li>
            <li>
                <p><span>Необходимые теги</span>
                    <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми тегами-->
                    <#list tags as tag>
                        <input type="checkbox" name="tag${tag.id}">${tag.title}</input>
                    </#list>
                </p>
            </li>

        </ul>
        <ul class="searchPanelList">
            <li>
                <p><span>Допустимые страны</span>
                    <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми странами-->
                    <#list countries as country>
                        <input type="checkbox" name="country${country.id}" checked>${country.title}</input>
                    </#list>
                    <!--<input type="button" value="снять выбор со всех стран">-->
                </p>
            </li>
            <li>
                <p><span>Допустимые города</span>
                    <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми городами-->
                    <!--Города должны быть сгруппированы по странам и перед ними должно быть название страны-->
                    <#list cities as city>
                        <input type="checkbox" name="city${city.id}" checked>${city.title}</input>
                    </#list>
                    <!-- <input type="button" value="снять выбор со всех городов">-->
                </p>
            </li>
            <li>
                <p><span>Необходимые развлечения</span>
                    <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми развлечениями-->
                    <#list entertainments as entertainment>
                        <input type="checkbox"
                               name="entertainment${entertainment.name()}">${entertainment.title}</input>
                    </#list>
                </p>
            </li>
            <li>
                <p><span>Минимальное количество звезд в гостинице</span>
                    <input type="number" size="3" name="minStar" min="0" max="5" value="0"></p>
            </li>
            <li>
                <p><span>Необходимые удобства</span>
                    <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми удобствами-->
                    <#list facilities as facility>
                        <input type="checkbox" name="facility${facility.name()}">${facility.title}</input>
                    </#list>
                </p>
            </li>
            <li>
                <p>
                    <button type="submit">найти курорт</button>
                </p>
            </li>
            <!--<li>
                <p>
                    <input type="button" value="быстрый подбор">
                    <input type="button" value="найти курорт">
                </p>
            </li>-->
        </ul>
    </form>
</div>

</BODY>

</HTML>
<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Главная страница</title>
</HEAD>
<BODY>

<div>
    <button class="loginBtn">Войти</button>
    <button class="RegBtn">Зарегистрироваться</button>
    <button class="ExitBtn">Выйти</button>
</div>

<div>
    <input type="text"></input>
    <input type="button" value="поиск">
</div>

<div>
    <form method="post" action="/main">
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
                        <option value="costUp">Возрастанию цены</option>
                        <option value="costDown">Уменьшению цены</option>
                        <option value="durationUp">Возрастанию длительности</option>
                        <option value="durationDown">Уменьшению длительности</option>
                    </select></p>
            </li>
            <li>
                <p>
                    <button type="submit">отправить</button>
                </p>
            </li>
        </ul>
    </form>
    <ul class="searchPanelList">
        <li>
            <!--в min и value надо указать сегодняшнюю дату-->
            <p><input type="button" value="расширенный подбор"></p>
        </li>
        <li>
            <p><span>Необходимые теги</span>
                <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми тегами-->
                <#list tags as tag>
                    <input type="checkbox">${tag.title}</input>
                </#list>
            </p>
        </li>
        <li>
            <p><span>Допустимые страны</span>
                <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми странами-->
                <#list countries as country>
                    <input type="checkbox" checked>${country.title}</input>
                </#list>
                <input type="button" value="снять выбор со всех стран">
            </p><!--Кнопка есть, функционала нет... Но это пока-->
        </li>
        <li>
            <p><span>Допустимые города</span>
                <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми городами-->
                <!--Города должны быть сгруппированы по странам и перед ними должно быть название страны-->
                <!--<input type="checkbox" checked>Москва</input>
                <input type="checkbox" checked>Санкт-Петербург</input>-->
                <#list cities as city>
                    <input type="checkbox" checked>${city.title}</input>
                </#list>
                <input type="button" value="снять выбор со всех городов"></p>
        </li>
        <li>
            <!--Кнопка есть, функционала нет... И это навсегда-->
            <p><span>еобходимые развлечения</span>
                <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми развлечениями-->
                <#list entertainments as entertainment>
                    <input type="checkbox">${entertainment}</input>
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
                    <input type="checkbox">${facility}</input>
                </#list>
            </p>
        </li>
        <li>
            <p>
                <input type="button" value="быстрый подбор">
                <input type="button" value="найти курорт">
            </p>
        </li>
    </ul>
</div>

</BODY>

</HTML>
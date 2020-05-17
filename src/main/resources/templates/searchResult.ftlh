<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="STYLESHEET.css">
    <title>Результаты поиска</title>
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
    <p>Количество человек</p>
    <p><input type="number" size="2" name="edtPersonCount" min="1" max="20" value="1"></p>
    <p>Минимальная стоимость</p>
    <p><input type="number" size="5" name="edtMinCost" min="0" max="10000000" value="0"></p>
    <p>Максимальная стоимость</p>
    <p><input type="number" size="5" name="edtMaxCost" min="0" max="10000000" value="10000000"></p>
    <p>Минимальная длительность (в днях)</p>
    <p><input type="number" size="3" name="edtMinCost" min="0" max="100" value="0"></p>
    <p>Максимальная длительность (в днях)</p>
    <p><input type="number" size="3" name="edtMaxCost" min="0" max="100" value="100"></p>
    <p>дата начала курорта</p>
    <p><input type="date" min="2020-05-16" max="2025-12-31" value="2020-05-16"></p>
    <!--в min и value надо указать сегодняшнюю дату-->
    <p>Необходимые теги</p>
    <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми тегами-->
    <p>
        <input type="checkbox">Море</input>
        <input type="checkbox">Горе</input>
    </p>
    <p>Допустимые страны</p>
    <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми странами-->
    <p>
        <input type="checkbox" checked>Россия</input>
        <input type="checkbox" checked>Германия</input>
    </p>
    <p><input type="button" value="снять выбор со всех стран"></p><!--Кнопка есть, функционала нет... Но это пока-->

    <p>Допустимые города</p>
    <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми городами-->
    <!--Города должны быть сгруппированы по странам и перед ними должно быть название страны-->
    <p>
        <input type="checkbox" checked>Москва</input>
        <input type="checkbox" checked>Санкт-Петербург</input>
    </p>
    <p><input type="button" value="снять выбор со всех городов"></p><!--Кнопка есть, функционала нет... Но это пока-->
    <p>Необходимые развлечения</p>
    <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми развлечениями-->
    <p>
        <input type="checkbox">Рыбалка</input>
        <input type="checkbox">Рестораны</input>
    </p>
    <p>Минимальное количество звезд в гостинице</p>
    <p><input type="number" size="3" name="edtMinCost" min="0" max="5" value="0"></p>
    <p>Необходимые удобства</p>
    <!--Я пока хз как это правильно сделать, но в идеале это должен быть выпадающий список со всеми удобствами-->
    <p>
        <input type="checkbox">Душ в номере</input>
        <input type="checkbox">Бассейн в отеле</input>
    </p>
    <input type="button" value="найти курорт">
</div>

<div>
    <p>Подходящие курорты:</p>
    <ul>
        <li>
            <p>!Название курорта!</p>

            <p><a>Гостиница !Название гостиницы! !количество звезд! звезд</a></p>

            <p><a>Страна</a></p>
            <p><a>Город</a></p>
            <p>Описание</p>
            <p>Длительность курорта</p>
            <p>Стоимость: !Стоимость!</p>
            <a>Теги:</a>
            <ul>
                <li><a>!Тег!</a></li>
            </ul>

            <p>Дата отправления: !Дата отправления!</p>
            <p>Дата прибытия: !Дата прибытия!</p>

            <button class="BuyingBtn">Купить билеты</button>
        </li>
    </ul>
</div>

</BODY>

</HTML>
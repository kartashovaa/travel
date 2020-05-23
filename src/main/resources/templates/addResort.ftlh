<HTML lang="en">
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Добавление нового курорта</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h3>Добавление нового курорта</h3>
<form method="post">
    <p>Введите данные о курорте</p>
    <p>Название</p>
    <input type="text" name="title" required>
    <p>Описание</p>
    <input type="text" name="title" required>
    <p>Город отправления</p>
    <select name="departureCity" required>
        <#list cities as city>
            <option value="${city.id}">${city.title}</option>
        </#list>
    </select>
    <p>Город прибытия</p>
    <select name="arrivalCity" required>
        <#list cities as city>
            <option value="${city.id}">${city.title}</option>
        </#list>
    </select>
    <p>Теги</p>
    <#list tags as tag>
        <input type="checkbox" name="entertainment${tag.id}">${tag.title}</input>
    </#list>
    <p>Отель</p>
    <select name="hotel" required>
        <#list hotels as hotel>
            <option value="${hotel.id}">${hotel.title}</option>
        </#list>
    </select>
    <!--    -->
    <!--ЗДЕСЬ НУЖНО ДОБАВИТЬ СПИСОК НОМЕРОВ В ЗАВИСИМОСТИ ОТ ВЫБРАННОГО ВЫШЕ ОТЕЛЯ-->
    <!--    -->
    <p>дата начала курорта</p>
    <input type="date" name="startDate" min="2020-05-16" max="2025-12-31" value="2020-05-16">
    <p>дата окончания курорта</p>
    <input type="date" name="startDate" min="2020-05-16" max="2025-12-31" value="2020-05-16">
    <p>Стоимость</p>
    <input type="number" size="5" name="cost" min="0" max="1000000" value="0" required>
    <p>Количество человек</p>
    <input type="number" size="3" name="personCount" min="0" max="20" value="1" required>
    <p>Необходимость загранпаспорта</p>
    <p><input type="radio" name="needForForeignPassport" value="yes">Да</p>
    <p><input type="radio" name="needForForeignPassport" value="no" checked>Нет</p>
    <p>
        <button type="submit">Добавить курорт</button>
    </p>
</form>

</BODY>

</HTML>
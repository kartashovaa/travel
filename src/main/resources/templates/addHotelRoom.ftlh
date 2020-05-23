<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Добавление нового номера</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h3>Добавление нового номера</h3>
<form method="post">
    <p>Введите данные о номере</p>
    <p>Количество спальных мест</p>
    <input type="number" size="3" name="numberOfSleepingPlaces" min="0" max="20" value="1" required>
    <p>Отель</p>
    <select name="hotel" required>
        <#list hotels as hotel>
            <option value="${hotel.id}">${hotel.title}</option>
        </#list>
    </select>
    <p>Список удобств:</p>
    <#list facilities as facility>
        <input type="checkbox" name="entertainment${facility.name()}">${facility.title}</input>
    </#list>
    <p>Стоимость (сутки)</p>
    <input type="number" size="3" name="cost" min="0" max="1000000" value="0" required>
    <p>
        <button type="submit">Добавить номер</button>
    </p>
</form>
</BODY>
</HTML>
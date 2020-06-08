<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Добавление нового отеля</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h3><a href="/hotels">Вернуться к списку отелей</a></h3>
<#if isSuccessful??>
    <h3 style="color: #00ff00">Отель успешно добавлен</h3>
</#if>
<#if !isSuccessful??>
    <h3>Добавление нового отеля</h3>
    <form action="/hotels/add" method="post">
        <p>Введите данные об отеле</p>
        <p>Название отеля</p>
        <input type="text" name="title" required>
        <p>Город</p>
        <select name="city" required>
            <#list cities as city>
                <option value="${city.id}">${city.title}</option>
            </#list>
        </select>
        <p>Адрес отеля</p>
        <input type="text" name="address">
        <p>Количество звезд</p>
        <input type="number" size="3" name="stars" min="0" max="5" value="1">
        <p>
            <button type="submit">Добавить отель</button>
        </p>
    </form>
</#if>

</BODY>

</HTML>
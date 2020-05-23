<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="STYLESHEET.css">
    <title>Добавление города</title>
</HEAD>
<BODY>
<h3><a href="/main">Вернуться на главную</a></h3>
<h3>Добавление нового города</h3>
<form method="post" action="/addCity">
    <p>Введите данные о городе</p>
    <p>Название города</p>
    <input type="text" name="title" required>
    <p>Страна</p>
    <select name="country" required>
        <#list countries as country>
            <option value="${country.id}">${country.title}</option>
        </#list>
    </select>
    <p>Список развлечений:</p>
    <#list entertainments as entertainment>
        <input type="checkbox" name="entertainment${entertainment.id}">${entertainment.title}</input>
    </#list>
    <p>
        <button type="submit">Добавить город</button>
    </p>

</form>

</BODY>

</HTML>
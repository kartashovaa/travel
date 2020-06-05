<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Добавление новой страны</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h3><a href="/countries">Вернуться к списку стран</a></h3>
<h3>Добавление новой страны</h3>
<form action="/countries/add" method="POST">
    <p>Введите данные о стране</p>
    <p>Название страны</p>
    <input type="text" name="title">
    <p>Описание страны</p>
    <input type="text" name="description">
    <p>
        <button type="submit">Добавить страну</button>
    </p>
</form>

</BODY>

</HTML>
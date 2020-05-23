<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${country.title}</title>
</HEAD>
<BODY>
<h3><a href="/">Вернуться на главную</a></h3>
<h2>${country.title}</h2>

<p>${country.description}</p>

<a>Города:</a>
<ul>
    <li><a>!Название города!</a></li>
</ul>

<p>дата начала курорта</p>
<p><input type="date" min="2020-05-16" max="2025-12-31" value="2020-05-16"></p>
<!--в min и value надо указать сегодняшнюю дату-->

<p>Доступные курорты в эту страну:</p>
<ul>
    <li><a>
            <ul>
                <li>!Название курорта!</li>
                <li>!Описание курорта!</li>
                <li>Теги:
                    <ul>
                        <li>!Тег!</li>
                    </ul>
                </li>
                <li><a>!Название гостиницы!</a></li>
                <li>Дата начала курорта: !Дата начала курорта!</li>
                <li>Дата окончания курорта: !Дата окончания курорта!</li>
            </ul>
        </a></li>
</ul>

</BODY>

</HTML>
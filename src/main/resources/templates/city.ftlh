<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>${city.title}</title>
</HEAD>
<BODY>

<h2>${city.title}</h2>

<p>Список развлечений:</p>
<ul>
    <#list city.entertainments as entertainment>
        <li>${entertainment.title}</li>
    </#list>
</ul>
<p>Гостиницы:</p>
<ul>
    <li>!Название гостиницы!</li>
</ul>

<p>дата начала курорта</p>
<p><input type="date" min="2020-05-16" max="2025-12-31" value="2020-05-16"></p>
<!--в min и value надо указать сегодняшнюю дату-->

<p>Доступные курорты в этот город:</p>
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
<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="STYLESHEET.css">
    <title>Личный кабинет</title>
</HEAD>
<BODY>
<a href="/logout" class="ExitBtn">Выйти</a>
<h2>Личный кабинет</h2>
<p>Здравствуйте, ${user.firstName} ${user.middleName}!</p>
<p>Ваши данные:</p>
<ul>
    <li>ФИО: ${user.lastName} ${user.firstName} ${user.middleName}</li>
    <li>Дата рождения: ${user.birthDay?string('dd.MM.yyyy')}</li>
    <li>Пол: <#if user.male>Мужской<#else>Женский</#if></li>
    <li>Наличие загранпаспорта: <#if user.hasInternationalPassport>Да<#else >Нет</#if></li>
    <li>Email: ${user.email}</li>
    <li>Город: ${user.city.title}</li>
    <li>Наличие скидки: <#if user.hasDiscount>Да<#else>Нет</#if>!</li>
</ul>
<p>История заказов</p>
<ul>

</ul>
</BODY>
</HTML>
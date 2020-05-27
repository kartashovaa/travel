<!DOCTYPE html>
<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Регистрация</title>
</HEAD>
<BODY>

<h2>Регистрация</h2>
<form action="/signup" method="POST">
    <#if errorMessage??>
        <h2 style="color: #ff0000">${errorMessage}</h2>
    </#if>
    <h3>Введите Ваши данные:</h3>
    <p>
        <label>Фамилия</label>
        <input type="text" name="lastName">
    </p>
    <p>
        <label>Имя</label>
        <input type="text" name="firstName">
    </p>
    <p>
        <label>Отчество</label>
        <input type="text" name="middleName">
    </p>
    <p>
        <label>Дата рождения</label>
        <input type="date" name="birthday">
    </p>
    <label>Пол</label>
    <p>
        <input type="radio" name="gender" value="man" checked>Мужской</input>
        <input type="radio" name="gender" value="woman">Женский</input>
    </p>
    <label>Наличие загранпаспорта</label>
    <p>
        <input type="radio" name="hasInternationalPassport" value="yes">Да</input>
        <input type="radio" name="hasInternationalPassport" value="no" checked>Нет</input>
    </p>
    <label>Город</label>
    <p>
        <select name="cityId">
            <#list cities as city>
                <option value="${city.id}">${city.title}</option>
            </#list>
        </select>
    </p>
    <p>
        <label>Email</label>
        <input type="text" name="email">
    </p>
    <p>
        <label>Пароль</label>
        <input type="password" name="password">
    </p>

    <p>
        <label>Подтвердите пароль</label>
        <input type="password" name="confirmPassword">
    </p>
    <p>
        <button type="submit">Зарегистрироваться</button>
    </p>
</form>

</BODY>

</HTML>
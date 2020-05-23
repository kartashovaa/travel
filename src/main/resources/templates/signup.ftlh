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
    <h3>Введите Ваши данные:</h3>
    <p>
        <label>Фамилия</label>
        <input type="text" name="lastName" required>
    </p>
    <p>
        <label>Имя</label>
        <input type="text" name="firstName" required>
    </p>
    <p>
        <label>Отчество</label>
        <input type="text" name="middleName" required>
    </p>
    <p>
        <label>Дата рождения</label>
        <input type="date" name="birthday" required>
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
        <input type="text" name="email" required>
    </p>
    <p>
        <label>Пароль</label>
        <input type="password" name="password" required>
    </p>

    <p>
        <label>Подтвердите пароль</label>
        <input type="password" name="confirmPassword" required>
    </p>
    <p>
        <button type="submit">Зарегистрироваться</button>
    </p>
</form>

</BODY>

</HTML>
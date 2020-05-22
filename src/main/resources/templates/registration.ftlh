<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="STYLESHEET.CSS">
    <title>Регистрация</title>
</HEAD>
<BODY>
<h3><a href="/main">Вернуться на главную</a></h3>
<h2>Регистрация</h2>
<form>
    <h3>Введите Ваши данные:</h3>
    <p>Фамилия</p>
    <input type="text" name="lastName" required>
    <p>Имя</p>
    <input type="text" name="firstName" required>
    <p>Отчество</p>
    <input type="text" name="middleName" required>
    <p>Дата рождения</p>
    <input type="date" name="birthday" required>
    <p>Пол</p>
    <p><input type="radio" name="male" value="man" checked>Мужской</p>
    <p><input type="radio" name="male" value="woman">Женский</p>
    <p>Наличие загранпаспорта</p>
    <p><input type="radio" name="hasInternationalPassport" value="yes">Да</p>
    <p><input type="radio" name="hasInternationalPassport" value="no" checked>Нет</p>
    <p>Email</p>
    <input type="text" name="email" required>
    <p>Пароль</p>
    <input type="password" name="password" required>
    <p>Город</p>
    <p>
        <select name="city">
            <#list cities as city>
                <option value="${city.id}">${city.title}</option>
            </#list>
        </select>
    </p>
    <p>
        <button type="submit">Зарегистрироваться</button>
    </p>
</form>

</BODY>

</HTML>
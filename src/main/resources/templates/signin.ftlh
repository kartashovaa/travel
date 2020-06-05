<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="/STYLESHEET.css">
    <title>Вход в аккаунт</title>
</HEAD>
<BODY>
<#if RequestParameters.error??>
    <h3 style="color: #ff0000">Пользователь не найден!</h3>
</#if>

<#if RequestParameters.logout??>
    <h3 style="color: #00ff00">Вы успешно вышли!</h3>
</#if>
<h2>Вход в аккаунт</h2>
<form action="/signin" method="POST">
    <p>Email</p>
    <input type="text" name="username" required>
    <p>Пароль</p>
    <input type="password" name="password" required>
    <p>
        <button type="submit">Войти</button>
    </p>
</form>
<a href="/signup">У меня нет аккаунта</a>
</BODY>
</HTML>

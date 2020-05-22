<HTML>
<HEAD>
    <meta charset="utf-8">
    <LINK REL="STYLESHEET" HREF="STYLESHEET.css">
    <title>Вход в аккаунт</title>
</HEAD>
<BODY>

<h2>Вход в аккаунт</h2>
<form action="/signin" method="POST">
    <p>Email</p>
    <input type="text" name="username" required>
    <p>Пароль</p>
    <input type="password" name="password" required>
    <button type="submit">Войти</button>
</form>
<a href="/signup">У меня нет аккаунта</a>
</BODY>
</HTML>

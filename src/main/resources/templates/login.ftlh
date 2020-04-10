<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h2>Please sign in!</h2>
<form method="post" action="/login">
    <p>
        <label>Username</label>
        <input type="text" name="username" placeholder="Username" required autofocus/>
    </p>
    <p>
        <label>Password</label>
        <input type="password" name="password" placeholder="Password" required/>
    </p>
    <button type="submit">Sing-in</button>
    <a href="/register">I don't have account</a>
</form>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h2>Please sign in!</h2>
<form method="post" action="/signin">
    <p>
        <label>Email</label>
        <input type="text" name="username" placeholder="Email" required autofocus/>
    </p>
    <p>
        <label>Password</label>
        <input type="password" name="password" placeholder="Password" required/>
    </p>
    <button type="submit">Sing-in</button>
    <a href="/signup">I don't have a account</a>
</form>
</body>
</html>
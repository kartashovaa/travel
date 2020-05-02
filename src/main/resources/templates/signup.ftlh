<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h2>Please sign in!</h2>
<form method="post" action="/signup">
    <#if errorMessage??>
        <h2 style="color: #ff0000">${errorMessage}</h2>
    </#if>
    <p>
        <label>First name</label> <input type="text" name="firstName" placeholder="First name" autofocus/>
    </p>
    <p>
        <label>Last name</label> <input type="text" name="lastName" placeholder="Last name"/>
    </p>
    <p>
        <label>Middle name</label> <input type="text" name="middleName" placeholder="Middle name"/>
    </p>
    <p>
        <label>Email</label> <input type="text" name="email" placeholder="Email"/>
    </p>
    <p>
        <label>City</label> <input type="text" name="city" placeholder="Moscow"/>
    </p>
    <p>
        <label>Birthday</label> <input type="date" name="birthday"/>
    </p>
    <p>
        <label>Is male</label> <input type="checkbox" name="gender"/>
    </p>
    <p>
        <label>Has International Password</label> <input type="checkbox" name="hasInternationalPassport"/>
    </p>
    <p>
        <label>Password</label> <input type="password" name="password" placeholder="Password"/>
    </p>
    <p>
        <label>Confirm password</label>
        <input type="password" name="confirmPassword" placeholder="Confirm password"/>
    </p>
    <button type="submit">Sing-up</button>
</form>
</body>
</html>
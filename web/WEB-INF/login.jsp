
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <br>
        <div>
            <form action="login" method="post">
                Username: <input type="text" name="username" value="${userName}"><br><br>
                Password: <input type="password" name="password" value="${passwordValue}"><br>
                <input type="submit" value="Login">
            </form>
                <br>
                <br>
                <a href="register?register">Not yet a member? Click here to register.</a>
            ${errorMessage}
            ${logout}

        </div>
    </body>
</html>
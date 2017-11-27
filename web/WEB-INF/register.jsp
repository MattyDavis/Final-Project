<%-- 
    Document   : register
    Created on : Nov 27, 2017, 3:31:21 PM
    Author     : 553817
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Please fill out form to register!</h1>
        <hr>
        <form action="register" method="POST">
                username: <input type="text" name="username"><br>
                first name: <input type="text" name="firstname"><br>
                last name: <input type="text" name="lastname"><br>
                password: <input type="password" name="password"><br>
                email: <input type="email" name="email"><br>
                <select name="companylist">
                    <c:forEach items="${companies}" var="company">
                        <option value="${company.companyID}" name ="nameCompany">
                         ${company.companyName}       
                        </option>
                        </c:forEach>
                </select>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
    </body>
</html>

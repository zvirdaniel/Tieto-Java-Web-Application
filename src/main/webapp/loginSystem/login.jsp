<%--
  Created by IntelliJ IDEA.
  User: zvird
  Date: 21.07.2016
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="loginServlet">
    <table border="1" width="30%" cellpadding="3">
        <thead>
        <tr>
            <th colspan="2">Please login</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="username" value=""/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" value=""/></td>
        </tr>
        <tr>
            <td><input type="reset" value="Reset"/></td>
            <td><input type="submit" value="Login"/></td>
        </tr>
        </tbody>
    </table>
</form>

</body>
</html>

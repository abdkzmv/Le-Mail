<%--
  Created by IntelliJ IDEA.
  User: abdullahkazimov
  Date: 04.08.22
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="frontend/style.css">
    <meta name="viewport" content="width=mobile-device-with, initial-scale=1">
</head>
<body>
    <%
        if(request.getSession().getAttribute("loginError") != null) {
            request.getSession().invalidate();
            request.getSession().setAttribute("loginError",true);
        }
    %>
    <header>
        <img src="files/logo.png" width="120" height="120">
    </header>
    <main>
        <br>
        <h2>Enter Le Mail</h2>
        <br>
        <form action="login" method="get">
            <table>
                <tr>
                    <td>
                        Username
                    </td>
                    <td>
                        <input type="text" name="username">
                    </td>
                </tr>
                <tr>
                    <td>
                        Password
                    </td>
                    <td>
                        <input type="password" name="user_password">
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        New user? <a href="register.jsp">Sign up here</a>
                    </td>
                </tr>
            </table>
            <br>
            <input type="submit" value="Sign in">
        </form>
        <%
            if(request.getSession().getAttribute("loginError") != null) {
                if((boolean) request.getSession().getAttribute("loginError") == true) {
                    request.getSession().invalidate();
        %>
        <p style="color: red"><br>Username and password don't match.<br>Please try again</p>
        <%
                }
            }
        %>
    </main>
    <footer>
        <br>
        <br>
        <a href="https://www.github.com/akazimov" target="_blank">&copy; Abdullah Kazimov</a>
    </footer>
</body>
</html>

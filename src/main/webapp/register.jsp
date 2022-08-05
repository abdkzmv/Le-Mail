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
    <title>Register</title>
    <link rel="stylesheet" href="frontend/style.css">
    <meta name="viewport" content="width=mobile-device-with, initial-scale=1">
</head>
<body>
    <header>
        <img src="files/logo.png" width="120" height="120">
    </header>
    <main>
        <br>
        <h2>Join Le Mail</h2>
        <br>
        <form action="register" method="post">
            <table>
                <tr>
                    <td>
                        Name
                    </td>
                    <td>
                        <input type="text" name="full_name">
                    </td>
                </tr>
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
                        Email
                    </td>
                    <td>
                        <input type="email" name="email">
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
                        Wanna sign in? <a href="login.jsp">Click here</a>
                    </td>
                </tr>
            </table>
            <br>
            <input type="submit" value="Register">
        </form>
        <%
            if(request.getSession().getAttribute("fillBlanks") != null)
                if((boolean) request.getSession().getAttribute("fillBlanks") == true) {
                    request.getSession().removeAttribute("fillBlanks");
                    String name = request.getParameter("full_name");
                    String username = request.getParameter("username");
                    String email = request.getParameter("email");
                    String password = request.getParameter("user_password");
                    if(!(name==null||username==null||email==null||password==null))
        %>
                <p style="color: red;"><br>Please fill in all blanks.</p>
        <%
                }
            if(request.getSession().getAttribute("problemExists") != null) {
                if((boolean)request.getSession().getAttribute("problemExists") == true) {

                    request.getSession().removeAttribute("problemExists");
                    String name = request.getParameter("full_name");
                    String username = request.getParameter("username");
                    String email = request.getParameter("email");
                    String password = request.getParameter("user_password");
                    if(!(name==null||username==null||email==null||password==null))
        %>
                <p style="color: red;"><br>Username is already in use.</p>
        <%
                }   else {
                    request.getSession().removeAttribute("problemExists");
                    String name = request.getParameter("full_name");
                    String username = request.getParameter("username");
                    String email = request.getParameter("email");
                    String password = request.getParameter("user_password");
                    if(!(name==null||username==null||email==null||password==null||
                            (name.isEmpty()||username.isEmpty()||email.isEmpty()||password.isEmpty())))
        %>
                <p style="color: green;"><br>Success!<br>Now go to <a href="login.jsp">Login Page</a> and sign in</p>
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

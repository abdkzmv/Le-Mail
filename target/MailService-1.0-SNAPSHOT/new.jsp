<%@ page import="database.entity.User" %>
<%@ page import="database.main.Context" %><%--
  Created by IntelliJ IDEA.
  User: abdullahkazimov
  Date: 04.08.22
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New message</title>
    <link rel="stylesheet" href="frontend/style.css">
    <meta name="viewport" content="width=mobile-device-with, initial-scale=1">
</head>
<body>
    <header>
        <a href="index.jsp">
            <img src="files/logo.png" width="120" height="120">
        </a>
        <br>
    </header>
    <%
        User me = Context.getUserDao().getByUsername(
                (String)request.getSession().getAttribute("username")
        );
    %>
    <form action="sendMail" method="post">
    <table>
        <tr>
            <td>
                From:
            </td>
            <td>
                <%=me.getUsername()%>
            </td>
        </tr>
        <tr>
            <td>
                To:
            </td>
            <td>
                <select name="option_user">
                    <%
                        for(User u : Context.getUserDao().getAllUsers()) {
                            if(u.getUsername().equals(me.getUsername()) == false) {
                    %>
                        <option value="<%=u.getUsername()%>">
                            <%=u.getUsername()%> (<%=u.getName()%>)
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Subject:
            </td>
            <td>
                <input name="newsubject" type="text">
            </td>
        </tr>
        <tr>
            <td>
                Message:
            </td>
            <td>
                <textarea name="newmessage">

                </textarea>
            </td>
        </tr>
    </table>
        <br>
        <input type="submit" value="Send">
    </form>
    <br>
    <div align="center">
        <a href="index.jsp">
            <input value="Homepage" type="submit">
        </a>
    </div>
    <%
        if(request.getSession().getAttribute("sentSuccessfully") != null) {
            if((boolean) request.getSession().getAttribute("sentSuccessfully") == true){
                request.getSession().removeAttribute("sentSuccessfully");
    %>
        <p style="color:green;"><br>Success!</p>
    <%
        } else {
            request.getSession().removeAttribute("sentSuccessfully");
    %>
        <p style="color: red;"><br>Please fill in all blanks.</p>
    <%
            }
        }
    %>
    <footer>
        <br>
        <br>
        <a href="https://www.github.com" target="_blank">&copy; Abdullah Kazimov</a>
    </footer>
</body>
</html>

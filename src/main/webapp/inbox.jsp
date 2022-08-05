<%@ page import="database.entity.User" %>
<%@ page import="database.main.Context" %>
<%@ page import="database.entity.Mail" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: abdullahkazimov
  Date: 04.08.22
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Inbox</title>
    <link href="frontend/style.css" rel="stylesheet">
    <meta name="viewport" content="width=mobile-device-with, initial-scale=1">
</head>
<body>
<header>
    <a href="index.jsp">
        <img src="files/logo.png" width="120" height="120">
    </a>
    <br>
</header>
    <table>
        <tr>
            <td>
                <a href="index.jsp">
                    <input type="submit" value="Homepage">
                </a>
            </td>
        </tr>
    </table>
    <br>
    <table>
        <tr>
            <td>
                <form action="received" method="post">
                    <input type="submit" value="Received">
                </form>
            </td>
            <td>
                <form action="sent" method="post">
                    <input type="submit" value="Sent">
                </form>
            </td>
        </tr>
    </table>
    <%
        String username = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("user_password");
        User u = Context.getUserDao().getByUsername(username);
    %>
    <%
        if(request.getSession().getAttribute("ReceivedCommandActivated") != null) {
            request.getSession().removeAttribute("ReceivedCommandActivated");
    %>
        <table>
            <%
                List<Mail> mails = Context.getMailDao().getAllMailsTo(u);
                for(int i=mails.size()-1;i>=0;i--) {
                    Mail m = mails.get(i);
            %>
            <tr>
                <td>
                    <details>
                        <summary>
                            <%=m.getSubject()%> (from <b><%=m.getFrom().getUsername()%> ~ <%=m.getFrom().getName()%></b>)
                        </summary>
                        <%=m.getText()%>
                    </details>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    <%
        }
        if(request.getSession().getAttribute("SentCommandActivated") != null) {
            request.getSession().removeAttribute("SentCommandActivated");
    %>
    <table>
        <%
            List<Mail> mails = Context.getMailDao().getAllMailsFrom(u);
            for(int i=mails.size()-1;i>=0;i--) {
                Mail m = mails.get(i);
        %>
        <tr>
            <td>
                <details>
                    <summary>
                        <%=m.getSubject()%> (to <b><%=m.getTo().getUsername()%> ~ <%=m.getTo().getName()%></b>)
                    </summary>
                    <%=m.getText()%>
                </details>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>
    <footer>
        <br>
        <br>
        <a href="https://www.github.com/akazimov" target="_blank">&copy; Abdullah Kazimov</a>
    </footer>
</body>
</html>

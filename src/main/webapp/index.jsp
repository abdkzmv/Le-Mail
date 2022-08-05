<%@ page import="database.entity.User" %>
<%@ page import="database.main.Context" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>MailService</title>
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
    <main>
    <%
        if(request.getSession().getAttribute("loggedInUser") == null)
            response.sendRedirect("register.jsp");
        else {
            User u = Context.getUserDao().getByUsername((String) request.getSession().getAttribute("username"));
            String name = u.getName();
    %>
        <h3>Welcome, <%=name%>! 👋</h3>
        <br>
        <a href="inbox.jsp">
            <input type="submit" value="My Inbox">
        </a>
        <br>
        <br>
        <a href="new.jsp">
            <input type="submit" value="Send Message">
        </a>
        <br>
        <br>
        <a href="settings.jsp">
            <input type="submit" value="Settings">
        </a>
        <br>
        <br>
    <form action="logout" method="post">
        <input type="submit" value="Log out">
    </form>
    <%
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
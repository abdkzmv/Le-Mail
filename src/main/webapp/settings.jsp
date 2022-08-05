<%@ page import="database.main.Context" %>
<%@ page import="database.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: abdullahkazimov
  Date: 05.08.22
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Settings</title>
    <link rel="stylesheet" href="frontend/style.css">
</head>
<body>
<header>
  <a href="index.jsp">
    <img src="files/logo.png" width="120" height="120">
  </a>
  <br>
</header>
<main>
  <br>
  <h2>User info</h2>
  <%
      User u = Context.getUserDao().getByUsername(
              (String) request.getSession().getAttribute("username")
      );
  %>
  <form action="update" method="post">
    <table>
      <tr>
        <td>
          Name
        </td>
        <td>
          <input type="text" name="full_name" value="<%=u.getName()%>">
        </td>
      </tr>
      <tr>
        <td>
          Username
        </td>
        <td style="text-align: center;">
          <%=request.getSession().getAttribute("username")%>
        </td>
      </tr>
      <tr>
        <td>
          Email
        </td>
        <td>
          <input type="email" name="email" value="<%=u.getEmail()%>">
        </td>
      </tr>
      <tr>
        <td>
          Password
        </td>
        <td>
          <input type="password" name="user_password" value="<%=u.getPassword()%>">
          <meta name="viewport" content="width=mobile-device-with, initial-scale=1">
        </td>
      </tr>
    </table>
    <br>
    <input type="submit" value="Update">
  </form>
  <br>
  <div align="center">
    <a href="index.jsp">
      <input value="Homepage" type="submit">
    </a>
  </div>
  <%
      if(request.getSession().getAttribute("fillBlanks") != null) {
        request.getSession().removeAttribute("fillBlanks");
  %>
        <p style="color: red;"><br>Please fill in all the blanks.</p>
  <%
    }
  %>
  <%
    if(request.getSession().getAttribute("success") != null) {
      request.getSession().removeAttribute("success");
  %>
        <p style="color: green;"><br>Success!</p>
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

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20.04.2020
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
USER
<form action="/logout" method="get">
    <input type = "submit" value="logout">
</form>
<%
    List<User> list = (List<User>) request.getAttribute("users");

    for (User item : list) {
        if(item.getName().equals(session.getAttribute("name"))) {
            out.println("<p>" + item + "</p>");
        }
    }
%>
</body>
</html>

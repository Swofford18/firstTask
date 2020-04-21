<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20.04.2020
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
ADMIN
<form action="/logout" method="get">
    <input type = "submit" value="logout">
</form>
<table border="1px black">
    <tr>
        <td>
            <%
                List<User> list = (List<User>) request.getAttribute("users");

                for (User item : list) {
                    out.println("<p>" + item + "</p>");
                }
            %>
        </td>
        <td>
            <form action="/admin/createUser" method="post">
                <input type="text" required placeholder="name" name="name"><br>
                <input type="text" required placeholder="password" name="password"><br>
                <input type="text" required placeholder="age" name="age"><br>
                <input type="text" required placeholder="role" name="role"><br>
                <input type = "submit" value="CREATE">
            </form>
        </td>
        <td>
            <form action="/admin/updateUser" method="post">
                <input type="text" required placeholder="name" name="name"><br>
                <input type="text" required placeholder="password" name="password"><br>
                <input type="text" required placeholder="age" name="age"><br>
                <input type="text" required placeholder="role" name="role"><br>
                <input type = "submit" value="UPDATE">
            </form>
        </td>
        <td>
            <form action="/admin/deleteUser" method="post">
                <input type="text" required placeholder="name" name="name"><br>
                <input type = "submit" value="DELETE">
            </form>
        </td>
    </tr>
</table>
</body>
</html>

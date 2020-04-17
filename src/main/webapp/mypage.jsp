<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>213</title>
</head>
<body>
<h1>
    <%
        List<User> list = (List<User>) request.getAttribute("users");

        for (User item : list) {
            out.println("<p>" + item + "</p>");
        }
    %>
</h1>
</body>
</html>
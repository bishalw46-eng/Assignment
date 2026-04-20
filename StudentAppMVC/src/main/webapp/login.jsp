<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Portal Login</title>
</head>
<body>
<%
    String error = (String) request.getAttribute("error");
%>

    <h2>Student Portal Login</h2>

<% if (error != null) { %>
    <p style="color:red;"><%= error %></p>
<% } %>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label>Username:</label>
        <input type="text" name="username" required autofocus>
        <br><br>

        <label>Password:</label>
        <input type="password" name="password" required>
        <br><br>

        <button type="submit">Login</button>
    </form>
</body>
</html>

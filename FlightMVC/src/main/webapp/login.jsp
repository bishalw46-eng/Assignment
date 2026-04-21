<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FlightMVC Login</title>
</head>
<body>
<%
    String error = (String) request.getAttribute("error");
%>

    <h2>Flight Booking Login</h2>
    <p>Login with your predefined user to continue to the booking page.</p>

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

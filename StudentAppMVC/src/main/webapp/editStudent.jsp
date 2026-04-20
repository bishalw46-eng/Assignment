<%@page import="com.learn.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student</title>
</head>
<body>
<%
    Student student = (Student) request.getAttribute("student");
    String error = (String) request.getAttribute("error");
%>

    <h2>Update Student</h2>
    <p><a href="${pageContext.request.contextPath}/listStudents">Back to Student List</a></p>

<% if (student == null) { %>
    <p>Student not found.</p>
<% } else { %>
    <% if (error != null) { %>
        <p style="color:red;"><%= error %></p>
    <% } %>

    <form action="${pageContext.request.contextPath}/updateStudent" method="post">
        <input type="hidden" name="id" value="<%= student.getId() %>">

        <label>Name:</label>
        <input type="text" name="name" value="<%= student.getName() %>" required>
        <br><br>

        <label>Email:</label>
        <input type="email" name="email" value="<%= student.getEmail() %>" required>
        <br><br>

        <label>Course:</label>
        <input type="text" name="course" value="<%= student.getCourse() %>" required>
        <br><br>

        <label>Marks:</label>
        <input type="number" step="0.01" name="marks" value="<%= student.getMarks() %>" required>
        <br><br>

        <button type="submit">Update Student</button>
    </form>
<% } %>
</body>
</html>

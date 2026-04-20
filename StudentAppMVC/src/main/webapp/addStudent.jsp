<%@page import="com.learn.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Registration</title>
</head>
<body>
<%
    String error = (String) request.getAttribute("error");
    Student student = (Student) request.getAttribute("student");
%>

    <h2>Add Student</h2>
    <p><a href="${pageContext.request.contextPath}/listStudents">Back to Student List</a></p>

<% if (error != null) { %>
    <p style="color:red;"><%= error %></p>
<% } %>

    <form action="${pageContext.request.contextPath}/addStudent" method="post">
        <label>Name:</label>
        <input type="text" name="name" value="<%= student != null ? student.getName() : "" %>" required>
        <br><br>

        <label>Email:</label>
        <input type="email" name="email" value="<%= student != null ? student.getEmail() : "" %>" required>
        <br><br>

        <label>Course:</label>
        <input type="text" name="course" value="<%= student != null ? student.getCourse() : "" %>" required>
        <br><br>

        <label>Marks:</label>
        <input type="number" step="0.01" name="marks" value="<%= student != null ? student.getMarks() : "" %>" required>
        <br><br>

        <button type="submit">Save Student</button>
    </form>
</body>
</html>

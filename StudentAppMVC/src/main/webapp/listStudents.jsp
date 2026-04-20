<%@page import="com.learn.model.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
    String username = (String) session.getAttribute("username");
    String error = (String) request.getAttribute("error");
%>

    <h2>Student Records</h2>
    <p>Welcome, <strong><%= username %></strong></p>
    <p>
        <a href="${pageContext.request.contextPath}/addStudent">Add Student</a> |
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </p>

<% if (error != null) { %>
    <p style="color:red;"><%= error %></p>
<% } %>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Course</th>
                <th>Marks</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
<%
    if (students == null || students.isEmpty()) {
%>
            <tr>
                <td colspan="6">No students found.</td>
            </tr>
<%
    } else {
        for (Student student : students) {
%>
            <tr>
                <td><%= student.getId() %></td>
                <td><%= student.getName() %></td>
                <td><%= student.getEmail() %></td>
                <td><%= student.getCourse() %></td>
                <td><%= student.getMarks() %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/editStudent?id=<%= student.getId() %>">Edit</a>
                    |
                    <a href="${pageContext.request.contextPath}/deleteStudent?id=<%= student.getId() %>">Delete</a>
                </td>
            </tr>
<%
        }
    }
%>
        </tbody>
    </table>
</body>
</html>

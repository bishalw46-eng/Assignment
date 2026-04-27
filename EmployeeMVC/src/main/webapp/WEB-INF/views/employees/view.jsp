<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Details</title>
</head>
<body>
    <h2>Employee Details</h2>

    <p><strong>ID:</strong> ${employee.id}</p>
    <p><strong>Name:</strong> ${employee.name}</p>
    <p><strong>Email:</strong> ${employee.email}</p>
    <p><strong>Department:</strong> ${employee.department}</p>
    <p><strong>Salary:</strong> ${employee.salary}</p>

    <p>
        <a href="${pageContext.request.contextPath}/employees">Back to Employee List</a>
        |
        <a href="${pageContext.request.contextPath}/employees/edit/${employee.id}">Edit Employee</a>
    </p>
</body>
</html>

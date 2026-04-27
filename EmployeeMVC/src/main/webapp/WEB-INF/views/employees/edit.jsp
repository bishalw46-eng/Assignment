<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
</head>
<body>
    <h2>Edit Employee</h2>
    <p><a href="${pageContext.request.contextPath}/employees">Back to Employee List</a></p>

    <form:form action="${pageContext.request.contextPath}/employees/edit/${employee.id}" method="post" modelAttribute="employee">
        <form:hidden path="id" />

        <label>Name:</label>
        <form:input path="name" />
        <form:errors path="name" cssStyle="color:red;" />
        <br><br>

        <label>Email:</label>
        <form:input path="email" type="email" />
        <form:errors path="email" cssStyle="color:red;" />
        <span style="color:red;">${emailError}</span>
        <br><br>

        <label>Department:</label>
        <form:input path="department" />
        <form:errors path="department" cssStyle="color:red;" />
        <br><br>

        <label>Salary:</label>
        <form:input path="salary" type="number" step="0.01" />
        <form:errors path="salary" cssStyle="color:red;" />
        <br><br>

        <button type="submit">Update Employee</button>
    </form:form>
</body>
</html>

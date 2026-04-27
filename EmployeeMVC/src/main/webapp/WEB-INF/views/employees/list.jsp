<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Records</title>
</head>
<body>
    <h2>Employee Records</h2>

    <p>Total Employees: <strong>${employeeCount}</strong></p>

    <form action="${pageContext.request.contextPath}/employees" method="get">
        <label>Search by name:</label>
        <input type="text" name="keyword" value="${keyword}">
        <button type="submit">Search</button>
        <a href="${pageContext.request.contextPath}/employees">Clear</a>
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/employees/add">Add Employee</a>
    </p>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Department</th>
                <th>Salary</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty employees}">
                    <tr>
                        <td colspan="6">No employees found.</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="employee" items="${employees}">
                        <tr>
                            <td>${employee.id}</td>
                            <td>${employee.name}</td>
                            <td>${employee.email}</td>
                            <td>${employee.department}</td>
                            <td>${employee.salary}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/employees/view/${employee.id}">View</a>
                                |
                                <a href="${pageContext.request.contextPath}/employees/edit/${employee.id}">Edit</a>
                                |
                                <a href="${pageContext.request.contextPath}/employees/delete/${employee.id}"
                                   onclick="return confirm('Delete this employee?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</body>
</html>

package com.learn.controller;

import java.io.IOException;

import com.learn.model.Student;
import com.learn.service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentService service = new StudentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        double marks;

        try {
            id = Integer.parseInt(request.getParameter("id"));
            marks = Double.parseDouble(request.getParameter("marks"));
        } catch (NumberFormatException exception) {
            response.sendRedirect(request.getContextPath() + "/listStudents");
            return;
        }

        Student student = new Student(
                id,
                request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("course"),
                marks
        );

        String error = service.updateStudent(student);
        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("student", student);
            request.getRequestDispatcher("/editStudent.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/listStudents");
    }
}

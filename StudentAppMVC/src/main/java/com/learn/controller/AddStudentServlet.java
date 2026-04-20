package com.learn.controller;

import java.io.IOException;

import com.learn.model.Student;
import com.learn.service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentService service = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/addStudent.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = buildStudentFromRequest(request, 0);
        if (student == null) {
            request.setAttribute("error", "Marks must be a valid number.");
            request.getRequestDispatcher("/addStudent.jsp").forward(request, response);
            return;
        }

        String error = service.addStudent(student);
        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("student", student);
            request.getRequestDispatcher("/addStudent.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/listStudents");
    }

    private Student buildStudentFromRequest(HttpServletRequest request, int id) {
        try {
            double marks = Double.parseDouble(request.getParameter("marks"));
            return new Student(
                    id,
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("course"),
                    marks
            );
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}

package com.learn.controller;

import java.io.IOException;

import com.learn.model.Student;
import com.learn.service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentService service = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = service.getStudentById(id);
            if (student == null) {
                response.sendRedirect(request.getContextPath() + "/listStudents");
                return;
            }

            request.setAttribute("student", student);
            request.getRequestDispatcher("/editStudent.jsp").forward(request, response);
        } catch (NumberFormatException exception) {
            response.sendRedirect(request.getContextPath() + "/listStudents");
        }
    }
}

package com.learn.controller;

import java.io.IOException;

import com.learn.service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentService service = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            service.deleteStudent(id);
        } catch (NumberFormatException exception) {
            request.getSession().setAttribute("error", "Invalid student id.");
        }

        response.sendRedirect(request.getContextPath() + "/listStudents");
    }
}

package com.learn.controller;

import java.io.IOException;
import java.util.List;

import com.learn.model.Student;
import com.learn.service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/listStudents")
public class ListStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentService service = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = service.getStudents();
        request.setAttribute("students", students);

        HttpSession session = request.getSession(false);
        if (session != null) {
            Object flashError = session.getAttribute("error");
            if (flashError != null) {
                request.setAttribute("error", flashError);
                session.removeAttribute("error");
            }
        }

        request.getRequestDispatcher("/listStudents.jsp").forward(request, response);
    }
}

package com.learn.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.learn.dao.StudentDAO;
import com.learn.model.Student;

public class StudentService {

    private final StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    public String addStudent(Student student) {
        return saveStudent(student, false);
    }

    public List<Student> getStudents() {
        try {
            return studentDAO.getAllStudents();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Student getStudentById(int id) {
        try {
            return studentDAO.getStudentById(id);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public String updateStudent(Student student) {
        return saveStudent(student, true);
    }

    public boolean deleteStudent(int id) {
        try {
            return studentDAO.deleteStudent(id) > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private String saveStudent(Student student, boolean update) {
        String validationError = validateStudent(student, update);
        if (validationError != null) {
            return validationError;
        }

        try {
            int affectedRows = update
                    ? studentDAO.updateStudent(student)
                    : studentDAO.addStudent(student);

            if (affectedRows == 0) {
                return update ? "Student could not be updated." : "Student could not be added.";
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return "Unable to save student details right now.";
        }

        return null;
    }

    private String validateStudent(Student student, boolean update) {
        if (student == null) {
            return "Student details are required.";
        }
        if (update && student.getId() <= 0) {
            return "A valid student id is required for update.";
        }
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return "Name is required.";
        }
        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            return "Email is required.";
        }
        if (!student.getEmail().contains("@")) {
            return "Enter a valid email address.";
        }
        if (student.getCourse() == null || student.getCourse().trim().isEmpty()) {
            return "Course is required.";
        }
        if (student.getMarks() < 0 || student.getMarks() > 100) {
            return "Marks must be between 0 and 100.";
        }

        try {
            boolean emailExists = studentDAO.emailExists(
                    student.getEmail().trim(),
                    update ? student.getId() : null
            );
            if (emailExists) {
                return "Email already exists.";
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return "Unable to validate student email.";
        }

        student.setName(student.getName().trim());
        student.setEmail(student.getEmail().trim());
        student.setCourse(student.getCourse().trim());
        return null;
    }
}

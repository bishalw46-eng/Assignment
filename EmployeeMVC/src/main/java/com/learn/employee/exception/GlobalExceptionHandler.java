package com.learn.employee.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNotFound(IllegalArgumentException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "employees/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, Model model) {
        model.addAttribute("errorMessage", "Something went wrong. Please try again.");
        return "employees/error";
    }
}

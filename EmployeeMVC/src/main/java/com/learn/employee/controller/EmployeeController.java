package com.learn.employee.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learn.employee.model.Employee;
import com.learn.employee.service.EmployeeService;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping({"/", "/employees"})
    public String listEmployees(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("employees", employeeService.searchEmployeesByName(keyword));
        model.addAttribute("employeeCount", employeeService.countEmployees());
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        return "employees/list";
    }

    @GetMapping("/employees/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/add";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "employees/add";
        }

        try {
            employeeService.createEmployee(employee);
        } catch (IllegalStateException exception) {
            model.addAttribute("emailError", exception.getMessage());
            return "employees/add";
        }

        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employees/edit";
    }

    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            employee.setId(id);
            return "employees/edit";
        }

        try {
            employeeService.updateEmployee(id, employee);
        } catch (IllegalStateException exception) {
            employee.setId(id);
            model.addAttribute("emailError", exception.getMessage());
            return "employees/edit";
        }

        return "redirect:/employees";
    }

    @GetMapping("/employees/view/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employees/view";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}

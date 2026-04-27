package com.learn.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.employee.model.Employee;
import com.learn.employee.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Employee> searchEmployeesByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllEmployees();
        }
        return employeeRepository.findByNameContainingIgnoreCase(keyword.trim());
    }

    @Transactional(readOnly = true)
    public long countEmployees() {
        return employeeRepository.count();
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found."));
    }

    public void createEmployee(Employee employee) {
        trimEmployee(employee);
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalStateException("Email already exists.");
        }
        employeeRepository.save(employee);
    }

    public void updateEmployee(Long id, Employee employee) {
        trimEmployee(employee);
        if (employeeRepository.existsByEmailAndIdNot(employee.getEmail(), id)) {
            throw new IllegalStateException("Email already exists.");
        }

        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());
        employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    private void trimEmployee(Employee employee) {
        if (employee.getName() != null) {
            employee.setName(employee.getName().trim());
        }
        if (employee.getEmail() != null) {
            employee.setEmail(employee.getEmail().trim());
        }
        if (employee.getDepartment() != null) {
            employee.setDepartment(employee.getDepartment().trim());
        }
    }
}

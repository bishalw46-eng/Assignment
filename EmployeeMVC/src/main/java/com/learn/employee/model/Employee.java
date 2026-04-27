package com.learn.employee.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required.")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Enter a valid email address.")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "Department is required.")
    @Column(nullable = false, length = 100)
    private String department;

    @NotNull(message = "Salary is required.")
    @DecimalMin(value = "0.01", message = "Salary must be greater than 0.")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}

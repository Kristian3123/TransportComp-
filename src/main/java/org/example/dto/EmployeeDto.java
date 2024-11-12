package org.example.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.entity.Qualification;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Data Transfer Object (DTO) class representing an employee.
 */
public class EmployeeDto {

    private int employeeId;

    private String employeeName;

    private Qualification qualification;

    private BigDecimal salary;

    private Company company;

    /**
     * Gets the company associated with this object.
     * @return The company associated with this object.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets the company for this object.
     * @param company The company to be set.
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Default constructor for EmployeeDto class.
     */
    public EmployeeDto() {
    }

    /**
     * Parameterized constructor for EmployeeDto class.

     * @param employeeId The employee ID
     * @param employeeName The employee name
     * @param salary The salary of the employee
     * @param qualification The qualification of the employee
     */
    public EmployeeDto( int employeeId, String employeeName, BigDecimal salary, Qualification qualification) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.qualification=qualification;
    }

    /**
     *Gets the ID of the employee.
     * @return The ID of the employee
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the EmployeeId of the employee .
     * @param employeeId The EmployeeId
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets the name of the employee.
     * @return The name of the employee
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Sets the Employee name .
     * @param employeeName The Employee name
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * Gets the qualification of the employee.
     * @return The qualification of the employee
     */
    public Qualification getQualification() {
        return qualification;
    }

    /**
     * Sets the qualification for the employee.
     * @param qualification The qualification of the employee
     */
    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    /**
     * Retrieves the salary of the employee.
     * @return The salary of the employee
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Sets the salary for the employee.
     * @param salary The salary to be set for the employee
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Returns a string representation of the employee.
     * @return A string representation of the employee
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" +employeeId +
                ", name='" + employeeName + '\'' +
                ", salary=" + salary +
                ", qualification=" + qualification +
                '}';
    }
}
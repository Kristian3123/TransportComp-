package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

/**
 * Represents an Employee entity.
 */
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employeeId")
    private int employeeId;

    @NotBlank(message = "Employee name cannot be blank!")
//    @Size(max = 20, message = "Employee name has to be with up to 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Employee name has to start with capital letter!")
    @Column(name = "name", nullable = false)
    private String employeeName;

//    @ElementCollection(targetClass = Qualification.class)
//    @CollectionTable(name = "employee_qualifications", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "qualification")
    private Qualification qualification;

    @NotNull(message = "Employee salary cannot be blank!")
    @DecimalMin(value = "1000.00", message = "Employee salary cannot be below the allowedcountry minimum!")
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @ManyToOne()
    @JoinColumn(name="companyID",nullable = false)
    private Company company;


    /**
     * Sets the company associated with this employee.
     * @param company The company to be set for the employee
     */
    public void setCompany(Company company)
    {
        this.company = company;
    }

    /**
     * Retrieves the company associated with this employee.
     * @return The company associated with the employee
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Default constructor for Employee class.
     */
    public Employee() {
    }

    /**
     * Parameterized constructor for Employee class.
     * @param company The company associated with the employee
     * @param employeeId The employee ID
     * @param employeeName The employee name
     * @param salary The salary of the employee
     * @param qualification The qualification of the employee
     */
    public Employee(Company company, int employeeId, String employeeName, BigDecimal salary, Qualification qualification) {
        this.company=company;
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
                ", company=" + company +
                "id=" +employeeId +
                ", name='" + employeeName + '\'' +
                ", salary=" + salary +
                ", qualification=" + qualification +
//                ", transport=" + transport +

                '}';
    }
}

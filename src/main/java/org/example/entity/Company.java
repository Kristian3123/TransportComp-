package org.example.entity;

import jakarta.validation.constraints.*;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.example.entity.Transport;

/**
 * Represents a company entity.
 */
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compnyID")
    private long companyId;

    @NotBlank(message = "Company name cannot be blank!")
//    @Size(max = 50, message = "Company name has to be with up to 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Company name has to start with capital letter!")
    @Column(name = "name", nullable = false)
    private String companyName;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)

    private Set<Client> clients;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)

    private Set<VehicleTypee> vehicles;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)

    private static Set<Employee> employees;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)

    private static Set<Transport> transports;

    @Column(name = "income")
    private BigDecimal income;

    /**
     * Sets the income of the company.
     * @param income The income of the company
     */
    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    /**
     * Calculates the income of the company based on transports and employees.
     *
     * @return The calculated income of the company
     */
    public BigDecimal getIncome() {
        BigDecimal income=BigDecimal.ZERO;

        if (transports != null) {
            for (Transport transport : transports) {
                if (transport.getPrice() != null && transport.isPaymentReceived()) {
                    income = income.add(transport.getPrice());
                }
            }
        }
        if (employees != null) {
            for (Employee employee : employees) {
                if (employee.getSalary() != null) {
                    income = income.subtract(employee.getSalary());
                }
            }
        }
        return income;
    }

    /**
     * Default constructor for Company class.
     */
    public Company() {
    }

    /**
     * Constructor for creating a Company object with specified companyId and companyName.
     * @param companyId   The unique identifier of the company.
     * @param companyName The name of the company.
     */
    public Company(int companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.clients = new HashSet<>();
        this.vehicles = new HashSet<>();
        this.employees = new HashSet<>();
        this.transports = new HashSet<>();
//        this.income=0.0;
    }

    /**
     * Gets the ID of the company.
     * @return The ID of the company
     */
    public long getCompanyId() {
        return companyId;
    }

    /**
     * Sets the ID of the company.
     * @param companyId The ID of the company
     */
    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    /**
     * Gets the Name of the company.
     * @return The Name of the company
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the Name of the company.
     * @param companyName The Name of the company
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**
     * Gets the clients associated with the company.
     * @return The clients associated with the company
     */
    public Set<Client> getClients() {
        return clients;
    }

    /**
     * Sets the clients associated with the company.
     * @param clients The clients associated with the company
     */
    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    /**
     * Gets the vehicles associated with the company.
     * @return The vehicles associated with the company
     */
    public Set<VehicleTypee> getVehicles() {
        return vehicles;
    }

    /**
     * Sets the vehicles associated with the company.
     * @param vehicles The vehicles associated with the company
     */
    public void setVehicles(Set<VehicleTypee> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Gets the employees associated with the company.
     * @return The employees associated with the company
     */
    public Set<Employee> getEmployees() {
        return employees;
    }

    /**
     * Sets the employees associated with the company.
     * @param employees The employees associated with the company
     */
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Gets the transports associated with the company.
     * @return The transports associated with the company
     */
    public Set<Transport> getTransports() {
        return transports;
    }

    /**
     * Sets the transports associated with the company.
     * @param transports The transports associated with the company
     */
    public void setTransports(Set<Transport> transports) {
        this.transports = transports;
    }


    /**
     * Generates a string representation of the Company object.
     * @return String representation of the Company object.
     */

    @Override
    public String toString() {
        return "Company{" +
                "id=" + companyId +
                ", name='" + companyName + '\'' +
//                ", clients=" + clients +
//                ", vehicles=" + vehicles +
//                ", employees=" + employees +
//                ", transports=" + transports +

                ", income=" + income +

                '}';
    }
}

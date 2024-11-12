package org.example.dto;

import com.sun.jdi.connect.Transport;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.example.entity.Client;
import org.example.entity.Employee;

import org.example.entity.VehicleTypee;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Data Transfer Object (DTO) class representing a company.
 */
public class CompanyDto {


    private long companyId;

    private String companyName;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    private List<Client> clients = new ArrayList<>();
    private Set<Client> clients;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    private List<VehicleTypee> vehicles = new ArrayList<>();
    private Set<VehicleTypee> vehicles;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    private List<Employee> employees = new ArrayList<>();
    private static Set<Employee> employees;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    private List<Transport> transports = new ArrayList<>();
    private static Set<Transport> transports;

    /**
     * Constructs an empty CompanyDto class.
     */
    public CompanyDto() {
    }

    /**
     * Constructs a CompanyDto object with provided companyId and companyName.
     * @param companyId   The unique identifier of the company.
     * @param companyName The name of the company.
     */
    public CompanyDto(int companyId, String companyName) {
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
    public static void setTransports(Set<Transport> transports) {
        transports = transports;
    }

    /**
     * Returns a string representation of the CompanyDto object.
     * @return A string containing information about the company object.
     */
    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", clients=" + clients +
                ", vehicles=" + vehicles +
                ", employees=" + employees +
                ", transports=" + transports +
//                ", income=" + income +
                '}';
    }
}
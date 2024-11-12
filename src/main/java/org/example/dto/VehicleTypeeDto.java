package org.example.dto;



import jakarta.persistence.*;
import org.example.entity.Company;
import org.example.entity.Transport;

import java.util.List;


/**
 * Data Transfer Object (DTO) class representing a vehicle typee.
 */
public class VehicleTypeeDto {

    private int vehicleId;


    private String vehicleType;

    private String vehicleModel;


    private String registrationNumber;

    private CompanyDto company;

    /**
     * Retrieves the company information in DTO format.
     * @return The company information in DTO format
     */
    public CompanyDto getCompany() {
        return company;
    }

    /**
     * Sets the company information in DTO format.
     * @param company The company information in DTO format to be set
     */
    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    private List<Transport> transports;

    /**
     * Retrieves the list of transport information .
     * @return The list of transport information
     */
    public List<Transport> getTransports() {
        return transports;
    }

    /**
     * Sets the list of transport .
     * @param transports The list of transport information
     */
    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    /**
     * Default constructor for VehicleTypeeDto class.
     */
    public VehicleTypeeDto() {
    }

    /**
     * Constructor for VehicleTypeeDto.
//     * @param company The company DTO associated with the vehicle
     * @param vehicleId The ID of the vehicle
     * @param vehicleType The type of the vehicle
     * @param model The model of the vehicle
     * @param registrationNumber The registration number of the vehicle
     */
    public VehicleTypeeDto(int vehicleId, String vehicleType, String model, String registrationNumber) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.vehicleModel =model;
        this.registrationNumber = registrationNumber;

    }

    /**
     * Gets the vehicle ID.
     * @return The vehicle ID.
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID.
     * @param vehicleId The vehicle ID to set.
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the vehicle type.
     * @return The vehicle type.
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the vehicle type.
     * @param vehicleType The vehicle type to set.
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Gets the vehicle model.
     * @return The vehicle model.
     */
    public String getVehicleModel() {
        return vehicleModel;
    }

    /**
     * Sets the vehicle model.
     * @param vehicleModel The vehicle model to set.
     */
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    /**
     * Gets the vehicle registration number.
     * @return The vehicle registration number.
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the vehicle registration number.
     * @param registrationNumber The registration number to set.
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }


    /**
     * Returns a string representation of the VehicleTypeeDto.
     * @return A string representation of the VehicleTypeeDto
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + vehicleId +
                ", type=" + vehicleType +
                ",model="+vehicleModel+
                ", registrationNumber='" + registrationNumber + '\'' +
                '}';
    }
}
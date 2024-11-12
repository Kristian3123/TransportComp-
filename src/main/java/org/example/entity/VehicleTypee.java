package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a type of vehicle entity.
 */
@Entity
@Table(name = "vehicle")
public class VehicleTypee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int vehicleId;

//    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "vehicle_model")
    private String vehicleModel;

    @Column(name = "registration_number")
    private String registrationNumber;
    @ManyToOne()
    @JoinColumn(name="compnyID",nullable = false)
    private Company company;

    /**
     * Sets the company associated with the vehicle.
     * @param company The company associated with the vehicle
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Gets the company associated with the vehicle.
     * @return  company The company associated with the vehicle
     */
    public Company getCompany() {
        return company;
    }
    @OneToMany(mappedBy = "vehicle")
    private List<Transport> transports;
    /**
     * Gets the list of transports associated with the vehicle.
     * @return The list of transports associated with the vehicle
     */
    public List<Transport> getTransports() {
        return transports;
    }
    /**
     * Gets the list of transports associated with the vehicle.
     * @param transports The list of transports associated with the vehicle
     */
    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }


    /**
     * Default constructor for the VehicleTypee class.
     */
    public VehicleTypee() {
    }


    /**
     * Parameterized constructor for VehicleTypee class.
     * @param company The company associated with the client
     * @param vehicleId The vehicle ID
     * @param vehicleType The vehicle type
     * @param model The vehicle мodel
     * @param registrationNumber The registration number for the vehicle
     */
    public VehicleTypee(Company company, int vehicleId, String vehicleType, String model, String registrationNumber) {
        this.company=company;
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.vehicleModel =model;
        this.registrationNumber = registrationNumber;

    }

    /**
     * Gets the ID of the vehicle.
     * @return The ID of the vehicle
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the ID of the vehicle.
     * @param vehicleId The ID of the vehicle
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the type of the vehicle.
     * @return The type of the vehicle
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the type of the vehicle.
     * @param vehicleType The type of the vehicle
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Gets the model of the vehicle.
     * @return The model of the vehicle
     */
    public String getVehicleModel() {
        return vehicleModel;
    }

    /**
     * Sets the model of the vehicle.
     * @param vehicleModel The model of the vehicle
     */
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    /**
     * Gets the registration number of the vehicle.
     * @return The registration number of the vehicle
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the registration number of the vehicle.
     * @param registrationNumber The registration number of the vehicle
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Returns a string representation of the VehicleTypee object.
     * @return A string representation of the VehicleTypee object
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
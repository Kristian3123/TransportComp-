package org.example.dto;

import jakarta.persistence.*;
import org.example.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
/**
 * DTO (Data Transfer Object) class representing transport information.
 */
public class TransportDto {

    private int transportId;

    private String startingPoint;

    private String destination;

    private CargoType cargoType;

    private BigDecimal totalWeight;

    /**
     * Getter the total weight.
     * @return The total weight as a BigDecimal.
     */
    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    /**
     * Setter the total weight.
     * @param totalWeight The total weight to be set as a BigDecimal.
     */
    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }


    private BigDecimal price;


    private LocalDate departureDate;


    private LocalDate arrivalDate;


    private boolean paymentReceived;

    private Employee driver;

    /**
     * Sets the driver for this transport.
     *
     * @param driver The employee to set as the driver for this transport.
     */
    public void setDriver(Employee driver) {
        this.driver = driver;
    }

    /**
     * Gets the driver assigned to this transport.
     *
     * @return The employee serving as the driver for this transport.
     */
    public Employee getDriver() {
        return driver;
    }

    private VehicleTypeeDto vehicle;

    private Client client;

    private Company company;

    /**
     * Sets the company associated with this object.
     *
     * @param company The company to be set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Gets the company associated with this object.
     *
     * @return The company associated with this object
     */
    public Company getCompany() {
        return company;
    }


    /**
     * Default constructor for TransportDto class.
     */
    public TransportDto() {
    }

    /**
     * Constructs a TransportDto object with provided parameters.
     * @param transportId     The unique identifier for the transport
     * @param startingPoint   The starting point of the transport
     * @param destination     The destination of the transport
     * @param cargoType       The type of cargo being transported
     * @param price           The price of the transport
     * @param paymentReceived Indicates whether payment has been received for the transport
     * @param departureDate   The departure date of the transport
     * @param arrivalDate     The arrival date of the transport
     */
    public TransportDto(int transportId, String startingPoint,
                        String destination, CargoType cargoType , BigDecimal price, boolean paymentReceived, LocalDate departureDate, LocalDate arrivalDate) {


        this.transportId = transportId;
        this.startingPoint=startingPoint;
        this.destination = destination;
        this.cargoType = cargoType;
        if (cargoType == CargoType.GOODS || cargoType == CargoType.SPECIAL_CARGO) {

            double randomWeight = new Random().nextDouble() * 1000;
            this.totalWeight =BigDecimal.valueOf(randomWeight);
        }
        this.price = price;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;


        this.paymentReceived = paymentReceived;
        this.vehicle = vehicle;
        this.client = client;
    }

    /**
     * Getter for the transport's identification number.
     * @return The transport's identification number.
     */
    public int getTransportId() {
        return transportId;
    }

    /**
     * Setter for the transport's identification number.
     * @param transportId The transport's identification number to set.
     */
    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    /**
     * Getter for the transport's starting point.
     * @return The transport's starting point.
     */
    public String getStartingPoint() {
        return startingPoint;
    }

    /**
     * Setter for the transport's starting point.
     * @param startingPoint The starting point of the transport to set.
     */
    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    /**
     * Getter for the transport's destination.
     * @return The transport's destination.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Setter for the transport's destination.
     * @param destination The destination of the transport to set.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Getter for the transport's cargo type.
     * @return The transport's cargo type.
     */
    public CargoType getCargoType() {
        return cargoType;
    }

    /**
     * Setter for the transport's cargo type.
     * @param cargoType The cargo type of the transport to set.
     */
    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    /**
     * Getter for the transport's price.
     * @return The transport's price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Setter for the transport's price.
     * @param price The price of the transport to set.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Getter for the transport's departure date.
     * @return The transport's departure date.
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    /**
     * Setter for the transport's departure date.
     * @param departureDate The departure date of the transport to set.
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Getter for the transport's arrival date.
     * @return The transport's arrival date.
     */
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Setter for the transport's arrival date.
     * @param arrivalDate The arrival date of the transport to set.
     */
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Getter for checking if payment is received for the transport.
     * @return True if payment is received; otherwise, false.
     */
    public boolean isPaymentReceived() {
        return paymentReceived;
    }

    /**
     * Setter for marking if payment is received for the transport.
     * @param paymentReceived The status of payment for the transport (true/false).
     */
    public void setPaymentReceived(boolean paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    /**
     * Getter for the transport's vehicle.
     * @return The transport's vehicle.
     */
    public VehicleTypeeDto getVehicle() {
        return vehicle;
    }

    /**
     * Sets the vehicle for the transport.
     * @param vehicle The VehicleTypeeDto object to be set
     */
    public void setVehicle(VehicleTypeeDto vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Retrieves the client for the transport.
     * @return The ClientDto object associated with the transport
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the client for the transport.
     * @param client The ClientDto object to be set
     */
    public void setClient(Client client) {
        this.client = client;
    }


    /**
     * Returns a string representation of the TransportDto object.
     * @return A string containing information about the TransportDto object.
     */
    @Override
    public String toString() {
        return "Transport{" +
                "company=" + company +
                "transportId=" + transportId +
                ", startingPoint='" + startingPoint + '\'' +
                ", destination='" + destination + '\'' +
                ", cargoType=" + cargoType +
                ", price=" + price +
                ", totalWeight=" + totalWeight +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", paymentReceived=" + paymentReceived +
                ", driver=" + driver +
                ", vehicle=" + vehicle +
                ", client=" + client +
                '}';
    }
}

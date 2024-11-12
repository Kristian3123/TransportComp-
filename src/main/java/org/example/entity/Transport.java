package org.example.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

/**
 * Represents a transport entity.
 */
@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transport_id")
    private int transportId;

    @Column(name = "starting_point")
    private String startingPoint;

    @Column(name = "destination")
    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_type")
    private CargoType cargoType;

    @Column(name = "total_weight")
    private BigDecimal totalWeight;

    /**
     * Returns the total weight of the transport.
     * @return The total weight of the transport.
     */
    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the total weight of the transport.
     * @param totalWeight The total weight of the transport to be set.
     */
    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "payment_received")
    private boolean paymentReceived;
    @ManyToOne()
    @JoinColumn(name="EmployeID",nullable = false)
    private Employee driver;

    public void setDriver(Employee driver) {
        this.driver = driver;
    }
    public Employee getDriver() {
        return driver;
    }
    @ManyToOne()
    @JoinColumn(name = "vehicle_id")
    private VehicleTypee vehicle;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne()
    @JoinColumn(name="compnyID",nullable = false)
    private Company company;

    /**
     * Sets the company associated with this employee.
     * @param company The company to be set for the employee
     */
    public void setCompany(Company company) {
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
     * Default constructor for Transport class.
     */
    public Transport() {
    }

    /**
     * Constructs a Transport object.
     *
     * @param company         The company associated with the transport.
     * @param driver          The driver of the transport.
     * @param vehicle         The vehicle used for the transport.
     * @param client          The client associated with the transport.
     * @param transportId     The ID of the transport.
     * @param startingPoint   The starting point of the transport.
     * @param destination     The destination of the transport.
     * @param cargoType       The type of cargo for the transport.
     * @param price           The price of the transport.
     * @param paymentReceived Indicates if payment for the transport is received.
     * @param departureDate   The departure date of the transport.
     * @param arrivalDate     The arrival date of the transport.
     */
    public Transport(Company company,Employee driver, VehicleTypee vehicle,Client client,int transportId, String startingPoint, String destination, CargoType cargoType , BigDecimal price,
                     boolean paymentReceived, LocalDate departureDate, LocalDate arrivalDate) {
        this.company=company;
        this.driver=driver;

        this.vehicle=vehicle;
        this.client=client;
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
     * Gets the transport ID.
     * @return The transport ID
     */
    public int getTransportId() {
        return transportId;
    }

    /**
     * Sets the transport ID.
     * @param transportId The transport ID to set
     */
    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    /**
     * Gets the starting point of the transport.
     * @return The starting point
     */
    public String getStartingPoint() {
        return startingPoint;
    }

    /**
     * Sets the starting point of the transport.
     * @param startingPoint The starting point to set
     */
    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    /**
     * Gets the destination of the transport.
     * @return The destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination of the transport.
     * @param destination The destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Gets the cargo type of the transport.
     * @return The cargo type
     */
    public CargoType getCargoType() {
        return cargoType;
    }

    /**
     * Sets the cargo type of the transport.
     * @param cargoType The cargo type to set
     */
    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    /**
     * Gets the price of the transport.
     * @return The price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the transport.
     * @param price The price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the departure date of the transport.
     * @return The departure date
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the departure date of the transport.
     * @param departureDate The departure date to set
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Gets the arrival date of the transport.
     * @return The arrival date
     */
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Sets the arrival date of the transport.
     * @param arrivalDate The arrival date to set
     */
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Checks if the payment for the transport is received.
     * @return True if payment is received, otherwise false
     */
    public boolean isPaymentReceived() {
        return paymentReceived;
    }

    /**
     * Sets whether the payment for the transport is received.
     * @param paymentReceived True if payment is received, otherwise false
     */
    public void setPaymentReceived(boolean paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    /**
     * Gets the type of vehicle used for the transport.
     * @return The type of vehicle
     */
    public VehicleTypee getVehicle() {
        return vehicle;
    }

    /**
     * Sets the type of vehicle used for the transport.
     * @param vehicle The type of vehicle to set
     */
    public void setVehicle(VehicleTypee vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Gets the client associated with the transport.
     * @return The client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the client associated with the transport.
     * @param client The client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }


    /**
     * Returns the string representation of the Transport object.
     * @return The string representation of the Transport object.
     */
    @Override
    public String toString() {
        return "Transport{" +

                "transportId=" + transportId +
                ", startingPoint='" + startingPoint + '\'' +
                ", destination='" + destination + '\'' +
                ", cargoType=" + cargoType +
                ", price=" + price +
                ", totalWeight=" + totalWeight +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", paymentReceived=" + paymentReceived +
                "company=" + company +
                ", driver=" + driver +
                ", vehicle=" + vehicle +
                ", client=" + client +
                '}';
    }
}

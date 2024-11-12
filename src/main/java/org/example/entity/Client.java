package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/**
 * Represents a client entity.
 */
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clientId")
    private int clientId;

    @NotBlank(message = "Client name cannot be blank!")
//    @Size(max = 20, message = "Employee name has to be with up to 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Client name has to start with capital letter!")
    @Column(name = "name", nullable = false)
    private String clientName;

    @Size(max = 10, message = "Contact info must have a maximum of 10 characters")
    @Column(name = "contact_info")
    private String contactInfo;

    @ManyToOne()
    @JoinColumn(name="compnyID",nullable = false)
    private Company company;

    /**
     * Sets the company associated with the client.
     * @param company The company associated with the client
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Gets the company associated with the client.
     * @return company The company associated with the client
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Default constructor for Client class.
     */
    public Client() {
    }

    /**
     * Parameterized constructor for Client class.
     * @param company The company associated with the client
     * @param clientId The client ID
     * @param clientName The client name
     * @param contactInfo The contact information for the client
     */
    public Client(Company company,int clientId, String clientName, String contactInfo) {
        this.company=company;
        this.clientId = clientId;
        this.clientName = clientName;
        this.contactInfo = contactInfo;
    }
    
        /**
     * Gets the client's unique ID.
     * @return The client ID
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets the client's unique ID.
     * @param clientId The client ID
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the client's name.
     * @return The client's name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the client's name.
     * @param clientName The client's name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Gets the client's contact information.
     * @return The client's contact information
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the client's contact information.
     * @param contactInfo The client's contact information
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * Returns a string representation of the Client object.
     * @return A string representation of the Client object.
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + clientId +
                ", name='" + clientName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
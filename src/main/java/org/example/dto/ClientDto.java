package org.example.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.example.entity.Company;

/**
 * Data Transfer Object (DTO) representing a client.
 */
public class ClientDto {

    private int clientId;

    private String clientName;

    private String contactInfo;

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
     * Default constructor for ClientDto class.
     */
    public ClientDto() {
    }

    /**
     * Parameterized constructor for ClientDto.
//     * @param company The company associated with the client
     * @param clientId The client's ID
     * @param clientName The client's name
     * @param contactInfo The client's contact information
     */
    public ClientDto(int clientId, String clientName, String contactInfo) {
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
     * Returns a string representation of the ClientDto object.
     * @return A string representation of the ClientDto object
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

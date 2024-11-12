package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Client;
import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
/**
 * Data Access Object (DAO) for managing Client entities.
 */
public class ClientDao {

    /**
     * Creates a new Client record in the database.
     *
     * @param client The Client object to be created
     */
    public static void createClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(client);
                transaction.commit();
            } catch (Exception e) {

                System.err.println("\n" + "Error recording client: " + e.getMessage());
                transaction.rollback();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves a Client by its unique identifier (ID).
     *
     * @param id The ID of the Client to retrieve
     * @return The Client object if found, otherwise null
     */
    public static Client getClientById(long id) {
        Client client;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.get(Client.class, id);
            transaction.commit();
        }
        return client;
    }

    /**
     * Retrieves all Client records from the database.
     *
     * @return A list of Client objects
     */
    public static List<Client> getClient() {
        List<Client> clients;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            clients = session.createQuery("Select e From Employee e", Client.class)
                    .getResultList();
            transaction.commit();
        }
        return  clients;
    }

    /**
     * Updates an existing Client record in the database.
     *
     * @param client The Client object to be updated
     */
    public static void updateClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(client);
            transaction.commit();
        }
    }

    /**
     * Deletes an existing Client record from the database.
     *
     * @param client The Client object to be deleted
     */
    public static void deleteClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
        }
    }

}

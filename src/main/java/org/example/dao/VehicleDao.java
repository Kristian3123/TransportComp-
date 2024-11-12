package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Employee;
import org.example.entity.VehicleTypee;
import org.example.exceptions.EmployeeNotFoundException;
import org.example.exceptions.VehicleNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Data Access Object (DAO) class for handling VehicleTypee entities.
 */
public class VehicleDao {

    /**
     * Creates a new vehicle in the database.
     *
     * @param vehicle The VehicleTypee object to be created.
     */
    public static void createVehicle(VehicleTypee vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(vehicle);
                transaction.commit();
            } catch (Exception e) {

                System.err.println("\n" + "Error recording vehicle: " + e.getMessage());
                transaction.rollback();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves a vehicle from the database based on the provided ID.
     *
     * @param id The ID of the vehicle to retrieve.
     * @return The retrieved VehicleTypee object.
     */
    public static VehicleTypee getVehicleById(long id) {
        VehicleTypee vehicle;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.get(VehicleTypee.class, id);
            transaction.commit();
        }
        return vehicle;
    }

    /**
     * Retrieves all vehicles from the database.
     *
     * @return A list of all VehicleTypee objects.
     */
    public static List<VehicleTypee> getVehicle() {
        List<VehicleTypee> vehicle;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.createQuery("Select e From Employee e", VehicleTypee.class)
                    .getResultList();
            transaction.commit();
        }
        return vehicle;
    }

    /**
     * Updates an existing vehicle in the database.
     *
     * @param vehicle The VehicleTypee object to be updated.
     * @throws VehicleNotFoundException if the vehicle is not found.
     */
    public static void updateVehicle(VehicleTypee vehicle) throws VehicleNotFoundException {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.saveOrUpdate(vehicle);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new VehicleNotFoundException("Error updating vehicle: " + e.getMessage());
            }
        } catch (Exception ex) {
            throw new VehicleNotFoundException("Error in updating vehicle: " + ex.getMessage());
        }
    }

    /**
     * Deletes an existing vehicle from the database.
     *
     * @param vehicle The VehicleTypee object to be deleted.
     * @throws VehicleNotFoundException if the vehicle is not found.
     */
    public static void deleteVehicle(VehicleTypee vehicle) throws VehicleNotFoundException{
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(vehicle);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new VehicleNotFoundException("Error deleting vehicle: " + e.getMessage());
            }
        } catch (Exception ex) {
            throw new VehicleNotFoundException("Error in deleting vehicle: " + ex.getMessage());
        }
    }
}

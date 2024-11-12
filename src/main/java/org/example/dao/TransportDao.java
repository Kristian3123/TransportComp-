package org.example.dao;

import com.sun.jdi.connect.spi.Connection;
import jakarta.persistence.criteria.*;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Employee;
import org.example.entity.Qualification;
import org.example.entity.Transport;
import org.example.exceptions.CompanyNotFoundException;
import org.example.exceptions.FileHandlingException;
import org.example.exceptions.TransportRecordNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.beans.Statement;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.io.*;

import static org.example.configuration.SessionFactoryUtil.sessionFactory;

/**
 * Data Access Object (DAO) class for managing Transport entities in the database.
 */
public class TransportDao {

    /**
     * Creates a new transport record in the database.
     * @param transport The Transport object to be created
     */
    public static void createTransport(Transport transport) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(transport);
                transaction.commit();
            } catch (Exception e) {

                System.err.println("\n" + "Error recording transport: " + e.getMessage());
                transaction.rollback();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves a Transport entity from the database based on its ID.
     * @param id The ID of the Transport entity to retrieve
     * @return The Transport object if found, otherwise null
     */
    public static Transport getTransportById(long id) {
        Transport transport;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transport = session.get(Transport.class, id);
            transaction.commit();
        }
        return transport;
    }

    /**
     * Retrieves a list of all Transport entities from the database.
     * @return A list of Transport objects
     */
    public static List<Transport> getTransport() {
        List<Transport> transports;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transports = session.createQuery("Select e From Employee e", Transport.class)
                    .getResultList();
            transaction.commit();
        }
        return transports;
    }

    /**
     * Updates the transport information in the database.
     * If the transport does not exist, a TransportRecordNotFoundException will be thrown.
     * @param transport The transport object to update
     * @throws TransportRecordNotFoundException If an error occurs while updating the transport or if the transport does not exist
     */
    public static void updateTransport(Transport transport) throws TransportRecordNotFoundException {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.saveOrUpdate(transport);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new TransportRecordNotFoundException("Error updating transport: " + e.getMessage());
            }
        } catch (Exception ex) {
            throw new TransportRecordNotFoundException("Error in updating transport: " + ex.getMessage());
        }
    }

    /**
     * Deletes the transport from the database.
     * If the transport does not exist, a TransportRecordNotFoundException will be thrown.
     * @param transport The transport object to delete
     * @throws TransportRecordNotFoundException If an error occurs while deleting the transport or if the transport does not exist
     */
    public static void deleteTransport(Transport transport) throws TransportRecordNotFoundException {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(transport);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new TransportRecordNotFoundException("Error deleting transport: " + e.getMessage());
            }
        } catch (Exception ex) {
            throw new TransportRecordNotFoundException("Error in deleting transport: " + ex.getMessage());
        }
    }

    /**
     * Retrieves a set of Transport entities sorted by destination.
     * @return A set of Transport objects sorted by destination
     */
    public static Set<Transport> getTransportSortedByDestination() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Transport t ";
//                    "WHERE t.paymentReceived = :isPaymentReceived";
            Query<Transport> query = session.createQuery(hql, Transport.class);
//            query.setParameter("isPaymentReceived", true);
            List<Transport> transports = query.getResultList();


            Collections.sort(transports, Comparator.comparing(Transport::getDestination).reversed());

            return new LinkedHashSet<>(transports);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new HashSet<>();
        }
    }

    /**
     * Retrieves a set of Transport entities filtered by destination.
     * @param destination The destination to filter the transports
     * @return A set of Transport objects with the specified destination
     */
    public static Set<Transport> getTransportByDestination(String destination) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Transport t " +
                    "where t.destination = :destination ";
//                    "AND t.paymentReceived = :isPaymentReceived";
            Query<Transport> query = session.createQuery(hql, Transport.class);
            query.setParameter("destination", destination);
//            query.setParameter("isPaymentReceived", true);
            return new HashSet<>(query.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();

            return new HashSet<>();
        }
    }

    /**
     * Writes the transport data for each company to a specified file.
     * @param transports The set of transports to be written to the file
     * @param filePath   The path of the file to write the transport data
     * @throws FileHandlingException If an error occurs during file handling
     */
    public static void writeTransportFile(Set<Transport> transports, String filePath) throws FileHandlingException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
//            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath,true)))) {// Or this fornon stop writing in the file
            Map<Long, List<Transport>> companyTransportsMap = new HashMap<>();


            for (Transport transport : transports) {
                Long companyId = transport.getCompany().getCompanyId();
                companyTransportsMap.computeIfAbsent(companyId, k -> new ArrayList<>()).add(transport);
            }

            for (Map.Entry<Long, List<Transport>> entry : companyTransportsMap.entrySet()) {
                Long companyId = entry.getKey();
                List<Transport> companyTransports = entry.getValue();

                writer.println("Company ID: " + companyId);
                for (Transport transport : companyTransports) {
                    writer.println(transport.toString());
                }
                writer.println();
            }

            System.out.println("\nShipment data for each company was successfully saved to the file.");
        } catch (IOException e) {
            throw new FileHandlingException("Error writing data to file: " + e.getMessage());
        }
    }

    /**
     * Reads transport data from a file and displays it.
     * @param filePath The path to the file containing transport data
     * @throws FileHandlingException If an error occurs while reading the file
     */
    public static void readTransportFile(String filePath) throws FileHandlingException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new FileHandlingException("Error in deleting transport: " + e.getMessage());
        }
    }


    /**
     * Reads various references related to transports from the database and displays the results.
     * This method performs multiple database queries to gather and display information
     * such as total transports, total income, employee transports, income from transports,
     * and income or losses of companies within a specified date range.
     */
    public static void ReadingReferences() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Query<Object[]> TotalTransports = session.createQuery(
                    "select COUNT(t), c.companyId " +
                            "from Transport t " +
                            "INNER JOIN t.driver e " +
                            "INNER JOIN e.company c " +
                            "where t.paymentReceived = :isPaymentReceived " +
                            "GROUP BY c.companyId", Object[].class
            );
            TotalTransports.setParameter("isPaymentReceived", true);
            List<Object[]> transportsByCompanyList = TotalTransports.getResultList();

            for (Object[] result : transportsByCompanyList) {
                Long totalTransports = (Long) result[0];
                long companyId = (long) result[1];

                System.out.println("Company ID: " + companyId + ", Total number of transports carried out: " + totalTransports);
            }

            Query<Object[]> TotalIncomeFromTransports = session.createQuery(
                    "select SUM (t.price), c.companyId " +
                            "from Transport t " +
                            "LEFT JOIN t.driver e " +
                            "LEFT JOIN e.company c " +
                            "where t.paymentReceived = :isPaymentReceived " +
                            "GROUP BY c.companyId", Object[].class
            );
            TotalIncomeFromTransports.setParameter("isPaymentReceived", true);
            List<Object[]> totalRevenueList = TotalIncomeFromTransports.getResultList();

            for (Object[] result : totalRevenueList) {
                BigDecimal totalRevenue = (BigDecimal) result[0];
                long companyId = (long) result[1];

                System.out.println("Company ID: " + companyId + ", Total income of carried out transports: " + totalRevenue);
            }


            List<Object[]> EmployeeTransports = session.createNativeQuery(
                            "select e.employeeId, COUNT(t.EmployeID) , e.companyID " +
                                    "from Employee e " +
                                    "LEFT JOIN Transport t ON e.employeeId = t.EmployeID " +
                                    "LEFT JOIN Company c ON e.companyID = c.compnyID " +
                                    "where t.payment_received = :isPaymentReceived " +
                                    "GROUP BY e.employeeId,e.companyID")
                    .setParameter("isPaymentReceived", true)
                    .getResultList();
            for (Object[] result : EmployeeTransports) {
                System.out.println("Employee ID: " + result[0] + ", Number of transports: " + result[1] + ", Company ID: " + result[2]);
            }


            List<Object[]> IncomeFromEmployee = session.createNativeQuery(
                            "select e.employeeId, SUM(t.price) ,e.companyID " +
                                    "from Employee e " +
                                    "LEFT JOIN Transport t ON e.employeeId = t.EmployeID " +
                                    "AND t.payment_received = :isPaymentReceived " +
                                    "LEFT JOIN Company c ON e.companyID = c.compnyID " +
//                                    "where t.payment_received = :isPaymentReceived " +
                                    "GROUP BY e.companyID,e.employeeId")
                    .setParameter("isPaymentReceived", true)
                    .getResultList();

            for (Object[] result : IncomeFromEmployee) {
                System.out.println("Employee ID: " + result[0] + ", Income: " + result[1] + ", Company ID:" + result[2]);
            }


            LocalDate startDate = LocalDate.of(2024, 1, 1);
            LocalDate endDate = LocalDate.of(2024, 12, 31);

            Query<Object[]> CompanyIncome = session.createQuery(
                    "select SUM(t.price) - SUM(e.salary), c.companyId " +
                            "from Transport t " +
                            "INNER JOIN t.driver e " +
                            "INNER JOIN e.company c " +
                            "where t.paymentReceived = :isPaymentReceived " +
                            "AND (t.departureDate BETWEEN :startDate AND :endDate OR t.arrivalDate BETWEEN :startDate AND :endDate) " +
                            "GROUP BY c.companyId", Object[].class

            );

            CompanyIncome.setParameter("isPaymentReceived", true);
            CompanyIncome.setParameter("startDate", startDate);
            CompanyIncome.setParameter("endDate", endDate);
            List<Object[]> companyIncomeList = CompanyIncome.getResultList();
            for (Object[] result : companyIncomeList) {
                BigDecimal companyIncomes = (BigDecimal) result[0];
                Long companyId = (Long) result[1];


                if (companyIncomes.compareTo(BigDecimal.ZERO) > 0) {
                    System.out.println("Company ID: " + companyId + ", Income: " + companyIncomes);
                } else if (companyIncomes.compareTo(BigDecimal.ZERO) == 0) {
                    System.out.println("Company ID " + companyId + " has no income.");
                } else {
                    BigDecimal loss = companyIncomes.abs();
                    System.out.println("Company ID " + companyId + " is facing a loss of: " + loss);
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



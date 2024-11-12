package org.example.dao;


import org.apache.logging.log4j.util.PropertySource;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.ClientDto;
import org.example.dto.EmployeeDto;
import org.example.dto.TransportDto;
import org.example.dto.VehicleTypeeDto;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.exceptions.CompanyNotFoundException;
import org.example.exceptions.EmployeeNotFoundException;
import org.example.exceptions.TransportRecordNotFoundException;
import org.example.exceptions.VehicleNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.configuration.SessionFactoryUtil.sessionFactory;

/**
 *Data Access Object (DAO) class for managing Company entities in the database.
 */
public class CompanyDao {

    /**
     * Creates a new company record in the database.
     * @param company The company entity to be created
     */
    public static void createCompany(Company company) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
           Transaction transaction = session.beginTransaction();
            try {
                session.save(company);
                transaction.commit();
            } catch (Exception e) {

                System.err.println("\n" + "Error recording company: " + e.getMessage());
                transaction.rollback();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves a company entity by its ID from the database.
     * @param id The ID of the company to retrieve
     * @return The company entity
     */
    public static Company getCompanyById(long id) {
        Company company;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(Company.class, id);
            transaction.commit();
        }
        return company;
    }

    public static List<Company> getCompanies() {
        List<Company> companies;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session.createQuery("Select c From Company c", Company.class)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }

    /**
     * Updates an existing company record in the database.
     * @param company The employee to be updated
     * @throws CompanyNotFoundException If the employee to be updated is not found
     */
    public static void updateCompany(Company company) throws CompanyNotFoundException {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.saveOrUpdate(company);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new CompanyNotFoundException("Error updating company: " + e.getMessage());
            }
        } catch (Exception ex) {
            throw new CompanyNotFoundException("Error in updating company: " + ex.getMessage());
        }
    }

    /**
     * Deletes a company record from the database.
     * @param company The employee to be deleted
     * @throws CompanyNotFoundException If the employee to be deleted is not found
     */
    public static void deleteCompany(Company company) throws CompanyNotFoundException {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(company);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new CompanyNotFoundException("Error deleting transport: " + e.getMessage());
            }
        } catch (Exception ex) {
            throw new CompanyNotFoundException("Error in deleting transport: " + ex.getMessage());
        }
    }

    /**
     * Retrieves a sorted set of companies by their name.
     * The companies are sorted in ascending order by name.
     * @return A sorted set of companies by name
     */
public static Set<Company> getSortedCompaniesByName() {
    try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
        String hql = "from Company";
        Query<Company> query = session.createQuery(hql, Company.class);
        List<Company> companies = query.getResultList();

        Collections.sort(companies, (c1, c2) -> c1.getCompanyName().compareTo(c2.getCompanyName()));

        return new LinkedHashSet<>(companies);
    } catch (Exception e) {
        e.printStackTrace();
        return new HashSet<>();
    }
}

    /**
     * Retrieves a set of companies filtered by the provided company name.
     * @param CompanyName The name used for filtering the companies
     * @return A set of companies matching the provided name
     */
public static Set<Company> getCompaniesByName (String CompanyName) {
    try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
        String hql = "FROM Company";
        Query<Company> query = session.createQuery(hql, Company.class);
        List<Company> companies = query.getResultList();
        Set<Company> filteredCompanies = new HashSet<>();
        for (Company company : companies) {
            String companyName = company.getCompanyName();
            if (companyName != null && companyName.contains(CompanyName)) {
                filteredCompanies.add(company);
            }
        }
            return filteredCompanies;
    }catch (HibernateException e) {
            e.printStackTrace();
            return new HashSet<>();
        }

    }

    /**
     * Retrieves companies sorted by their income.
     * @return Set of companies sorted by income. If an exception occurs during database access, an empty set is returned.
     */
public static Set<Company> getSortedCompaniesByIncome() {
    try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
        Query<Object[]> companyIncomeQuery = session.createQuery(
                "select SUM(t.price) - SUM(e.salary), c " +
                        "from Transport t " +
                        "INNER JOIN t.driver e " +
                        "INNER JOIN e.company c " +
                        "where t.paymentReceived = :isPaymentReceived " +
                        "GROUP BY c ",
                Object[].class
        );
        companyIncomeQuery.setParameter("isPaymentReceived", true);
        List<Object[]> companyIncomeList = companyIncomeQuery.getResultList();
        List<Company> companies = new ArrayList<>();

        for (Object[] result : companyIncomeList) {
            BigDecimal companyIncome = (BigDecimal) result[0];
            Company company = (Company) result[1];

            company.setIncome(companyIncome);
            companies.add(company);
        }
        companies.sort(Comparator.comparing(Company::getIncome));

        return new HashSet<>(companies);
    } catch (HibernateException e) {
        e.printStackTrace();
        return new HashSet<>();
    }
}

    /**
     * Retrieves a set of companies whose income is greater than or equal to the provided minimum income value.
     * @param minIncome The minimum income value used for filtering the companies
     * @return A set of companies with income greater than or equal to the minimum income
     */
public static Set<Company> getCompaniesByIncome(BigDecimal minIncome) {
    try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
        Query<Object[]> companyIncomeQuery = session.createQuery(
                "select SUM(t.price) - SUM(e.salary), c " +
                        "from Transport t " +
                        "INNER JOIN t.driver e " +
                        "INNER JOIN e.company c " +
                        "where t.paymentReceived = :isPaymentReceived " +
                        "GROUP BY c ",
                Object[].class
        );
        companyIncomeQuery.setParameter("isPaymentReceived", true);
        List<Object[]> companyIncomeList = companyIncomeQuery.getResultList();
        Set<Company> filteredCompanies = new HashSet<>();

        for (Object[] result : companyIncomeList) {
            BigDecimal companyIncome = (BigDecimal) result[0];
            Company company = (Company) result[1];

            company.setIncome(companyIncome);
            if (companyIncome != null && companyIncome.compareTo(minIncome) >= 0) {
                filteredCompanies.add(company);
            }
        }

        return filteredCompanies;
    } catch (HibernateException e) {
        e.printStackTrace();
        return new HashSet<>();
    }
}

    /**
     * Retrieves a list of employee data transfer objects (DTOs) associated with a specific company.
     * @param id The ID of the company to retrieve employee DTOs for
     * @return A list of employee DTOs associated with the specified company
     */
    public static List<EmployeeDto> getCompanyEmployeesDTO(long id) {
        List<EmployeeDto> employees;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery(
                            "select new org.example.dto.EmployeeDto(e.employeeId, e.employeeName, e.salary, e.qualification) from Employee e" +
                                    " join e.company c " +
                                    "where c.companyId = :id",
                            EmployeeDto.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }

        return employees;
    }

    /**
     * Retrieves a list of client data transfer objects (DTOs) associated with a specific company.
     * @param id The ID of the company to retrieve client DTOs for
     * @return A list of client DTOs associated with the specified company
     */
    public static List<ClientDto> getCompanyClientsDto(long id) {
        List<ClientDto> clients;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            clients = session.createQuery(
                            "select new org.example.dto.ClientDto(c.clientId, c.clientName, c.contactInfo) from Client c" +
                                    " join c.company company " +
                                    "where company.companyId = :id",
                            ClientDto.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }

        return clients;
    }

    /**
     * Retrieves a list of vehicle data transfer objects (DTOs) associated with a specific company.
     * @param id The ID of the company to retrieve vehicle DTOs for
     * @return A list of vehicle DTOs associated with the specified company
     */
    public static List<VehicleTypeeDto> getCompanyVehicleTypesDTO(long id) {
        List<VehicleTypeeDto> vehicleTypes;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicleTypes = session.createQuery(
                            "select new org.example.dto.VehicleTypeeDto(v.vehicleId, v.vehicleType, v.vehicleModel, v.registrationNumber) from VehicleTypee v" +
                                    " join v.company c " +
                                    "where c.companyId = :id",
                            VehicleTypeeDto.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }

        return vehicleTypes;
    }

    /**
     * Retrieves a list of transport data transfer objects (DTOs) associated with a specific company.
     * @param id The ID of the company to retrieve transport DTOs for
     * @return A list of transport DTOs associated with the specified company
     */
    public static List<TransportDto> getCompanyTransportsDto(long id) {
        List<TransportDto> transports;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transports = session.createQuery(
                            "select new org.example.dto.TransportDto(t.transportId, t.startingPoint, t.destination, t.cargoType, t.price, t.paymentReceived,t.departureDate, t.arrivalDate) " +
                                    "from Transport t " +
                                    "JOIN t.driver d " +
                                    "JOIN d.company c " +
                                    "where c.companyId = :id",
                            TransportDto.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }

        return transports;
    }


}

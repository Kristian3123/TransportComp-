package org.example.dao;


import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.entity.Qualification;
import org.example.exceptions.EmployeeNotFoundException;
import org.example.exceptions.TransportRecordNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

/**
 * Data Access Object (DAO) for managing Employee entities.
 */
public class EmployeeDao {
    //, Set<Qualification> qualifications

    /**
     * Creates a new employee record in the database.
     * @param employee The employee to be created
     */
    public static void createEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(employee);
                transaction.commit();
            } catch (Exception e) {

                System.err.println("\n" + "Error recording employee: " + e.getMessage());
                transaction.rollback();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves an employee by their ID from the database.
     * @param id The ID of the employee
     * @return The employee corresponding to the given ID
     */
    public static Employee getEmployeeById(long id) {
        Employee employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            transaction.commit();
        }
        return employee;
    }

    /**
     * Retrieves a list of all employees from the database.
     * @return List of all employees
     */
    public static List<Employee> getEmployees() {
        List<Employee> employees;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery("Select e From Employee e", Employee.class)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }

    /**
     * Updates an existing employee record in the database.
     * @param employee The employee to be updated
     * @throws EmployeeNotFoundException If the employee to be updated is not found
     */
    public static void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.saveOrUpdate(employee);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new EmployeeNotFoundException("Error updating employee: " + e.getMessage());
            }
        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Error in updating employee: " + ex.getMessage());
        }
    }

    /**
     * Deletes an employee record from the database.
     * @param employee The employee to be deleted
     * @throws EmployeeNotFoundException If the employee to be deleted is not found
     */
    public static void deleteEmployee(Employee employee) throws EmployeeNotFoundException {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(employee);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new EmployeeNotFoundException("Error deleting employee: " + e.getMessage());
            }
        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Error in deleting employee: " + ex.getMessage());
        }
    }

    /**
     * Retrieves employees sorted by their qualification.
     * @return Set of employees sorted by qualification
     */
    public static Set<Employee> getEmployeesSortedByQualification() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Employee e ORDER BY e.qualification";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            List<Employee> employees = query.getResultList();

            Collections.sort(employees, Comparator.comparing(Employee::getQualification));

            return new LinkedHashSet<>(employees);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new HashSet<>();
        }
    }

    /**
     * Retrieves employees by a specific qualification.
     * @param qualification The qualification to filter employees by
     * @return Set of employees with the specified qualification
     */
    public static Set<Employee> getEmployeesByQualification(Qualification qualification) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Employee e where e.qualification = :qual ";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("qual", qualification);
            return new HashSet<>(query.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();

            return new HashSet<>();
        }
    }

    /**
     * Retrieves employees sorted by their salary.
     * @return Set of employees sorted by salary
     */
    public static Set<Employee> getEmployeesSortedBySalary() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Employee  ";
//        ORDER BY e.company.companyId,e.salary
            Query<Employee> query = session.createQuery(hql, Employee.class);
            List<Employee> employees = query.getResultList();
            employees.sort(Comparator.comparing(Employee::getSalary));
            return new LinkedHashSet<>(employees);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new HashSet<>();
        }
    }

    /**
     * Retrieves employees within a specific salary range.
     * @param minSalary The minimum salary value
     * @param maxSalary The maximum salary value
     * @return Set of employees within the specified salary range
     */
    public static Set<Employee> getEmployeesBySalary(double minSalary, double maxSalary) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Employee e where e.salary BETWEEN :minSalary AND :maxSalary";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("minSalary", minSalary);
            query.setParameter("maxSalary", maxSalary);
            return new HashSet<>(query.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();

            return new HashSet<>();
        }
    }

}


//    public static List<Employee> getEmployeesBySalary(double minSalary, double maxSalary) {
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            String hql = "FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary";
//            Query<Employee> query = session.createQuery(hql, Employee.class);
//            query.setParameter("minSalary", minSalary);
//            query.setParameter("maxSalary", maxSalary);
//            return query.getResultList();
//        }
//    }
//
//        public static List<Employee> getEmployeesByQualification(Qualification qualification) {
//            try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//                Transaction transaction = session.beginTransaction();
//
//                List<Employee> employees = session.createQuery(
//                                "SELECT e FROM Employee e WHERE e.qualification = :qual",
//                                Employee.class)
//                        .setParameter("qual", qualification)
//                        .getResultList();
//
//                transaction.commit();
//                return employees;
//            }
//        }


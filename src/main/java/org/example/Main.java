package org.example;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.*;
import org.example.dto.*;
import org.example.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        SessionFactoryUtil.getSessionFactory().openSession();



        Company company = new Company(1,"ABC Transport");
        Company company1 = new Company(2,"Fast Transport");
        Company company2 = new Company(3,"BEST Transport");


        CompanyDao.createCompany(company);
        CompanyDao.createCompany(company1);
        CompanyDao.createCompany(company2);

        Employee employee =new Employee(company,1, "Ivan",BigDecimal.valueOf(1500), Qualification.SPECIAL_GOODS_TRANSPORT);
        Employee employee1 =new Employee(company1,1, "Andreq",BigDecimal.valueOf(2500), Qualification.GOODS_TRANSPORT);
        Employee employee2 =new Employee(company,2, "Bob",BigDecimal.valueOf(2500), Qualification.PASSENGER_TRANSPORT);
        Employee employee3 =new Employee(company2,1, "Ivana",BigDecimal.valueOf(1000), Qualification.PASSENGER_TRANSPORT);

//          employee1.setCompany(company);
          EmployeeDao.createEmployee(employee);
          EmployeeDao.createEmployee(employee1);
          EmployeeDao.createEmployee(employee2);
          EmployeeDao.createEmployee(employee3);

//        EmployeeDao.deleteEmployee(employee1);
//        EmployeeDao.updateEmployee(employee);
//        session.save(employee);

        VehicleTypee vehicleTypee=new VehicleTypee(company,1, "kamion//bql","sivic","CB3052");
        VehicleTypee vehicleTypee1=new VehicleTypee(company1,1, "tcisterna","Opell","PB2430");
        VehicleTypee vehicleTypee2=new VehicleTypee(company2,1, "kamion//bql","Opell","PB2430");

        VehicleDao.createVehicle(vehicleTypee);
        VehicleDao.createVehicle(vehicleTypee1);
        VehicleDao.createVehicle(vehicleTypee2);

        Client client=new Client(company,1, "Rojur","0889036251");
        Client client1=new Client(company1,1, "Adam","0890023625");
        Client client2=new Client(company2,1, "Bojidar","0880152363");

        ClientDao.createClient(client);
        ClientDao.createClient(client1);
        ClientDao.createClient(client2);


        Transport transport=new Transport(company,employee,vehicleTypee,client,1,"Sofia","Varna", CargoType.GOODS,BigDecimal.valueOf(3000),
                true,LocalDate.now(), LocalDate.now().plusDays(3));
        Transport transport1=new Transport(company,employee,vehicleTypee,client,2,"Plovdiv","Astra", CargoType.PASSENGERS,BigDecimal.valueOf(1000),
                false,LocalDate.now(), LocalDate.now().plusDays(5));
        Transport transport2=new Transport(company1,employee1,vehicleTypee1,client1,1,"Burgas","Vraca", CargoType.SPECIAL_CARGO,BigDecimal.valueOf(500),
                true,LocalDate.now(), LocalDate.now().plusDays(2));
        Transport transport3=new Transport(company,employee2,vehicleTypee,client,3,"Vraca","Dublin", CargoType.GOODS,BigDecimal.valueOf(5000),
                true,LocalDate.now(), LocalDate.now().plusDays(10));
        Transport transport4=new Transport(company2,employee3,vehicleTypee2,client2,1,"Vraca","Dublin", CargoType.PASSENGERS,BigDecimal.valueOf(1000),
                true,LocalDate.now(), LocalDate.now().plusDays(15));
//        transport.setVehicle(vehicleTypee);
//        transport.setDriver(employee);
//        transport.setCompany(company);


        TransportDao.createTransport(transport);
        TransportDao.createTransport(transport1);
        TransportDao.createTransport(transport2);
        TransportDao.createTransport(transport3);
        TransportDao.createTransport(transport4);


//        TransportDao.updateTransport(transport);
//        TransportDao.updateTransport(transport1);
//        TransportDao.updateTransport(transport2);
//         TransportDao.updateTransport(transport3);
//         TransportDao.deleteTransport(transport3);

 //        Company
        //sorted
        Set<Company> companysSortedByName= CompanyDao.getSortedCompaniesByName();
        System.out.println("  ");
        System.out.println("CompanysSortedByName:");
        System.out.println(companysSortedByName);
        System.out.println("  ");

       Set<Company> companysSortedByIncome= CompanyDao.getSortedCompaniesByIncome();
        System.out.println("  ");
        System.out.println("CompanysSortedByIncome:");
        System.out.println(companysSortedByIncome);
        System.out.println("  ");

        // filtered
        Set<Company> companysByName=  CompanyDao.getCompaniesByName("Fast Transport");
        System.out.println("  ");
        System.out.println("CompanysByName:");
        System.out.println(companysByName);
        System.out.println("  ");

        Set<Company> companiesByIncome=  CompanyDao.getCompaniesByIncome(BigDecimal.valueOf(1000));
        System.out.println("  ");
        System.out.println("CompaniesByIncome:");
        System.out.println(companiesByIncome);
        System.out.println("  ");

//        Employee
        //sorted
//EmployeeDao.getEmployeesSortedByQualification();
         Set<Employee> employeesSortedByQualification=EmployeeDao.getEmployeesSortedByQualification();
        System.out.println("  ");
         System.out.println("EmployeesSortedByQualification:");
        System.out.println(employeesSortedByQualification);
        System.out.println("  ");
//EmployeeDao.getEmployeesSortedBySalary();
        Set<Employee> employeesSortedBySalary=EmployeeDao.getEmployeesSortedBySalary();
        System.out.println("  ");
        System.out.println("EmployeesSortedBySalary:");
        System.out.println(employeesSortedBySalary);
        System.out.println("  ");

        //filtered
//EmployeeDao.getEmployeesByQualification(Qualification.SPECIAL_GOODS_TRANSPORT);
            Set<Employee> employeesByQualification=EmployeeDao.getEmployeesByQualification(Qualification.SPECIAL_GOODS_TRANSPORT);
        System.out.println("  ");
        System.out.println("EmployeesByQualification:");
            System.out.println(employeesByQualification);
        System.out.println("  ");
//EmployeeDao.getEmployeesBySalary(1000);
            Set<Employee> employeesBySalary=EmployeeDao.getEmployeesBySalary(1000,2000);
        System.out.println("  ");
        System.out.println("EmployeesBySalary:");
            System.out.println(employeesBySalary);
        System.out.println("  ");

//     Transport
        //sorted
       Set<Transport> transportsSortedByDestination=TransportDao.getTransportSortedByDestination();
       System.out.println("  ");
        System.out.println("TransportSortedByDestination:");
        System.out.println(transportsSortedByDestination);
        System.out.println("  ");

       //filtered
        Set<Transport> transportsByDestination=  TransportDao.getTransportByDestination("Varna");
        System.out.println("  ");
        System.out.println("TransportByDestination:");
        System.out.println(transportsByDestination);
        System.out.println("  ");

        Set<Transport> transports = new HashSet<>();
        transports.add(transport);
        transports.add(transport1);
        transports.add(transport2);
        transports.add(transport3);
        transports.add(transport4);

        TransportDao transportDAO = new TransportDao();
        transportDAO. writeTransportFile( transports,"C:\\Users\\PC\\Desktop\\NBU godina 3\\Prilojno programirane Java\\TransportComp\\Transport.txt");
        TransportDao. readTransportFile("C:\\Users\\PC\\Desktop\\NBU godina 3\\Prilojno programirane Java\\TransportComp\\Transport.txt");


        TransportDao.ReadingReferences();
        System.out.println( );


        CompanyDao.getCompanyEmployeesDTO(1)
                .stream()
                .forEach(System.out::println);

        CompanyDao.getCompanyClientsDto(1)
                .stream()
                .forEach(System.out::println);

        CompanyDao.getCompanyVehicleTypesDTO(1)
                .stream()
                .forEach(System.out::println);

        CompanyDao.getCompanyTransportsDto(1)
                .stream()
                .forEach(System.out::println);
    }



}
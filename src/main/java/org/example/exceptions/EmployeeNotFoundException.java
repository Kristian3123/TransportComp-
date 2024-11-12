package org.example.exceptions;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

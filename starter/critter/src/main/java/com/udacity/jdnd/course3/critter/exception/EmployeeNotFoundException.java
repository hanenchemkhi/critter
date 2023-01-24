package com.udacity.jdnd.course3.critter.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("The Employee " + id + " does not exist ");
    }

}
package com.udacity.jdnd.course3.critter.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("The customer " + id + " does not exist ");
    }

}


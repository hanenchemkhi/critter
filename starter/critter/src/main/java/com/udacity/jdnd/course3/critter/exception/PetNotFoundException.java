package com.udacity.jdnd.course3.critter.exception;

public class PetNotFoundException extends RuntimeException{
    public PetNotFoundException(Long id) {
        super("Pet with " + id + " does not exist ");
    }
}

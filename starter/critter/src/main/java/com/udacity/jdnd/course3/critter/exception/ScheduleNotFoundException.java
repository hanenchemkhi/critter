package com.udacity.jdnd.course3.critter.exception;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(Long id) {

        super("Schedule " + id + " does not exist ");
    }

}
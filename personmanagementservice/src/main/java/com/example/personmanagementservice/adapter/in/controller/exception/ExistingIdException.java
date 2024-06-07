package com.example.personmanagementservice.adapter.in.controller.exception;

public class ExistingIdException extends RuntimeException{
    public ExistingIdException(Integer id) {
        super("O Id " + id + " já existe na lista.");
    }

}

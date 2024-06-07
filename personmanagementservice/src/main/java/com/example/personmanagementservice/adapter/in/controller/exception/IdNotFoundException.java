package com.example.personmanagementservice.adapter.in.controller.exception;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(Integer id) {
        super("Id " + id + " não encontrado na lista.");
    }

}

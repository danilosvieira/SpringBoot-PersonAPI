package com.example.personmanagementservice.adapter.in.controller.exception;

public class OutputAgeException extends RuntimeException{
    public OutputAgeException() {
        super("Outputs aceitos: days | months | years");
    }

}

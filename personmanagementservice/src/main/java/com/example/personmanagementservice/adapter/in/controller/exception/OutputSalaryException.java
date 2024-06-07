package com.example.personmanagementservice.adapter.in.controller.exception;

public class OutputSalaryException extends RuntimeException{
    public OutputSalaryException() {
        super("Outputs aceitos: min | full");
    }

}

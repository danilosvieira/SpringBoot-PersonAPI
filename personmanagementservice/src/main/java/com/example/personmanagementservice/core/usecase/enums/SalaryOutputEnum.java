package com.example.personmanagementservice.core.usecase.enums;

import java.util.Arrays;

public enum SalaryOutputEnum {
    min, full;

    public static boolean contains(String output){
        return Arrays.stream(SalaryOutputEnum.values()).anyMatch(salaryOutputEnum -> salaryOutputEnum.toString().equals(output));
    }
}

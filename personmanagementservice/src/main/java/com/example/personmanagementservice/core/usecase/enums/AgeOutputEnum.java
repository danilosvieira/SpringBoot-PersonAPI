package com.example.personmanagementservice.core.usecase.enums;

import java.util.Arrays;

public enum AgeOutputEnum {
    days, months, years;

    public static boolean contains(String output){
        return Arrays.stream(AgeOutputEnum.values()).anyMatch(ageOutputEnum -> ageOutputEnum.toString().equals(output));
    }
}


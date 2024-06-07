package com.example.personmanagementservice.core.usecase.strategy;

import com.example.personmanagementservice.core.usecase.enums.AgeOutputEnum;

import java.time.LocalDate;

public interface StrategyCalculoData {

    Integer calcularDiferencaEntreDatas(LocalDate data1, LocalDate data2);

    AgeOutputEnum getUnidadeTempo();
}

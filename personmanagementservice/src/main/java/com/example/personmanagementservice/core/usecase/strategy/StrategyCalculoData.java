package com.example.personmanagementservice.core.usecase.strategy;

import java.time.LocalDate;

public interface StrategyCalculoData {

    Integer calcularDiferencaEntreDatas(LocalDate data1, LocalDate data2);

    UnidadeTempoEnum getUnidadeTempo();
}

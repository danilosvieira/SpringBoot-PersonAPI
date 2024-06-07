package com.example.personmanagementservice.core.usecase.strategy;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class StrategyCalculoDataDias implements StrategyCalculoData{
    @Override
    public Integer calcularDiferencaEntreDatas(LocalDate data1, LocalDate data2) {
        return (int) ChronoUnit.DAYS.between(data1, data2);
    }

    @Override
    public UnidadeTempoEnum getUnidadeTempo() {
        return UnidadeTempoEnum.days;
    }
}

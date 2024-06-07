package com.example.personmanagementservice.core.usecase.strategy;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StrategyFactory {

    private Map<String, StrategyCalculoData> strategies;

    public StrategyCalculoData findStrategy(String unidadeTempo) {
        return strategies.get(unidadeTempo);
    }

    @Autowired
    public StrategyFactory(Set<StrategyCalculoData> strategySet) {
        createStrategy(strategySet);
    }

    private void createStrategy(Set<StrategyCalculoData> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(strategy -> strategies.put(strategy.getUnidadeTempo().toString(), strategy));
    }

}

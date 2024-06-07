package com.example.personmanagementservice.core.usecase.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class StrategyConfig {

    @Bean
    public StrategyFactory strategyFactory(Set<StrategyCalculoData> strategySet) {
        return new StrategyFactory(strategySet);
    }

}

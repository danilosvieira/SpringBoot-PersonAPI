package com.example.personmanagementservice.core.usecase.config;

import com.example.personmanagementservice.core.domain.Pessoa;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Getter
public class GerenciadorPessoas {

    private Map<Integer,Pessoa> mapaPessoas;

    @PostConstruct
    private void inicializarMapa() {
        mapaPessoas = new TreeMap<>();

        mapaPessoas.put(10, Pessoa.builder()
                .id(10)
                .nome("Pedro")
                .dataNascimento(LocalDate.of(1988,6,20))
                .dataAdmissao(LocalDate.of(2021,3,1))
                .build());

        mapaPessoas.put(13, Pessoa.builder()
                .id(13)
                .nome("Fernanda")
                .dataNascimento(LocalDate.of(1993,8,3))
                .dataAdmissao(LocalDate.of(2022,5,15))
                .build());

        mapaPessoas.put(12, Pessoa.builder()
                .id(12)
                .nome("Natan")
                .dataNascimento(LocalDate.of(1990,12,5))
                .dataAdmissao(LocalDate.of(2022,11,1))
                .build());

    }
}

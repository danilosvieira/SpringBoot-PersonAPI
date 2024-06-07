package com.example.personmanagementservice.core.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Pessoa {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataAdmissao;
}

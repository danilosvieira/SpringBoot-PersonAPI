package com.example.personmanagementservice.adapter.in.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PessoaDto {

    private Integer id;

    @NotNull
    private String nome;

    @NotNull
    private LocalDate data_nascimento;

    @NotNull
    private LocalDate data_admissao;
}

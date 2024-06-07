package com.example.personmanagementservice.adapter.in.controller.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PessoaPatchDto {

    private String nome;

    private LocalDate data_nascimento;

    private LocalDate data_admissao;
}

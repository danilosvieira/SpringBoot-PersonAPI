package com.example.personmanagementservice.adapter.in.controller.mapper;

import com.example.personmanagementservice.adapter.in.controller.dto.PessoaDto;
import com.example.personmanagementservice.adapter.in.controller.dto.PessoaPatchDto;
import com.example.personmanagementservice.core.domain.Pessoa;

public class PessoaMapper {
    public static Pessoa pessoaDtoToDomain(PessoaDto dto) {
        return Pessoa.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .dataNascimento(dto.getData_nascimento())
                .dataAdmissao(dto.getData_admissao())
                .build();
    }

    public static Pessoa pessoaPatchDtoToDomain(PessoaPatchDto dto) {
        return Pessoa.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getData_nascimento())
                .dataAdmissao(dto.getData_admissao())
                .build();
    }
}

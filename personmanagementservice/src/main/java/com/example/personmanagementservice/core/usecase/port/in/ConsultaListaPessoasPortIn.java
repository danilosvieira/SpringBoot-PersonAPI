package com.example.personmanagementservice.core.usecase.port.in;

import com.example.personmanagementservice.core.domain.Pessoa;

import java.util.List;

public interface ConsultaListaPessoasPortIn {
    List<Pessoa> consultarPessoas();
}

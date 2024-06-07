package com.example.personmanagementservice.core.usecase.port.in;

import com.example.personmanagementservice.core.domain.Pessoa;

public interface InserirPessoaPortIn {
    void inserir(Pessoa pessoa);
}

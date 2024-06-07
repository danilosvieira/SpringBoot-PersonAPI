package com.example.personmanagementservice.core.usecase.port.in;

import com.example.personmanagementservice.core.domain.Pessoa;

public interface GerenciarPessoaPortIn {
    void deletar(Integer id);

    void alterar(Integer id, Pessoa pessoa);

    void alterarAtributo(Integer id, Pessoa pessoa);

    Pessoa consultar(Integer id);
}

package com.example.personmanagementservice.core.usecase;

import com.example.personmanagementservice.adapter.in.controller.config.GerenciadorPessoas;
import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.port.in.InserirPessoaPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InserirPessoaUseCase implements InserirPessoaPortIn {

    @Autowired
    private GerenciadorPessoas gerenciadorPessoas;


    @Override
    public void inserir(Pessoa pessoa) {
        if(pessoa.getId() == null) {
            if (gerenciadorPessoas.getListaPessoas().isEmpty()) {
                pessoa.setId(1);
            } else {
                Optional<Pessoa> maiorId = gerenciadorPessoas.getListaPessoas().stream()
                        .max(Comparator.comparing(Pessoa::getId));

                maiorId.ifPresent(p -> pessoa.setId(p.getId() + 1));
            }
        }

        gerenciadorPessoas.getListaPessoas().add(pessoa);
    }
}

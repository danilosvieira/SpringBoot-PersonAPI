package com.example.personmanagementservice.core.usecase;

import com.example.personmanagementservice.adapter.in.controller.exception.ExistingIdException;
import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.config.GerenciadorPessoas;
import com.example.personmanagementservice.core.usecase.port.in.InserirPessoaPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InserirPessoaUseCase implements InserirPessoaPortIn {

    @Autowired
    private GerenciadorPessoas gerenciadorPessoas;

    @Override
    public void inserir(Pessoa pessoa) {
        List<Pessoa> listaPessoas = gerenciadorPessoas.getListaPessoas();

        if(pessoa.getId() == null) {
            if (listaPessoas.isEmpty()) {
                pessoa.setId(1);
            } else {
                Optional<Pessoa> maiorId = listaPessoas.stream()
                        .max(Comparator.comparing(Pessoa::getId));

                maiorId.ifPresent(p -> pessoa.setId(p.getId() + 1));
            }
        }

        if (listaPessoas.stream()
                .anyMatch(p -> p.getId().equals(pessoa.getId()))) {
            throw new ExistingIdException(pessoa.getId());
        } else {
            listaPessoas.add(pessoa);
        }
    }
}

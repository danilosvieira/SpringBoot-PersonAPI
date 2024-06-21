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

import java.util.TreeMap;

@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InserirPessoaUseCase implements InserirPessoaPortIn {

    @Autowired
    private GerenciadorPessoas gerenciadorPessoas;

    @Override
    public void inserir(Pessoa pessoa) {

        TreeMap<Integer,Pessoa> mapa = (TreeMap<Integer, Pessoa>) gerenciadorPessoas.getMapaPessoas();

        if(pessoa.getId() == null) {
            if (mapa.isEmpty()) {
                pessoa.setId(1);
            } else {
                Integer lastId = mapa.lastKey();
                pessoa.setId(lastId + 1);
            }
        }

        if (mapa.containsKey(pessoa.getId())) {
            throw new ExistingIdException(pessoa.getId());
        } else {
            mapa.put(pessoa.getId(), pessoa);
        }
    }
}

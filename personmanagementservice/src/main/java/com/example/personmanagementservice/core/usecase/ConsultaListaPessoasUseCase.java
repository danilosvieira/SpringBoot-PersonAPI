package com.example.personmanagementservice.core.usecase;

import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.config.GerenciadorPessoas;
import com.example.personmanagementservice.core.usecase.port.in.ConsultaListaPessoasPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConsultaListaPessoasUseCase implements ConsultaListaPessoasPortIn {

    @Autowired
    private GerenciadorPessoas gerenciadorPessoas;

    @Override
    public List<Pessoa> consultarPessoas() {
        Map<String, Pessoa> mapaOrdenadoPorNome = new TreeMap<>();

        for(Pessoa pessoa : gerenciadorPessoas.getMapaPessoas().values()){
            mapaOrdenadoPorNome.put(pessoa.getNome() + pessoa.getId(), pessoa);
        }

        return mapaOrdenadoPorNome.values().stream().toList();
    }
}

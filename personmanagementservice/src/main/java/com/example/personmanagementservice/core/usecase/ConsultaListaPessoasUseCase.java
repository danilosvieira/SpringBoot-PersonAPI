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

@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConsultaListaPessoasUseCase implements ConsultaListaPessoasPortIn {

    @Autowired
    private GerenciadorPessoas gerenciadorPessoas;

    @Override
    public List<Pessoa> consultarPessoas() {
        return gerenciadorPessoas.getListaPessoas().stream()
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .toList();
    }
}

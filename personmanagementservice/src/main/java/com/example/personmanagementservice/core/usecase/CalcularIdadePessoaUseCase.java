package com.example.personmanagementservice.core.usecase;

import com.example.personmanagementservice.adapter.in.controller.exception.IdNotFoundException;
import com.example.personmanagementservice.adapter.in.controller.exception.OutputAgeException;
import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.config.GerenciadorPessoas;
import com.example.personmanagementservice.core.usecase.enums.AgeOutputEnum;
import com.example.personmanagementservice.core.usecase.port.in.CalcularIdadePessoaPortIn;
import com.example.personmanagementservice.core.usecase.strategy.StrategyCalculoData;
import com.example.personmanagementservice.core.usecase.strategy.StrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CalcularIdadePessoaUseCase implements CalcularIdadePessoaPortIn {

    @Autowired
    private GerenciadorPessoas gerenciadorPessoas;

    @Autowired
    private StrategyFactory strategyFactory;

    @Override
    public Integer calcularIdadeAtual(Integer id, String output) {

        if(!AgeOutputEnum.contains(output)) {
            throw new OutputAgeException();
        }

        Pessoa pessoa = null;
        Integer idade = null;

        if(gerenciadorPessoas.getMapaPessoas().containsKey(id)){
            pessoa = gerenciadorPessoas.getMapaPessoas().get(id);

            StrategyCalculoData strategyCalculoData = strategyFactory.findStrategy(output);
            idade = strategyCalculoData.calcularDiferencaEntreDatas(pessoa.getDataNascimento(), LocalDate.now());
        } else {
            throw new IdNotFoundException(id);
        }

        return idade;
    }

}

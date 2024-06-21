package com.example.personmanagementservice.core.usecase;

import com.example.personmanagementservice.adapter.in.controller.exception.IdNotFoundException;
import com.example.personmanagementservice.adapter.in.controller.exception.OutputSalaryException;
import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.config.GerenciadorPessoas;
import com.example.personmanagementservice.core.usecase.enums.SalaryOutputEnum;
import com.example.personmanagementservice.core.usecase.port.in.CalcularSalarioPessoaPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CalcularSalarioPessoaUseCase implements CalcularSalarioPessoaPortIn {

    @Autowired
    private GerenciadorPessoas gerenciadorPessoas;

    @Override
    public String calcularSalario(Integer id, String output) {

        if(!SalaryOutputEnum.contains(output)) {
            throw new OutputSalaryException();
        }

        Pessoa pessoa = null;
        double salario = 0;

        if(gerenciadorPessoas.getMapaPessoas().containsKey(id)){
            pessoa = gerenciadorPessoas.getMapaPessoas().get(id);

            long numAnos = ChronoUnit.YEARS.between(pessoa.getDataAdmissao(), LocalDate.now());

            salario = 1558 * Math.pow(1.18, numAnos) + 500 * (Math.pow(1.18, numAnos) - 1) / 0.18;

            if (output.equals(SalaryOutputEnum.min.toString())) {
                salario = salario/1302;
            }
        } else {
            throw new IdNotFoundException(id);
        }

        DecimalFormat df = new DecimalFormat("#.00");

        return df.format(salario);
    }
}

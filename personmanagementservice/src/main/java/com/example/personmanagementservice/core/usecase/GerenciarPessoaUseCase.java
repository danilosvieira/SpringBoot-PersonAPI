package com.example.personmanagementservice.core.usecase;

import com.example.personmanagementservice.adapter.in.controller.config.GerenciadorPessoas;
import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.port.in.GerenciarPessoaPortIn;
import com.example.personmanagementservice.core.usecase.strategy.StrategyCalculoData;
import com.example.personmanagementservice.core.usecase.strategy.StrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GerenciarPessoaUseCase implements GerenciarPessoaPortIn {

    @Autowired
    private GerenciadorPessoas gerenciadorPessoas;

    @Autowired
    private StrategyFactory strategyFactory;

    @Override
    public void deletar(Integer id) {
        gerenciadorPessoas.getListaPessoas()
                .removeIf(pessoa -> pessoa.getId().equals(id));
    }

    @Override
    public void alterar(Integer id, Pessoa pessoa) {

        List<Pessoa> lista = gerenciadorPessoas.getListaPessoas();

        OptionalInt indexOpt = IntStream.range(0, lista.size())
                .filter(i -> lista.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            int index = indexOpt.getAsInt();
            lista.set(index, pessoa);
        }

    }

    @Override
    public void alterarAtributo(Integer id, Pessoa pessoaAlterado) {

        Optional<Pessoa> pessoaOpt = gerenciadorPessoas.getListaPessoas().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (pessoaOpt.isPresent()) {
            Pessoa pessoa = pessoaOpt.get();

            if (pessoaAlterado.getNome() != null)
                pessoa.setNome(pessoaAlterado.getNome());

            if (pessoaAlterado.getDataAdmissao() != null)
                pessoa.setDataAdmissao(pessoaAlterado.getDataAdmissao());

            if (pessoaAlterado.getDataNascimento() != null)
                pessoa.setDataNascimento(pessoaAlterado.getDataNascimento());
        }

    }

    @Override
    public Pessoa consultar(Integer id) {
        Optional<Pessoa> pessoaOpt = gerenciadorPessoas.getListaPessoas().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        Pessoa pessoa = null;

        if (pessoaOpt.isPresent()) {
            pessoa = pessoaOpt.get();
        }
        return pessoa;
    }

    @Override
    public Integer calcularIdadeAtual(Integer id, String unidadeTempo) {
        Optional<Pessoa> pessoaOpt = gerenciadorPessoas.getListaPessoas().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        Pessoa pessoa = null;
        Integer idade = null;

        if (pessoaOpt.isPresent()) {
            pessoa = pessoaOpt.get();

            StrategyCalculoData strategyCalculoData = strategyFactory.findStrategy(unidadeTempo);
            idade = strategyCalculoData.calcularDiferencaEntreDatas(pessoa.getDataNascimento(), LocalDate.now());
        }

        return idade;
    }

    @Override
    public String calcularSalario(Integer id, String output) {
        Optional<Pessoa> pessoaOpt = gerenciadorPessoas.getListaPessoas().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        Pessoa pessoa = null;
        double salario = 0;

        if (pessoaOpt.isPresent()) {
            pessoa = pessoaOpt.get();

            long numAnos = ChronoUnit.YEARS.between(pessoa.getDataAdmissao(), LocalDate.now());

            salario = 1558 * Math.pow(1.18, numAnos) + 500 * (Math.pow(1.18, numAnos) - 1) / 0.18;

            if (output.equals("min")) {
                salario = salario/1302;
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");

        return df.format(salario);
    }
}

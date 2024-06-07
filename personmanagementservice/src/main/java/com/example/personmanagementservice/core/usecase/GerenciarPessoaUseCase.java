package com.example.personmanagementservice.core.usecase;

import com.example.personmanagementservice.adapter.in.controller.exception.IdNotFoundException;
import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.config.GerenciadorPessoas;
import com.example.personmanagementservice.core.usecase.port.in.GerenciarPessoaPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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

    @Override
    public void deletar(Integer id) {
        Optional<Pessoa> pessoaOpt = gerenciadorPessoas.getListaPessoas().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (pessoaOpt.isPresent()) {
            gerenciadorPessoas.getListaPessoas()
                    .removeIf(pessoa -> pessoa.getId().equals(id));
        } else {
            throw new IdNotFoundException(id);
        }
    }

    @Override
    public void alterar(Integer id, Pessoa pessoa) {

        List<Pessoa> lista = gerenciadorPessoas.getListaPessoas();

        OptionalInt indexOpt = IntStream.range(0, lista.size())
                .filter(i -> lista.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            if(pessoa.getId() == null)
                pessoa.setId(id);

            int index = indexOpt.getAsInt();
            lista.set(index, pessoa);
        } else {
            throw new IdNotFoundException(id);
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
        } else {
            throw new IdNotFoundException(id);
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
        } else {
            throw new IdNotFoundException(id);
        }
        return pessoa;
    }

}

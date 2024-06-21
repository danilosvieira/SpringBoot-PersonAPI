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

@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GerenciarPessoaUseCase implements GerenciarPessoaPortIn {

    @Autowired
    private GerenciadorPessoas gerenciadorPessoas;

    @Override
    public void deletar(Integer id) {

        if(gerenciadorPessoas.getMapaPessoas().containsKey(id)){
            gerenciadorPessoas.getMapaPessoas().remove(id);
        } else {
            throw new IdNotFoundException(id);
        }
    }

    @Override
    public void alterar(Integer id, Pessoa pessoa) {

        if(gerenciadorPessoas.getMapaPessoas().containsKey(id)){
            if(pessoa.getId() == null)
                pessoa.setId(id);

            gerenciadorPessoas.getMapaPessoas().replace(id, pessoa);
        } else {
            throw new IdNotFoundException(id);
        }

    }

    @Override
    public void alterarAtributo(Integer id, Pessoa pessoaAlterado) {

        if(gerenciadorPessoas.getMapaPessoas().containsKey(id)){
            Pessoa pessoa = gerenciadorPessoas.getMapaPessoas().get(id);

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
        if(gerenciadorPessoas.getMapaPessoas().containsKey(id)){
            return gerenciadorPessoas.getMapaPessoas().get(id);
        } else {
            throw new IdNotFoundException(id);
        }
    }

}

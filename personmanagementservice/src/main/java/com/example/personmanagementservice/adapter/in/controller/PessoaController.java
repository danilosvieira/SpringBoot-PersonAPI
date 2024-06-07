package com.example.personmanagementservice.adapter.in.controller;

import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.GerenciarPessoaUseCase;
import com.example.personmanagementservice.core.usecase.port.in.ConsultaListaPessoasPortIn;
import com.example.personmanagementservice.core.usecase.port.in.InserirPessoaPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PessoaController {

    @Autowired
    private ConsultaListaPessoasPortIn consultaListaPessoasPortIn;

    @Autowired
    private InserirPessoaPortIn inserirPessoaPortIn;

    @Autowired
    private GerenciarPessoaUseCase gerenciarPessoaUseCase;

    @GetMapping("person")
    public ResponseEntity<Object> getListaPessoas(){
        List<Pessoa> listaPessoas = consultaListaPessoasPortIn.consultarPessoas();
        return ResponseEntity.status(HttpStatus.OK).body(listaPessoas);
    }

    @PostMapping("person")
    public ResponseEntity<Object> insertPessoa(@RequestBody Pessoa pessoa){
        inserirPessoaPortIn.inserir(pessoa);
        return ResponseEntity.status(HttpStatus.OK).body("SUCESS: Pessoa incluída com sucesso.");
    }

    @DeleteMapping("person/{id}")
    public ResponseEntity<Object> insertPessoa(@PathVariable(value = "id") Integer id){
        gerenciarPessoaUseCase.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("SUCESS: Pessoa excluída com sucesso.");
    }

    @PutMapping("person/{id}")
    public ResponseEntity<Object> atualizarPessoa(@PathVariable(value = "id") Integer id, @RequestBody Pessoa pessoa){
        gerenciarPessoaUseCase.alterar(id, pessoa);
        return ResponseEntity.status(HttpStatus.OK).body("SUCESS: Pessoa atualizada com sucesso.");
    }

    @PatchMapping("person/{id}")
    public ResponseEntity<Object> atualizarAtributoPessoa(@PathVariable(value = "id") Integer id, @RequestBody Pessoa pessoa){
        gerenciarPessoaUseCase.alterarAtributo(id, pessoa);
        return ResponseEntity.status(HttpStatus.OK).body("SUCESS: Pessoa atualizada com sucesso.");
    }

    @GetMapping("person/{id}")
    public ResponseEntity<Object> getPessoa(@PathVariable(value = "id") Integer id){
        Pessoa pessoa = gerenciarPessoaUseCase.consultar(id);
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("person/{id}/age")
    public ResponseEntity<Object> getIdadePessoa(@PathVariable(value = "id") Integer id, @RequestParam String output){
        Integer idade = gerenciarPessoaUseCase.calcularIdadeAtual(id, output);
        return ResponseEntity.status(HttpStatus.OK).body(idade);
    }

    @GetMapping("person/{id}/salary")
    public ResponseEntity<Object> getSalarioPessoa(@PathVariable(value = "id") Integer id, @RequestParam String output){
        String salario = gerenciarPessoaUseCase.calcularSalario(id, output);
        return ResponseEntity.status(HttpStatus.OK).body(salario);
    }

}

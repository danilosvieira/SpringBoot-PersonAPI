package com.example.personmanagementservice.adapter.in.controller;

import com.example.personmanagementservice.adapter.in.controller.dto.PessoaDto;
import com.example.personmanagementservice.adapter.in.controller.dto.PessoaPatchDto;
import com.example.personmanagementservice.adapter.in.controller.mapper.PessoaMapper;
import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.port.in.GerenciarPessoaPortIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GerenciarPessoaController {

    @Autowired
    private GerenciarPessoaPortIn gerenciarPessoaPortIn;

    @DeleteMapping("person/{id}")
    public ResponseEntity<Object> deletarPessoa(@PathVariable(value = "id") Integer id){
        gerenciarPessoaPortIn.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("SUCESS: Pessoa excluída com sucesso.");
    }

    @PutMapping("person/{id}")
    public ResponseEntity<Object> atualizarPessoa(@PathVariable(value = "id") Integer id, @RequestBody PessoaDto pessoaDto){
        gerenciarPessoaPortIn.alterar(id, PessoaMapper.pessoaDtoToDomain(pessoaDto));
        return ResponseEntity.status(HttpStatus.OK).body("SUCESS: Pessoa atualizada com sucesso.");
    }

    @PatchMapping("person/{id}")
    public ResponseEntity<Object> atualizarAtributoPessoa(@PathVariable(value = "id") Integer id, @Valid @RequestBody PessoaPatchDto pessoaPatchDto){
        gerenciarPessoaPortIn.alterarAtributo(id, PessoaMapper.pessoaPatchDtoToDomain(pessoaPatchDto));
        return ResponseEntity.status(HttpStatus.OK).body("SUCESS: Pessoa atualizada com sucesso.");
    }

    @GetMapping("person/{id}")
    public ResponseEntity<Object> getPessoa(@PathVariable(value = "id") Integer id){
        Pessoa pessoa = gerenciarPessoaPortIn.consultar(id);
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

}

package com.example.personmanagementservice.adapter.in.controller;

import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.port.in.InserirPessoaPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class InserirPessoaController {

    @Autowired
    private InserirPessoaPortIn inserirPessoaPortIn;

    @PostMapping("person")
    public ResponseEntity<Object> insertPessoa(@RequestBody Pessoa pessoa){
        inserirPessoaPortIn.inserir(pessoa);
        return ResponseEntity.status(HttpStatus.OK).body("SUCESS: Pessoa incluída com sucesso.");
    }

}

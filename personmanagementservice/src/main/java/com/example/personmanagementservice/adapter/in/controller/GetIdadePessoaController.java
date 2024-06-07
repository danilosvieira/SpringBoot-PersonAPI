package com.example.personmanagementservice.adapter.in.controller;

import com.example.personmanagementservice.core.usecase.port.in.CalcularIdadePessoaPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GetIdadePessoaController {

    @Autowired
    private CalcularIdadePessoaPortIn calcularIdadePessoaPortIn;

    @GetMapping("person/{id}/age")
    public ResponseEntity<Object> getIdadePessoa(@PathVariable(value = "id") Integer id, @RequestParam String output){
        Integer idade = calcularIdadePessoaPortIn.calcularIdadeAtual(id, output);
        return ResponseEntity.status(HttpStatus.OK).body(idade);
    }

}

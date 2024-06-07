package com.example.personmanagementservice.adapter.in.controller;

import com.example.personmanagementservice.core.usecase.port.in.CalcularSalarioPessoaPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GetSalarioPessoaController {

    @Autowired
    private CalcularSalarioPessoaPortIn calcularSalarioPessoaPortIn;

    @GetMapping("person/{id}/salary")
    public ResponseEntity<Object> getSalarioPessoa(@PathVariable(value = "id") Integer id, @RequestParam String output){
        String salario = calcularSalarioPessoaPortIn.calcularSalario(id, output);
        return ResponseEntity.status(HttpStatus.OK).body(salario);
    }

}

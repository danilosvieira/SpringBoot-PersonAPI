package com.example.personmanagementservice.adapter.in.controller;

import com.example.personmanagementservice.core.domain.Pessoa;
import com.example.personmanagementservice.core.usecase.port.in.ConsultaListaPessoasPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ConsultarListaPessoasController {

    @Autowired
    private ConsultaListaPessoasPortIn consultaListaPessoasPortIn;

    @GetMapping("person")
    public ResponseEntity<Object> getListaPessoas(){
        List<Pessoa> listaPessoas = consultaListaPessoasPortIn.consultarPessoas();
        return ResponseEntity.status(HttpStatus.OK).body(listaPessoas);
    }

}

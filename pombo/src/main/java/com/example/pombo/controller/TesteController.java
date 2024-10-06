package com.example.pombo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pombo.exception.PomboException;
import com.example.pombo.model.entity.Teste;
import com.example.pombo.service.TesteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/teste")
public class TesteController {

    @Autowired
    private TesteService testeService;

    public ResponseEntity<Teste> inserir(@RequestBody Teste teste) throws PomboException {
        return ResponseEntity.ok(testeService.inserir(teste));
    }
    
}

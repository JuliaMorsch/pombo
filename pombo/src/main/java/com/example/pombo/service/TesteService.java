package com.example.pombo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pombo.model.entity.Teste;
import com.example.pombo.repository.TesteRepository;

@Service
public class TesteService {
    
    @Autowired
    private TesteRepository testeRepository;    
    
    public Teste inserir(Teste teste) {
        return testeRepository.save(teste);
    }
}

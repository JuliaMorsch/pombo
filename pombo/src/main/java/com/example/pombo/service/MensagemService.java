package com.example.pombo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pombo.entity.Mensagem;
import com.example.pombo.exception.PomboException;
import com.example.pombo.repository.MensagemRepository;

@Service
public class MensagemService {
    
    @Autowired
    private MensagemRepository mensagemRepository;

    public Mensagem save(Mensagem mensagem) throws PomboException{
        return mensagemRepository.save(mensagem);
    }
}

package com.example.pombo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pombo.entity.Mensagem;
import com.example.pombo.entity.Usuario;
import com.example.pombo.exception.PomboException;
import com.example.pombo.service.MensagemService;
import com.example.pombo.service.UsuarioService;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Mensagem save(@RequestBody Mensagem mensagem) {
        Long idUsuario = mensagem.getUsuario().getId();
        if (idUsuario != null) {
            try {
                Usuario usuario = usuarioService.findById(idUsuario);
                mensagem.setUsuario(usuario);
            } catch (PomboException e) {
                throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage(), e);
            }
        }

        try {
            return mensagemService.save(mensagem);
        } catch (PomboException e) {
            throw new RuntimeException("Erro ao salvar mensagem: " + e.getMessage(), e);
        }
    }
}

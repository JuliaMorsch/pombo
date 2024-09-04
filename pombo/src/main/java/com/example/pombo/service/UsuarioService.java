package com.example.pombo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pombo.entity.Usuario;
import com.example.pombo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioSalvo;
    }
}

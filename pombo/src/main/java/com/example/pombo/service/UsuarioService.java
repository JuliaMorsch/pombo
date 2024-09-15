package com.example.pombo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pombo.entity.Usuario;
import com.example.pombo.exception.PomboException;
import com.example.pombo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioSalvo;
    }

    public Usuario findById(Long id) throws PomboException {
        return usuarioRepository.findById(id).orElseThrow(() -> new PomboException("Usuário não encontrado."));
    
    }

    public List<Usuario> findAll(){ 
        return usuarioRepository.findAll();
    }
}

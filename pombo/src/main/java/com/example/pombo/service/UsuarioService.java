package com.example.pombo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pombo.exception.PomboException;
import com.example.pombo.model.entity.Usuario;
import com.example.pombo.repository.MensagemRepository;
import com.example.pombo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario save(Usuario usuario) throws PomboException {
        if (usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new PomboException("CPF já cadastrado.");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario findByUsuario(String usuarioId) throws PomboException {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new PomboException("Usuário não encontrado."));

    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(String id) {
        usuarioRepository.deleteById(id);
    }
}

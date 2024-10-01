package com.example.pombo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pombo.exception.PomboException;
import com.example.pombo.model.entity.Mensagem;
import com.example.pombo.model.entity.Usuario;
import com.example.pombo.repository.MensagemRepository;
import com.example.pombo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // public Set<Mensagem> consultarCurtidas(String idMensagem, String idUsuario) {
    // Mensagem mensagem = mensagemRepository.findById(idMensagem).orElseThrow(() ->
    // new RuntimeException("Mensagem não encontrada."));
    // Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new
    // RuntimeException("Usuário não encontrado."));
    // if (usuario.getMensagensCurtidas().contains(mensagem)) {
    // usuario.getMensagensCurtidas().remove(mensagem);
    // } else {
    // usuario.getMensagensCurtidas().add(mensagem);
    // }
    // usuarioRepository.save(usuario);
    // usuario.getMensagensCurtidas().size();
    // return usuario.getMensagensCurtidas();
    // }

    // public Set<Usuario> gerarCurtidasUsuarios(String idMensagem, String idUsuario) {
    //     Mensagem mensagem = mensagemRepository.findById(idMensagem)
    //             .orElseThrow(() -> new RuntimeException("Mensagem não encontrada."));
    //     Usuario usuario = usuarioRepository.findById(idUsuario)
    //             .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    //     if (mensagem.getUsuariosCurtiram().contains(usuario)) {
    //         mensagem.getUsuariosCurtiram().remove(usuario);
    //     } else {
    //         mensagem.getUsuariosCurtiram().add(usuario);
    //     }
    //     mensagemRepository.save(mensagem);
    //     mensagem.getUsuariosCurtiram().size();
    //     return mensagem.getUsuariosCurtiram();
    // }

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

package com.example.pombo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pombo.exception.PomboException;
import com.example.pombo.model.dto.MensagemRelatorioDTO;
import com.example.pombo.model.entity.Denuncia;
import com.example.pombo.model.entity.Mensagem;
import com.example.pombo.model.entity.Usuario;
import com.example.pombo.repository.MensagemRepository;
import com.example.pombo.repository.UsuarioRepository;

@Service
public class MensagemService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    public MensagemRelatorioDTO gerarRelatorio(Mensagem mensagem) throws PomboException {
        MensagemRelatorioDTO dto = new MensagemRelatorioDTO();
        dto.setUuidUsuario(mensagem.getUsuario().getId());
        dto.setNomeUsuario(mensagem.getUsuario().getNome());
        dto.setQtdeCurtidas(mensagem.getLikes());
        dto.setQtdeDenuncias(mensagem.getDenuncias().size());

        if (mensagem.isBloqueado() == true) {
            dto.setTextoOuStatus("Bloqueado pelo administrador");
        } else {
            dto.setTextoOuStatus(mensagem.getTexto());
        }

        return dto;
    }

    public Mensagem save(Mensagem mensagem) throws PomboException {
        Usuario usuario = usuarioRepository.findById(mensagem.getUsuario().getId())
                .orElseThrow(() -> new PomboException("Usuário não encontrado."));
        return mensagemRepository.save(mensagem);
    }

    public List<Mensagem> findAll() {
        return mensagemRepository.findAll();
    }

    public Mensagem buscar(String id) {
        return mensagemRepository.findById(id).get();
    }

    public List<Mensagem> listarMensagensPorUsuario(Usuario usuario) {
        return mensagemRepository.findByUsuario(usuario);
    }

    public Set<Usuario> curtir(String idMensagem, String idUsuario) throws PomboException {
        Mensagem mensagem = mensagemRepository.findById(idMensagem)
                .orElseThrow(() -> new PomboException("Mensagem não encontrada."));
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new PomboException("Usuário não encontrado."));

        if (mensagem.getUsuariosCurtiram().contains(usuario)) {
            mensagem.getUsuariosCurtiram().remove(usuario);
            mensagem.setLikes(mensagem.getLikes() - 1);
        } else {
            mensagem.getUsuariosCurtiram().add(usuario);
            mensagem.setLikes(mensagem.getLikes() + 1);
        }
        mensagemRepository.save(mensagem);

        return mensagem.getUsuariosCurtiram();
    }

    // public void bloquearMensagem(String mensagemId) {
    //     Mensagem mensagem = mensagemRepository.findById(mensagemId)
    //             .orElseThrow(() -> new RuntimeException("Mensagem não encontrada."));
    //     mensagem.setBloqueado(true);
    //     mensagemRepository.save(mensagem);
    // }

    // public String bloquearMensagem(String idMensagem, String idUsuario) throws
    // PomboException {
    // vericarPerfilAcesso(idUsuario);

    // String resultado;
    // Mensagem mensagem = mensagemRepository.findById(idMensagem).get();
    // if (verificarSituacaoMensagem(idMensagem)) {
    // mensagem.setBloqueado(true);
    // mensagem.setTexto("**Esse texto está bloqueado.**");
    // resultado = "Mensagem bloqueada!";
    // } else {
    // throw new PomboException("A mensagem não foi bloqueada");
    // }

    // mensagemRepository.save(mensagem);

    // return resultado;
    // }

    // public void verificarSituacaoMensagem(String idMensagem) throws PomboException {

    //     Mensagem mensagem = mensagemRepository.findById(idMensagem)
    //             .orElseThrow(() -> new PomboException("Mensagem não encontrada."));

    //     List<Denuncia> denuncias = mensagem.getDenuncias();

    //     for (Denuncia denuncia : denuncias) {
    //         if (denuncia.isAnalisado() == true) {
    //             break;
    //         } else {
    //             throw new PomboException(
    //                     "Para bloquear uma mensagem, ela precisar conter no mínimo uma denúncia analisada.");
    //         }
    //     }
    // }

    // return analisada;
    // }

    public void delete(String id) {
        mensagemRepository.deleteById(id);
    }

    // private void vericarPerfilAcesso(String idUsuario) throws PomboException {

    // boolean verificado = false;

    // Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new
    // PomboException("Usuário não encontrado."));

    // if(usuario.isAdmin() == true) {
    // verificado = true;
    // }
    // }

}

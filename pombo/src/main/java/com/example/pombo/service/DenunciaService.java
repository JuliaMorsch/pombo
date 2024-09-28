package com.example.pombo.service;

import com.example.pombo.model.dto.DenunciaRelatorioDTO;
import com.example.pombo.model.entity.Denuncia;
import com.example.pombo.model.entity.DenunciaPK;
import com.example.pombo.model.entity.Mensagem;
import com.example.pombo.model.entity.Usuario;
import com.example.pombo.model.enums.MotivoDenuncia;
import com.example.pombo.model.filtro.DenunciaFiltro;
import com.example.pombo.repository.DenunciaRepository;
import com.example.pombo.repository.MensagemRepository;
import com.example.pombo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

     public DenunciaRelatorioDTO gerarRelatorio(Denuncia denuncia, Mensagem mensagem) {
         DenunciaRelatorioDTO dto = new DenunciaRelatorioDTO();
         dto.setIdMensagem(denuncia.getMensagem().getId());
         dto.setQntdDenuncias(mensagem.getDenuncias().size());
      
         if (denuncia.isAnalisado() == false) {
            dto.setQntdDenunciasPendentes(dto.getQntdDenunciasPendentes() + 1);
         } else {
            dto.setQntdDenunciasAnalisadas(dto.getQntdDenunciasAnalisadas() + 1);
         }
            return dto;
         }
     

    public Denuncia denunciarMensagem(String idMensagem, String idUsuario, MotivoDenuncia motivo) {
        Mensagem mensagem = this.mensagemRepository.findById(idUsuario).get();
        Usuario usuario = this.usuarioRepository.findById(idUsuario).get();

        DenunciaPK denunciaPK = new DenunciaPK();
        denunciaPK.setIdMensagem(idMensagem);
        denunciaPK.setIdUsuario(idUsuario);

        Denuncia denuncia = new Denuncia();
        denuncia.setId(denunciaPK);
        denuncia.setMensagem(mensagem);
        denuncia.setUsuario(usuario);
        denuncia.setMotivo(motivo);
        denuncia.setAnalisado(false);
        return denunciaRepository.save(denuncia);
    }

    public List<Denuncia> listar() {
        return denunciaRepository.findAll();
    }

    public List<Denuncia> listarComFiltro(DenunciaFiltro filtros) {
        if(filtros.temPaginacao()) {
            int pageNumber = filtros.getPagina();
            int pageSize = filtros.getLimite();

            PageRequest pagina = PageRequest.of(pageNumber - 1, pageSize);
            return denunciaRepository.findAll(filtros, pagina).toList();
        }
        return denunciaRepository.findAll(filtros);
    }

    public Denuncia buscar(String id) {
        return denunciaRepository.findById(id).get();
    }
}
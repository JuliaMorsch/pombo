package com.example.pombo.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class DenunciaPK implements Serializable {
    
    @Column(name = "id_mensagem")
    String idMensagem;

    @Column(name = "id_usuario")
    String idUsuario;
}

package com.example.pombo.model.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.example.pombo.model.enums.MotivoDenuncia;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class Denuncia {
    
    @EmbeddedId
    private DenunciaPK id;

    @ManyToOne
    @MapsId("idMensagem")
    @JoinColumn(name = "id_mensagem")
    private Mensagem mensagem;
    
    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @CreationTimestamp
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private MotivoDenuncia motivo;

    private boolean analisado;
}

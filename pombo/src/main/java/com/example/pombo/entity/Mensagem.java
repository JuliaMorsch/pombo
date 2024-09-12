package com.example.pombo.entity;


// import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.grammars.hql.HqlParser.LocalDateTimeContext;

import com.example.pombo.validation.NonBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Mensagem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonBlank(message = "O campo texto é obrigatório e não pode conter apenas espaços em branco")
    @Size(min = 1, max = 300, message = "O campo texto pode ter no máximo 300 caracteres")
    private String texto;

    // ver quem deu like
    private int curtida;
    
    private boolean bloqueado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}

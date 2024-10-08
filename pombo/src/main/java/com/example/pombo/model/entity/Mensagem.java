package com.example.pombo.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.example.pombo.validation.NonBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Mensagem {

    @Id
    @UuidGenerator
    private String id;

    @NonBlank(message = "O campo texto é obrigatório e não pode conter apenas espaços em branco")
    @Size(min = 1, max = 300, message = "O campo texto pode ter no máximo 300 caracteres")
    private String texto;

    private int likes = 0;
    @CreationTimestamp
    private LocalDateTime dataCriacao;
    private boolean bloqueado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "mensagem")
    private List<Denuncia> denuncias;

    @ManyToMany
    @JoinTable(name = "curtidas",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_mensagem")
    )
    private Set<Usuario> usuariosCurtiram;

}

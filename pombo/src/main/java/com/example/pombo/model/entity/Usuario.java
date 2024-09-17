package com.example.pombo.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Data
public class Usuario {
    
    @Id
    @UuidGenerator
    private String id;
    private String nome;
    private String email;
    @CPF
    private String cpf;
    private boolean isAdmin = false;

    @JsonBackReference
    @OneToMany(mappedBy = "usuario",    
        cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Mensagem> mensagem;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "curtidas",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_mensagem")
    )
    private Set<Mensagem> mensagensCurtidas = new HashSet<>();

}

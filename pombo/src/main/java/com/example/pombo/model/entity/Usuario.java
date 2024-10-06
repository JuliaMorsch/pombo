package com.example.pombo.model.entity;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Data
public class Usuario {
    
    @Id
    @UuidGenerator
    private String id;

    private String nome;

    private String email;
    
    @NotBlank(message = "CPF não pode ser vazio.")
    @CPF    
    private String cpf;
    private boolean isAdmin = false;

    @OneToMany(mappedBy = "usuario")
    @JsonBackReference
    private List<Mensagem> mensagens;   
    
    // @ManyToMany(mappedBy = "usuariosCurtiram")
    // private Set<Mensagem> mensagensCurtidas;

}

package com.example.pombo.model.entity;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
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

    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Mensagem> mensagem;

    // Lista de mensagens curtidas do usuário
    

}

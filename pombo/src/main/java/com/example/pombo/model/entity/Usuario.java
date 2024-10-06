package com.example.pombo.model.entity;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @OneToMany
    @JoinColumn(name = "id_mensagem")
    private List<Mensagem> mensagens;    

}

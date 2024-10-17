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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class Usuario {
    
    @Id
    @UuidGenerator
    private String id;

    @Size(min = 3, message = "O campo nome deve ter no minimo 3 caracteres.")
    private String nome;
    
    @Email
    private String email;
    
    @NotBlank(message = "CPF não pode ser vazio.")
    @CPF    
    private String cpf;
    private boolean isAdmin;

    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Mensagem> mensagens;  


    @ManyToMany(mappedBy = "usuariosCurtiram")
    Set<Mensagem> curtidas;
    
    

}

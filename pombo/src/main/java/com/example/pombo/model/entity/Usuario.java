package com.example.pombo.model.entity;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    // @JsonBackReference
    // @OneToMany(mappedBy = "usuario",    
    //     cascade = CascadeType.ALL,orphanRemoval = true)
    // private List<Mensagem> mensagem;

    // Lista de mensagens curtidas do usuário
    // @OneToMany
    // @JoinTable(
    //         name = "curtidas",
    //         joinColumns = @JoinColumn(name = "id_mensagem"),
    //         inverseJoinColumns = @JoinColumn(name = "id_usuario")
    // )
    // private Set<Mensagem> mensagensCurtidas = new HashSet<>();
    

}

package com.example.pombo.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class Usuario implements UserDetails {
    
    @Id
    @UuidGenerator
    private String id;
    
    @NotBlank(message = "Nome não pode ser vazio.")
    @Size(min = 3, message = "O campo nome deve ter no minimo 3 caracteres.")
    private String nome;
    
    @Email(message = "Email inválido.")
    private String email;

    @NotBlank(message = "Senha é obrigatória.")
    @Column(length = 4000)   
    private String senha;
    
    @NotBlank(message = "CPF não pode ser vazio.")
    @CPF    
    private String cpf;
    @Enumerated(EnumType.STRING)
    private PerfilAcesso perfilAcesso;

    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Mensagem> mensagens;      

    @Override 
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(perfilAcesso.toString()));

        return list;
    }
}

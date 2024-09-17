package com.example.pombo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pombo.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
}

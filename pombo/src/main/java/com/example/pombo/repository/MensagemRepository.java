package com.example.pombo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pombo.model.entity.Mensagem;
import com.example.pombo.model.entity.Usuario;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, String>{
    List<Mensagem> findByUsuario(Usuario usuario);
}

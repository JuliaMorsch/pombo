package com.example.pombo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pombo.entity.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
    
}

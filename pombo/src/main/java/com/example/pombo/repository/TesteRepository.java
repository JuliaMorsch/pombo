package com.example.pombo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pombo.model.entity.Teste;

@Repository
public interface TesteRepository extends JpaRepository<Teste, String> {
    
}

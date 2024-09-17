package com.example.pombo.repository;

import com.example.pombo.model.entity.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, String>, JpaSpecificationExecutor<Denuncia> {
}
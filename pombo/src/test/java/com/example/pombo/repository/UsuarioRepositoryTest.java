package com.example.pombo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    
}

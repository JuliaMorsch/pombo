package com.example.pombo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.pombo.model.entity.Usuario;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testarTodosCamposValidos() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome para teste");
        usuario.setEmail("testaUsuario@gmail.com");
        usuario.setCpf("053.843.920-37");
        usuario.setAdmin(false);
        usuario.setMensagens(null);
        usuario.setCurtidas(null);        

        usuarioRepository.save(usuario);   
    }

    
}

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

        usuarioRepository.save(usuario);   
    }

    @Test
    public void testarNomeInvalidoMenorQue2() {
        Usuario usuario = new Usuario();
        usuario.setNome("N");
        usuario.setEmail("emailteste@teste.com");
        usuario.setCpf("966.326.170-69"); 
        usuario.isAdmin();
        
        usuarioRepository.save(usuario);
    }

    @Test
    public void testarNomeInvalidoNulo() {
        Usuario usuario = new Usuario();
        usuario.setNome(null);
        usuario.setEmail("teste@teste.com");
        usuario.setCpf("966.326.170-69");
        usuario.isAdmin();

        usuarioRepository.save(usuario);
    }

    @Test
    public void testarEmailInvalido() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome para teste");
        usuario.setEmail("emailteste.com");
        usuario.setCpf("966.326.170-69");
        usuario.isAdmin();

        usuarioRepository.save(usuario);
    }

    @Test
    public void testarCpfInvalido() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome para teste");
        usuario.setEmail("email@email.com");
        usuario.setCpf("11111111111");
        usuario.isAdmin();

        usuarioRepository.save(usuario);
    }
}



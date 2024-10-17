package com.example.pombo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.pombo.model.entity.Denuncia;
import com.example.pombo.model.entity.DenunciaPK;
import com.example.pombo.model.entity.Mensagem;
import com.example.pombo.model.entity.Usuario;

@SpringBootTest

@ActiveProfiles("test")
public class MensagemRepositoryTest {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private DenunciaRepository denunciaRepository;

    // Carrega lista de denuncias e curtidas da mensagem
    @BeforeEach
    private void setUp() {
    DenunciaPK denunciaPK = new DenunciaPK();


    List<Denuncia> denuncias = new ArrayList<>();
    
    Mensagem mensagem = new Mensagem();
    for(int i = 0; i <= 5; i++){
    mensagem.setId(UUID.randomUUID().toString());
    mensagem.setTexto("Testando");
    }

    for(int i = 0; i <= 5; i++){
    Usuario usuario = new Usuario();
    usuario.setNome("Teste" + i);
    usuario.setEmail("teste@teste" + i + ".com");
    // usuario.setCpf("38136211469");
    }

    Denuncia denuncia = new Denuncia();
    // denuncia.set
    // setar elementos na lista de denuncias - compara com a tabela de denuncias

    }

    @Test
    public void testaTodosOsDadosCorretos() {
        Mensagem mensagem = new Mensagem();
        mensagem.setTexto("Esse é um texto de teste");
        mensagem.setLikes(10);
        // mensagem.setDenuncias();
    }
}

package com.example.pombo.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.pombo.model.entity.Denuncia;
import com.example.pombo.model.entity.Mensagem;

@SpringBootTest

@ActiveProfiles("test")
public class MensagemRepositoryTest {
    
    @Mock
    private MensagemRepository mensagemRepository;

    @Mock
    private DenunciaRepository denunciaRepository;

    // Carrega lista de denuncias e curtidas da mensagem
    @BeforeEach
    private void setUp(){}
    List<Denuncia> denuncias = new ArrayList<>();
    Mensagem mensagem = new Mensagem();

    @Test
    public void testaTodosOsDadosCorretos(){
        Mensagem mensagem = new Mensagem();
        mensagem.setTexto("Esse é um texto de teste");
        mensagem.setLikes(10);
        // mensagem.setDenuncias();
    }
}

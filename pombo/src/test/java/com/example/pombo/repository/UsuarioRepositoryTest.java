package com.example.pombo.repository;


import java.util.Random;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setId(UUID.randomUUID().toString());
            usuario.setNome("Teste" + i);
            usuario.setEmail("email" + i + "@teste.com");
            usuario.setCpf(gerarCpf());
            usuario.setAdmin(false);

            usuarioRepository.save(usuario);

        }
    }

    @AfterEach
    public void tearDown(){
        usuarioRepository.deleteAll();
    }

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
        usuario.setAdmin(false);

        usuarioRepository.save(usuario);
    }

    @Test
    public void testarEmailInvalido() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome para teste");
        usuario.setEmail("emailteste.com");
        usuario.setCpf("966.326.170-69");
        usuario.setAdmin(false);

        usuarioRepository.save(usuario);
    }

    @Test
    public void testarCpfInvalido() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome para teste");
        usuario.setEmail("email@email.com");
        usuario.setCpf("11111111111");
        usuario.setAdmin(false);

        usuarioRepository.save(usuario);
    }

    private String gerarCpf() {
        Random random = new Random();
        int[] cpf = new int[9];

        // Gera os 9 primeiro digitos do cpf
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        // Calcula o primeiro dígito verificador
        cpf = calcularDigito(cpf, 9);

        // Calcula o segundo dígito verificador
        cpf = calcularDigito(cpf, 10);

        // Concatena o CPF em String
        StringBuilder cpfString = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            cpfString.append(cpf[i]);
        }

        return cpfString.toString();

    }

    // Método para calcular os dígitos verificadores
    private static int[] calcularDigito(int[] cpf, int peso) {
        int soma = 0;
        for (int i = 0; i < peso - 1; i++) {
            soma += cpf[i] * (peso - i);
        }

        int digito = 11 - (soma % 11);
        cpf[peso - 1] = digito >= 10 ? 0 : digito;
        return cpf;
    }

}

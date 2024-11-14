package com.example.pombo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.example.pombo.auth.AuthenticationService;
import com.example.pombo.exception.PomboException;
import com.example.pombo.model.entity.PerfilAcesso;
import com.example.pombo.model.entity.Usuario;
import com.example.pombo.service.UsuarioService;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("authenticate")
    public String authenticate(Authentication authentication) {
        return authenticationService.authenticate(authentication);
    }

    @PostMapping("/novoUsuario")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registrarUsuario(@RequestBody Usuario novoUsuario) throws PomboException {
        String senhaCifrada = passwordEncoder.encode(novoUsuario.getSenha());

        novoUsuario.setSenha(senhaCifrada);
        novoUsuario.setPerfilAcesso(PerfilAcesso.USUARIO);

        usuarioService.save(novoUsuario);
    }
    
}

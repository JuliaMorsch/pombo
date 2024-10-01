package com.example.pombo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pombo.exception.PomboException;
import com.example.pombo.model.entity.Usuario;
import com.example.pombo.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //   @PostMapping("/curtir/{idMensagem}/{idUsuario}")
    // public ResponseEntity<Set<Mensagem>> mensagensCurtidas(@PathVariable String idMensagem, @PathVariable String idUsuario) {  
    //     Set<Mensagem> mensagensCurtidas = usuarioService.consultarCurtidas(idMensagem, idUsuario);
    //     return ResponseEntity.ok(mensagensCurtidas);
    // }

    @Operation(summary = "Salvar Usuário", description = "Adicionar um novo Usuário.", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Erro na criação do Usuário.",
                content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"usuario\": \"Erro de validação: campo X é obrigatório\", \"status\": 400}")))
    })
    @PostMapping
    public Usuario save(@Valid @RequestBody Usuario usuario) throws PomboException {
        Usuario usuarioSalvo = usuarioService.save(usuario);
        return usuarioSalvo;
    }

    @Operation(summary = "Pesquisar Usuário por ID", 
			   description = "Busca um Usuário específico pelo seu ID.")
    @GetMapping(path = "/{id}")
    public Usuario findById(@PathVariable String id) throws PomboException {
        Usuario usuario = usuarioService.findByUsuario(id);
        return usuario;
    }

    @Operation(summary = "Listar todos os Usuários", 
			   description = "Retorna uma lista de todas os Usuários cadastradas no sistema.",
			   responses = {
					@ApiResponse(responseCode = "200", description = "Lista de Usuários retornada com sucesso")
				})
    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @Operation(summary = "Atualizar Usuário existente", description = "Atualiza os dados de um Usuário existente.")
    @PutMapping
    public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario usuario) {
        usuario = usuarioService.atualizar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Deletar Usuário por ID", description = "Remove um Usuário específico pelo seu ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

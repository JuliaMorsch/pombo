package com.example.pombo.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pombo.auth.AuthenticationService;
import com.example.pombo.exception.PomboException;
import com.example.pombo.model.entity.PerfilAcesso;
import com.example.pombo.model.entity.Usuario;
import com.example.pombo.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@MultipartConfig(fileSizeThreshold = 10485760) 
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Upload de imagem para perfil do usuário", 
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                     description = "Imagem de perfil do usuário", 
                     required = true,
                     content = @Content(
                           mediaType = "multipart/form-data",
                           schema = @Schema(type = "string", format = "binary")
                     )
               ),
               description = "Realiza o upload de uma imagem para o perfil do usuário."
               )
    @PostMapping("/{id}/upload")
    public void fazerUploadImagem(@RequestParam("imagem") MultipartFile imagem, 
                                  @PathVariable String id) throws PomboException, IOException {

            if(imagem == null) {
                throw new PomboException("Arquivo inválido");
            }

            Usuario usuarioAutenticado = authenticationService.getUsuarioAutenticado();
            if(usuarioAutenticado == null) {
                throw new PomboException("Usuário não encontrado");
            }

            if (usuarioAutenticado.getPerfilAcesso() == PerfilAcesso.USUARIO) {
                throw new PomboException("Usuário sem permissão de acesso.");
            }

            usuarioService.salvarImagemPerfil(imagem, id);
    }

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

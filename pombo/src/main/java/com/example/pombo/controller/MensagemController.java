package com.example.pombo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pombo.exception.PomboException;
import com.example.pombo.model.dto.MensagemRelatorioDTO;
import com.example.pombo.model.entity.Mensagem;
import com.example.pombo.model.entity.Usuario;
import com.example.pombo.service.MensagemService;
import java.util.Set;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;
    
    // @PostMapping("/curtir/{idMensagem}/{idUsuario}")
    // public ResponseEntity<Set<Usuario>> usuariosQueCurtiram(@PathVariable String idMensagem, @PathVariable String idUsuario) {  
    //     Set<Usuario> usuariosCurtiram = mensagemService.gerarCurtidasUsuarios(idMensagem, idUsuario);
    //     return ResponseEntity.ok(usuariosCurtiram);
    // }

    @PostMapping("/relatorioMensagem/{idMensagem}")
    public ResponseEntity<MensagemRelatorioDTO> gerarRelatorio(@PathVariable String idMensagem) throws PomboException {
        Mensagem mensagem = mensagemService.buscar(idMensagem);
        MensagemRelatorioDTO dto = mensagemService.gerarRelatorio(mensagem);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Salvar Mensagem", description = "Adicionar uma nova Mensagem.", responses = {
            @ApiResponse(responseCode = "200", description = "Mensagem criado com sucesso.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Erro na criação da Mensagem.",
                content =
                 @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"mensagem\": \"Erro de validação: campo X é obrigatório\", \"status\": 400}")))
    })
    @PostMapping
    public ResponseEntity<Mensagem> save(@Valid @RequestBody Mensagem mensagem) throws PomboException {
        mensagem = mensagemService.save(mensagem);
        return ResponseEntity.ok(mensagem);
    }

    @Operation(summary = "Pesquisar Mensagem por ID", 
    description = "Busca uma Mensagem específica pelo seu ID.")
    @GetMapping(path = "/{id}")
    public Mensagem buscar(@PathVariable String id) {
        return mensagemService.buscar(id);
    }

    @Operation(summary = "Listar todas as Mensagens", 
    description = "Retorna uma lista de todas as Mensagens cadastradas no sistema.",
    responses = {
         @ApiResponse(responseCode = "200", description = "Lista de Mensagens retornada com sucesso")
     })
    @GetMapping
    public ResponseEntity<List<Mensagem>> findAll() {
        List<Mensagem> mensagens = mensagemService.findAll();
        return new ResponseEntity<>(mensagens, HttpStatus.OK);
    }

    @Operation(summary = "Bloquear Mensagem por ID", description = "Bloqueia uma Mensagem específica pelo seu ID.")
    @PostMapping("/{mensagemId}/bloquear")
    public ResponseEntity<Void> bloquearMensagem(@PathVariable String mensagemId) {
        mensagemService.bloquearMensagem(mensagemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Deletar Mensagem por ID", description = "Remove uma Mensagem específica pelo seu ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        mensagemService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

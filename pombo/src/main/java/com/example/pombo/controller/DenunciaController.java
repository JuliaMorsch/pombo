package com.example.pombo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pombo.model.dto.DenunciaRelatorioDTO;
import com.example.pombo.model.entity.Denuncia;
import com.example.pombo.model.entity.Usuario;
import com.example.pombo.model.enums.MotivoDenuncia;
import com.example.pombo.model.filtro.DenunciaFiltro;
import com.example.pombo.service.DenunciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "/api/denuncia")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @PostMapping("/relatorioDenuncia/{idMensagem}")
    public ResponseEntity<DenunciaRelatorioDTO> gerarRelatorio(@PathVariable String idMensagem) {
        DenunciaRelatorioDTO relatorioDTO = denunciaService.gerarRelatorio(idMensagem);
        return ResponseEntity.ok(relatorioDTO);
    }

    @Operation(summary = "Criar Denúncia", description = "Criar uma Denúncia referenciando usuário e mensagem.", responses = {
            @ApiResponse(responseCode = "200", description = "Denúncia criada com sucesso.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Erro na criação da Denúncia.",
                content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"denuncia\": \"Erro de validação: campo X é obrigatório\", \"status\": 400}")))
    })
    @PostMapping
    public ResponseEntity<Denuncia> denunciarMensagem(
            @RequestParam("idMensagem") String idMensagem,
            @RequestParam("idUsuario") String idUsuario,
            @RequestParam("motivo") MotivoDenuncia motivo
    ) {

        Denuncia denuncia = denunciaService.denunciarMensagem(idMensagem, idUsuario, motivo);

        return ResponseEntity.ok(denuncia);
    }

    @Operation(summary = "Pesquisar Denúncia por ID", 
    description = "Busca uma Denúncia específica pelo seu ID.")
    @GetMapping(path = "/{idMensagem}/{idUsuario}")
    public ResponseEntity<Denuncia> buscar(@PathVariable String idMensagem, @PathVariable String idUsuario) {
        try {
            Denuncia denuncia = denunciaService.buscar(idMensagem, idUsuario);
            return ResponseEntity.ok(denuncia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Listar todos as Denúncias", 
    description = "Retorna uma lista de todas as Denúncias cadastradas no sistema.",
    responses = {
         @ApiResponse(responseCode = "200", description = "Lista de Denúncias retornada com sucesso")
     })
    @GetMapping
    public List<Denuncia> listar() {
        return denunciaService.listar();
    }

    @Operation(summary = "Pesquisar Denúncias com filtros", 
			   description = "Retorna uma lista de Denúncias que atendem aos critérios especificados no seletor.")
    @PostMapping("/filtro")
    public List<Denuncia> pesquisarComFiltro(@RequestBody DenunciaFiltro filtro) {
        return denunciaService.listarComFiltro(filtro);
    }
}
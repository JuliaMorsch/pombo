package com.example.pombo.model.dto;

import lombok.Data;

@Data
public class MensagemRelatorioDTO {
    private String idMensagem;
    private String textoOuStatus;
    private int qtdeCurtidas;
    private String nomeUsuario;
    private String uuidUsuario;
    private int qtdeDenuncias;
    
}

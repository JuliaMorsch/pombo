package com.example.pombo.model.dto;

import lombok.Data;

@Data
public class DenunciaRelatorioDTO {
    
    private String idMensagem;
    private int qntdDenuncias;
    private int qntdDenunciasPendentes;
    private int qntdDenunciasAnalisadas;
}

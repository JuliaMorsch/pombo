package com.example.pombo.model.dto;

import lombok.Data;

@Data
public class DenunciaRelatorioDTO {

    private String idUsuario;
    private int qtdDenuncias;
    private int qtdDenunciasPendentes;
    private int qtdDenunciasAnalisadas;

}

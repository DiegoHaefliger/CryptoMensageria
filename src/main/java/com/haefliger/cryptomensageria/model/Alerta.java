package com.haefliger.cryptomensageria.model;


import lombok.Data;

/**
 * Author diego-haefliger
 * Date 13/06/25
 */
@Data
public class Alerta {
    private Long estrategiaId;
    private Boolean ativo;
    private Double precoAtual;
    private String mensagem;
}

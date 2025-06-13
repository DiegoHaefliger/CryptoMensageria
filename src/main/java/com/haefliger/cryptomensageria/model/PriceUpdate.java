package com.haefliger.cryptomensageria.model;


import lombok.Data;

/**
 * Author diego-haefliger
 * Date 13/06/25
 */

@Data
public class PriceUpdate {
    private String ativo;
    private Double preco;
    private Long timestamp;
}

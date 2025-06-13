package com.haefliger.cryptomensageria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CondicaoEstrategia {

    private Long id;
    private Estrategia estrategia;
    private String tipoIndicador;
    private String operador;
    private String valor;
    private LocalDateTime dateCreated;

}

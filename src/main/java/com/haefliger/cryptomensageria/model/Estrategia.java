package com.haefliger.cryptomensageria.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estrategia {

    private Long id;
    private List<CondicaoEstrategia> condicoes;
    private String nome;
    private String simbolo;
    private String intervalo;
    private String operadorLogico;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastUpdate;
    private Boolean ativo;
    private Boolean permanente;

}

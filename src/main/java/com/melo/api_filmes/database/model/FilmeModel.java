package com.melo.api_filmes.database.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmeModel {
    private Integer id;
    private String titulo;
    private String genero;
    private int anoDeLancamento;
    private Double notaIMDB;
}

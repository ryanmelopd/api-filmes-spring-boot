package com.melo.api_filmes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmeDto {
    private String titulo;
    private String genero;
    private Integer anoDeLancamento;
    private Double notaIMDB;
}

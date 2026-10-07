package com.melo.api_filmes.service;

import com.melo.api_filmes.database.model.FilmeModel;
import com.melo.api_filmes.dto.FilmeDto;
import com.melo.api_filmes.exception.ExceptionNotFound;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmeService {
    private static final List<FilmeModel> FILMES = new ArrayList<>();

    static {
        FILMES.add(FilmeModel.builder()
                .id(1)
                .titulo("Duna: Parte Um")
                .genero("Ficção Científica")
                .anoDeLancamento(2021)
                .notaIMDB(8.0)
                .build());
    }

    public List<FilmeModel> listarTodos() {
        return new ArrayList<>(FILMES);
    }

    public List<FilmeModel> listarFilmesMaisBemAvaliados() {
        return FILMES.stream()
                .filter(filme -> filme.getNotaIMDB() >= 8.0)
                .toList();
    }

    public Optional<FilmeModel> listarFilmeMelhorAvaliado() {
        return FILMES.stream()
                .max(Comparator.comparing(FilmeModel::getNotaIMDB));
    }

    public Optional<FilmeModel> listarFilmePiorAvaliado() {
        return FILMES.stream()
                .min(Comparator.comparing(FilmeModel::getNotaIMDB));
    }

    public FilmeModel criarFilme(FilmeDto filmeDto) {
        int novoId = FILMES.stream()
                .map(FilmeModel::getId)
                .max(Integer::compareTo)
                .orElse(0) + 1;

        FilmeModel filmeModel = FilmeModel.builder()
                .id(novoId)
                .titulo(filmeDto.getTitulo())
                .genero(filmeDto.getGenero())
                .anoDeLancamento(filmeDto.getAnoDeLancamento())
                .notaIMDB(filmeDto.getNotaIMDB())
                .build();

        FILMES.add(filmeModel);
        return filmeModel;
    }

    public FilmeModel atualizarFilme(FilmeDto filmeDto, Integer id) {
        FilmeModel filmeModel = FILMES.stream()
                .filter(filme -> filme.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new ExceptionNotFound("Filme não encontrado"));

        filmeModel.setTitulo(filmeDto.getTitulo());
        filmeModel.setNotaIMDB(filmeDto.getNotaIMDB());
        filmeModel.setGenero(filmeDto.getGenero());
        filmeModel.setAnoDeLancamento(filmeDto.getAnoDeLancamento());

        return filmeModel;
    }

    public FilmeModel atualizarParcialmente(FilmeDto dto, Integer id) {
        FilmeModel filmeModel= FILMES.stream()
                .filter(filme -> filme.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new ExceptionNotFound("Filme não encontrado"));

        if (dto.getTitulo() != null) {
            filmeModel.setTitulo(dto.getTitulo());
        }

        if (dto.getGenero() != null) {
            filmeModel.setGenero(dto.getGenero());
        }

        if (dto.getAnoDeLancamento() != null) {
            filmeModel.setAnoDeLancamento(dto.getAnoDeLancamento());
        }

        if (dto.getNotaIMDB() != null) {
            filmeModel.setNotaIMDB(dto.getNotaIMDB());
        }

        return filmeModel;
    }

    public void deletarFilme(Integer id) {
        FilmeModel filme = FILMES.stream()
                .filter(f -> f.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new ExceptionNotFound("Filme não encontrado"));

        FILMES.remove(filme);
    }
}

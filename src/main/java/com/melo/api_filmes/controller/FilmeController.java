package com.melo.api_filmes.controller;

import com.melo.api_filmes.database.model.FilmeModel;
import com.melo.api_filmes.dto.FilmeDto;
import com.melo.api_filmes.service.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/filmes")
@RequiredArgsConstructor
public class FilmeController {
    private final FilmeService filmeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FilmeModel> listarTodos() {
        return filmeService.listarTodos();
    }

    @GetMapping("/melhores-avaliados")
    @ResponseStatus(HttpStatus.OK)
    public List<FilmeModel> listarFilmesMaisBemAvaliados() {
        return filmeService.listarFilmesMaisBemAvaliados();
    }

    @GetMapping("/melhor-avaliado")
    @ResponseStatus(HttpStatus.OK)
    public Optional<FilmeModel> listarFilmeMelhorAvaliado() {
        return filmeService.listarFilmeMelhorAvaliado();
    }

    @GetMapping("/pior-avaliado")
    @ResponseStatus(HttpStatus.OK)
    public Optional<FilmeModel> listarFilmePiorAvaliado() {
        return filmeService.listarFilmePiorAvaliado();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmeModel criarFilme(@RequestBody FilmeDto filmeDto) {
        return filmeService.criarFilme(filmeDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmeModel atualizarFilme(@PathVariable Integer id, @RequestBody FilmeDto filmeDto) {
        return filmeService.atualizarFilme(filmeDto, id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmeModel atualizarParcialmente(@PathVariable Integer id, @RequestBody FilmeDto filmeDto) {
        return filmeService.atualizarParcialmente(filmeDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFilme(@PathVariable Integer id) {
        filmeService.deletarFilme(id);
    }
}

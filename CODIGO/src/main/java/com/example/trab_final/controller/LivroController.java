package com.example.trab_final.controller;

import com.example.trab_final.service.AreaService;
import com.example.trab_final.service.LivroService;
import com.example.trab_final.service.AutorService;
import com.example.trab_final.service.TituloService;

import com.example.trab_final.dto.LivroDTO;
import com.example.trab_final.dto.AutorDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class LivroController {

    private final LivroService livroService;
    private final AreaService areaService;
    private final AutorService autorService;
    private final TituloService tituloService;

    public LivroController(LivroService livroService, AreaService areaService, AutorService autorService, TituloService tituloService) {
        this.livroService = livroService;
        this.areaService = areaService;
        this.autorService = autorService;
        this.tituloService = tituloService;
    }

    @PostMapping("/cadastrarLivro")
    public ResponseEntity<String> cadastrarLivro(@RequestBody LivroDTO livroDTO) {
        try {
            validarOuCadastrarArea(livroDTO.getArea());
            validarOuCadastrarAutores(livroDTO.getAutores());
            validarOuCadastrarTitulo(livroDTO);
            cadastrarLivroAssociado(livroDTO);

            return ResponseEntity.ok("Livro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao cadastrar livro.");
        }
    }

    private void validarOuCadastrarArea(String area) {
        boolean areaValida = areaService.validarArea(area);
        if (!areaValida && !areaService.cadastrarArea(area, "")) {
            throw new IllegalArgumentException("Erro ao cadastrar área inexistente.");
        }
    }

    private void validarOuCadastrarAutores(List<AutorDTO> autoresDTO) {
        List<List<String>> autores = new ArrayList<>();
        for (AutorDTO autor : autoresDTO) {
            autores.add(autor.toList());
        }

        if (!autorService.cadastrarAutores(autores)) {
            throw new IllegalArgumentException("Erro ao cadastrar autores.");
        }
    }

    private void validarOuCadastrarTitulo(LivroDTO livroDTO) {
        boolean tituloValido = tituloService.validarTitulo(livroDTO.getIsbn());
        if (!tituloValido) {
            tituloValido = tituloService.cadastrarTitulo(
                livroDTO.getPrazo(),
                livroDTO.getIsbn(),
                livroDTO.getEdicao(),
                livroDTO.getAno(),
                livroDTO.getEditora(),
                livroDTO.getPaginas(),
                livroDTO.getNome(),
                livroDTO.getArea(),
                autorService.converterAutoresParaLista(livroDTO.getAutores())
            );
        }

        if (!tituloValido) {
            throw new IllegalArgumentException("Erro ao cadastrar título.");
        }
    }

    private void cadastrarLivroAssociado(LivroDTO livroDTO) {
        boolean cadastrado = livroService.cadastrarLivro(
            livroDTO.isDisponivel(),
            livroDTO.isExemplarBiblioteca(),
            livroDTO.getIsbn()
        );

        if (!cadastrado) {
            throw new IllegalArgumentException("Erro ao cadastrar livro.");
        }
    }
}

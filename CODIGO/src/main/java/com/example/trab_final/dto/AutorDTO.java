package com.example.trab_final.dto;

import java.util.ArrayList;
import java.util.List;

public class AutorDTO {
    private String nome;
    private String sobrenome;

    public AutorDTO(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }


    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public List<String> toList() {
        List<String> autor = new ArrayList<>();
        autor.add(nome);
        autor.add(sobrenome);
        return autor;
    }
}
package com.example.trab_final.model;

public class Autor {
    private Long id;
    private String nome;
    private String sobrenome;
    private String titulacao;

    public Autor() {}

    public Autor(String nome, String sobrenome, String titulacao) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.titulacao = titulacao;
    }

    public Autor(Long id, String nome, String sobrenome, String titulacao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.titulacao = titulacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
}

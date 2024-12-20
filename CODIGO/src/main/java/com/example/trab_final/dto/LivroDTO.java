package com.example.trab_final.dto;

import java.util.List;

public class LivroDTO {
    private boolean disponivel;
    private boolean exemplarBiblioteca;
    private int prazo;
    private String isbn;
    private int edicao;
    private int ano;
    private String editora;
    private int paginas;
    private String nome;
    private String area;
    private List<AutorDTO> autores;

    public LivroDTO(boolean disponivel, boolean exemplarBiblioteca, int prazo, String isbn, int edicao, int ano, String editora, int paginas, String nome, String area, List<AutorDTO> autores) {
        this.disponivel = disponivel;
        this.exemplarBiblioteca = exemplarBiblioteca;
        this.prazo = prazo;
        this.isbn = isbn;
        this.edicao = edicao;
        this.ano = ano;
        this.editora = editora;
        this.paginas = paginas;
        this.nome = nome;
        this.area = area;
        this.autores = autores;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean isExemplarBiblioteca() {
        return exemplarBiblioteca;
    }

    public void setExemplarBiblioteca(boolean exemplarBiblioteca) {
        this.exemplarBiblioteca = exemplarBiblioteca;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<AutorDTO> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorDTO> autores) {
        this.autores = autores;
    }

}


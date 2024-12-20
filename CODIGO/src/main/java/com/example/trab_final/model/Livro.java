package com.example.trab_final.model;

public class Livro {
    private long id; 
    private boolean disponivel;
    private boolean exemplarBiblioteca;
    private Titulo objTitulo;

    public Livro() {}

    public Livro(boolean disponivel,boolean exemplarBiblioteca, Titulo objTitulo) {
        this.disponivel = disponivel;
        this.exemplarBiblioteca = exemplarBiblioteca;
        this.objTitulo = objTitulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Titulo getTitulo() {
        return objTitulo;
    }

    public void setTitulo(Titulo objTitulo) {
        this.objTitulo = objTitulo;
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
}

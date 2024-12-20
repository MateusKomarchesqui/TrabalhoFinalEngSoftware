package com.example.trab_final.model;

public class AutorTitulo {
    private Autor autor;
    private Titulo titulo;

    public AutorTitulo() {}

    public AutorTitulo(Autor autor, Titulo titulo) {
        this.autor = autor;
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "AutorLivro{" +
               "autor=" + autor.getNome() + " " + autor.getSobrenome() +
               ", titulo=" + titulo.getNome() +
               '}';
    }
}
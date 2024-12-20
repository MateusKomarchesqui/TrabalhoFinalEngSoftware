package com.example.trab_final.repository;

import java.util.List;

import com.example.trab_final.model.Autor;

public interface AutorDAO extends GenericDAO<Autor>{
    public List<Autor> readAutoresByTituloIsbn(String isbn);
    public Autor readByNome(String nome, String sobrenome);
}

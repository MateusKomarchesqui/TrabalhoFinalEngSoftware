package com.example.trab_final.repository;


import com.example.trab_final.model.Titulo;

public interface TituloDAO extends GenericDAO<Titulo> {
    public Titulo readByIsbn(String isbn);
    public Titulo readTituloByLivroId(Long livroId);
}

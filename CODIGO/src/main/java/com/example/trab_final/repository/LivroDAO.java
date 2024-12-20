package com.example.trab_final.repository;

import java.util.List;

import com.example.trab_final.model.Livro;

public interface LivroDAO extends GenericDAO<Livro> {
    public List<Livro> readByIsbnBasic(String isbn);
    public Livro readByItemEmprestimoId(Long id);
    public Livro readByItemDevolucaoId(Long id);
}

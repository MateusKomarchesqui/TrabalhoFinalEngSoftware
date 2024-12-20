package com.example.trab_final.repository;

import com.example.trab_final.model.ItemEmprestimo;

import java.util.List;


public interface ItemEmprestimoDAO extends GenericDAO<ItemEmprestimo> {
    public List<ItemEmprestimo> readByEmprestimoId(long emprestimo_id);
}

package com.example.trab_final.repository;

import java.util.List;

import com.example.trab_final.model.Emprestimo;

public interface EmprestimoDAO extends GenericDAO<Emprestimo> {
    List<Emprestimo> readByMatricula(long matricula);
}

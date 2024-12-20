package com.example.trab_final.repository;

import com.example.trab_final.model.Devolucao;

public interface DevolucaoDAO extends GenericDAO<Devolucao> {
    public Devolucao readByEmprestimoId(Long emprestimoId);
}

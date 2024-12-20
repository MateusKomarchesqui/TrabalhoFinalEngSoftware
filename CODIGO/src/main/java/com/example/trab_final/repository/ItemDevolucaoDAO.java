package com.example.trab_final.repository;

import com.example.trab_final.model.ItemDevolucao;

import java.util.List;

public interface ItemDevolucaoDAO extends GenericDAO<ItemDevolucao> {
    public List<ItemDevolucao> readByDevolucaoId(Long devolucaoId);
}

package com.example.trab_final.repository;

import com.example.trab_final.model.Area;

public interface AreaDAO extends GenericDAO<Area> {
    Area readByNome(String nome);
    Area readByIsbn(String isbn);
}

package com.example.trab_final.repository;

import java.util.List;

import com.example.trab_final.model.Debito;

public interface DebitoDAO extends GenericDAO<Debito>{
    public List<Debito> readByMatricula(long matricula);
}

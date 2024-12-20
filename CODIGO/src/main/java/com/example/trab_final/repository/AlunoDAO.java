package com.example.trab_final.repository;

import com.example.trab_final.model.Aluno;

public interface AlunoDAO extends GenericDAO<Aluno> {
    public Aluno readByMatricula(long matricula);
    public Aluno readByCpf(long cpf);
}

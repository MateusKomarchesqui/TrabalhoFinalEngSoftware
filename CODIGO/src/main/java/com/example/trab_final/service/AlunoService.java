package com.example.trab_final.service;

import com.example.trab_final.model.Aluno;
import com.example.trab_final.repository.AlunoDAO;
import com.example.trab_final.repository.factory.DAOFactory;

import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    public AlunoService() {}

    public boolean validarAluno(Long matricula) {
        AlunoDAO alunoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAlunoDAO();
        return alunoDAO.readByMatricula(matricula) != null;
    }

    public boolean cadastrarAluno(Long matricula, String nome, long cpf, String endereco) {
        Aluno aluno = new Aluno(matricula, nome, cpf, endereco);
        AlunoDAO alunoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAlunoDAO();
        return alunoDAO.create(aluno) != null;
    }
    
}

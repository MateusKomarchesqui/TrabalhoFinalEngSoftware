package com.example.trab_final.repository.postgres;

import com.example.trab_final.model.Aluno;
import com.example.trab_final.repository.AlunoDAO;
import com.example.trab_final.connection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresAlunoDAO implements AlunoDAO{

    private static PostgresAlunoDAO instance;

    private PostgresAlunoDAO() { }

    public static synchronized PostgresAlunoDAO getInstance() {
        if (instance == null) {
            instance = new PostgresAlunoDAO();
        }
        return instance;
    }

    @Override
    public Aluno readById(Long id) {
        String sql = "SELECT * FROM aluno WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(rs.getLong("matricula"), rs.getString("nome"), rs.getLong("cpf"), rs.getString("endereco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Aluno> readAll() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getLong("matricula"), rs.getString("nome"), rs.getLong("cpf"), rs.getString("endereco"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    @Override
    public Aluno create(Aluno obj) {
        String sql = "INSERT INTO aluno (matricula, nome, cpf, endereco) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getMatricula());
            stmt.setString(2, obj.getNome());
            stmt.setLong(3, obj.getCpf());
            stmt.setString(4, obj.getEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

    @Override
    public Aluno update(Aluno obj) {
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, endereco = ? WHERE matricula = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, obj.getNome());
            stmt.setLong(2, obj.getCpf());
            stmt.setString(3, obj.getEndereco());
            stmt.setLong(4, obj.getMatricula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

    @Override
    public boolean delete(Aluno obj) {
        String sql = "DELETE FROM aluno WHERE matricula = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getMatricula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Aluno readByMatricula(long matricula) {
        String sql = "SELECT * FROM aluno WHERE matricula = ?";
        
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(rs.getLong("matricula"), rs.getString("nome"), rs.getLong("cpf"), rs.getString("endereco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public Aluno readByCpf(long cpf) {
        String sql = "SELECT * FROM aluno WHERE cpf = ?";
        
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(rs.getLong("matricula"), rs.getString("nome"), rs.getLong("cpf"), rs.getString("endereco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}

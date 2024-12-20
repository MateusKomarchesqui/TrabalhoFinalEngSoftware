package com.example.trab_final.repository.postgres;

import com.example.trab_final.model.Devolucao;
import com.example.trab_final.repository.DevolucaoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.trab_final.connection.PostgresConnection;


public class PostgresDevolucaoDAO implements DevolucaoDAO {
    private static PostgresDevolucaoDAO instance;

    private PostgresDevolucaoDAO() {}

    public static synchronized PostgresDevolucaoDAO getInstance() {
        if (instance == null) {
            instance = new PostgresDevolucaoDAO();
        }
        return instance;
    }

    @Override
    public Devolucao readById(Long id) {
        String sql = "SELECT * FROM devolucao WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Devolucao devolucao = new Devolucao();
                devolucao.setId(rs.getLong("id"));
                devolucao.setEmprestimo_id(rs.getLong("emprestimo_id"));
                devolucao.setValorMulta(rs.getFloat("valor_multa"));
                devolucao.setItens(null);
                return devolucao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Devolucao> readAll() {
        String sql = "SELECT * FROM devolucao";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Devolucao devolucao = new Devolucao();
                devolucao.setId(rs.getLong("id"));
                devolucao.setEmprestimo_id(rs.getLong("emprestimo_id"));
                devolucao.setValorMulta(rs.getFloat("valor_multa"));
                devolucao.setItens(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Devolucao create(Devolucao devolucao) {
        String sql = "INSERT INTO devolucao (emprestimo_id, valor_multa) VALUES (?, ?) returning id";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, devolucao.getEmprestimo_id());
            stmt.setFloat(2, devolucao.getValorMulta());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                devolucao.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return devolucao;
    }

    @Override
    public Devolucao update(Devolucao devolucao) {
        String sql = "UPDATE devolucao SET emprestimo_id = ?, valor_multa = ? WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, devolucao.getEmprestimo_id());
            stmt.setFloat(2, devolucao.getValorMulta());
            stmt.setLong(3, devolucao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return devolucao;
    }

    @Override
    public boolean delete(Devolucao devolucao) {
        String sql = "DELETE FROM devolucao WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, devolucao.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Devolucao readByEmprestimoId(Long id) {
        String sql = "SELECT * FROM devolucao WHERE emprestimo_id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Devolucao devolucao = new Devolucao();
                devolucao.setId(rs.getLong("id"));
                devolucao.setEmprestimo_id(rs.getLong("emprestimo_id"));
                devolucao.setValorMulta(rs.getFloat("valor_multa"));
                devolucao.setItens(null);
                return devolucao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}

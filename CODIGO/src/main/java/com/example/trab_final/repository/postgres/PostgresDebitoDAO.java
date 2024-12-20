package com.example.trab_final.repository.postgres;

import com.example.trab_final.model.Debito;
import com.example.trab_final.repository.DebitoDAO;
import com.example.trab_final.connection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresDebitoDAO implements DebitoDAO {

    private static PostgresDebitoDAO instance;

    private PostgresDebitoDAO() {}

    public static synchronized PostgresDebitoDAO getInstance() {
        if (instance == null) {
            instance = new PostgresDebitoDAO();
        }
        return instance;
    }

    @Override
    public Debito readById(Long id) {
        String sql = "SELECT * FROM debito WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Debito debito = new Debito();
                debito.setId(rs.getLong("id"));
                debito.setMatricula(rs.getInt("matricula"));
                debito.setData(rs.getDate("data"));
                debito.setValor(rs.getFloat("valor"));
                return debito;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Debito> readAll() {
        List<Debito> debitos = new ArrayList<>();
        String sql = "SELECT * FROM debito";
        
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Debito debito = new Debito();
                debito.setId(rs.getLong("id"));
                debito.setMatricula(rs.getInt("matricula"));
                debito.setData(rs.getDate("data"));
                debito.setValor(rs.getFloat("valor"));
                debitos.add(debito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return debitos;
    }

    @Override
    public Debito create(Debito obj) {
        String sql = "INSERT INTO debito (matricula, data, valor) VALUES (?, ?, ?) RETURNING id";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getMatricula());
            stmt.setDate(2, new Date(obj.getData().getTime()));
            stmt.setFloat(3, obj.getValor());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                obj.setId(rs.getLong("id"));
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Debito update(Debito obj) {
        String sql = "UPDATE debito SET matricula = ?, data = ?, valor = ? WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getMatricula());
            stmt.setDate(2, new Date(obj.getData().getTime()));
            stmt.setFloat(3, obj.getValor());
            stmt.setLong(4, obj.getId());
            stmt.executeUpdate();
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Debito obj) {
        String sql = "DELETE FROM debito WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Debito> readByMatricula(long matricula) {
        String sql = "SELECT * FROM debito WHERE matricula = ?";

        List<Debito> debitos = new ArrayList<>();

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, matricula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Debito debito = new Debito();
                debito.setId(rs.getLong("id"));
                debito.setMatricula(rs.getLong("matricula"));
                debito.setData(rs.getDate("data"));
                debito.setValor(rs.getFloat("valor"));
                debitos.add(debito);
            }

            return debitos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
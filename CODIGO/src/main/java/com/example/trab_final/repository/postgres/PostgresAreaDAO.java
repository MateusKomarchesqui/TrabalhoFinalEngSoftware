package com.example.trab_final.repository.postgres;

import com.example.trab_final.model.Area;
import com.example.trab_final.repository.AreaDAO;
import com.example.trab_final.connection.PostgresConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

public class PostgresAreaDAO implements AreaDAO {
    private static PostgresAreaDAO instance;

    private PostgresAreaDAO() {}

    public static synchronized PostgresAreaDAO getInstance() {
        if (instance == null) {
            instance = new PostgresAreaDAO();
        }
        return instance;
    }

    @Override
    public Area readByNome(String nome) {
        String sql = "SELECT * FROM Area WHERE nome = ?";
        
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Area area = new Area(rs.getString("nome"), rs.getString("descricao"));
                return area;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Area readById(Long id) {
        String sql = "SELECT * FROM Area WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Area area = new Area(rs.getString("nome"), rs.getString("descricao"));
                return area;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Area> readAll() {
        String sql = "SELECT * FROM Area";
        
        List<Area> areas = new java.util.ArrayList<>();
        
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Area area = new Area(rs.getString("nome"), rs.getString("descricao"));
                areas.add(area);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return areas;
    }

    @Override
    public Area create(Area obj) {
        String sql = "INSERT INTO Area (nome, descricao) VALUES (?, ?)";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getDescricao());
            stmt.executeUpdate();
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public Area update(Area obj) {
        String sql = "UPDATE Area SET descricao = ? WHERE nome = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, obj.getDescricao());
            stmt.setString(2, obj.getNome());
            stmt.executeUpdate();
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(Area obj) {
        String sql = "DELETE FROM Area WHERE nome = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, obj.getNome());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Area readByIsbn(String isbn) {
        String sql = """
                SELECT a.nome as a_nome, a.descricao as a_descricao FROM public.area a
                JOIN public.titulo t ON a.nome = t.area_nome
                WHERE t.isbn = ?
                """;

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Area area = new Area(rs.getString("a_nome"), rs.getString("a_descricao"));
                return area;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

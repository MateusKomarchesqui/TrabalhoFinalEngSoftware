package com.example.trab_final.repository.postgres;

import com.example.trab_final.model.ItemDevolucao;
import com.example.trab_final.repository.ItemDevolucaoDAO;

import java.util.List;
import java.util.ArrayList;

import com.example.trab_final.connection.PostgresConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;


public class PostgresItemDevolucaoDAO implements ItemDevolucaoDAO {
    private static PostgresItemDevolucaoDAO instance;

    private PostgresItemDevolucaoDAO() {}

    public static synchronized PostgresItemDevolucaoDAO getInstance() {
        if (instance == null) {
            instance = new PostgresItemDevolucaoDAO();
        }
        return instance;
    }

    @Override
    public ItemDevolucao readById(Long id) {
        String sql = "SELECT * FROM item_devolucao WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ItemDevolucao(
                    rs.getLong("id"),
                    rs.getLong("devolucao_id"),
                    null,
                    rs.getDate("data_devolucao"),
                    rs.getInt("dias_atraso")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemDevolucao> readAll() {
        String sql = "SELECT * FROM item_devolucao";
        List<ItemDevolucao> itensDevolucao = new ArrayList<>();

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                itensDevolucao.add(new ItemDevolucao(
                    rs.getLong("id"),
                    rs.getLong("devolucao_id"),
                    null,
                    rs.getDate("data_devolucao"),
                    rs.getInt("dias_atraso")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itensDevolucao;
    }


    @Override
    public ItemDevolucao create(ItemDevolucao itemDevolucao) {
        String sql = "INSERT INTO item_devolucao (devolucao_id, livro_id, data_devolucao, dias_atraso) VALUES (?, ?, ?, ?) returning id";
        
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, itemDevolucao.getDevolucao_id());
            stmt.setLong(2, itemDevolucao.getLivro().getId());
            stmt.setDate(3, new java.sql.Date(itemDevolucao.getDataDevolucao().getTime()));
            stmt.setInt(4, itemDevolucao.getDiasAtraso());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                itemDevolucao.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return itemDevolucao;
    }

    @Override
    public ItemDevolucao update(ItemDevolucao itemDevolucao) {
        String sql = "UPDATE item_devolucao SET devolucao_id = ?, livro_id = ?, data_devolucao = ?, dias_atraso = ? WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, itemDevolucao.getDevolucao_id());
            stmt.setLong(2, itemDevolucao.getLivro().getId());
            stmt.setDate(3, new java.sql.Date(itemDevolucao.getDataDevolucao().getTime()));
            stmt.setInt(4, itemDevolucao.getDiasAtraso());
            stmt.setLong(5, itemDevolucao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return itemDevolucao;
    }

    @Override
    public boolean delete(ItemDevolucao itemDevolucao) {
        String sql = "DELETE FROM item_devolucao WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, itemDevolucao.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ItemDevolucao> readByDevolucaoId(Long id) {
        List<ItemDevolucao> itensDevolucao = new ArrayList<>();
        String sql = "SELECT * FROM item_devolucao WHERE devolucao_id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                itensDevolucao.add(new ItemDevolucao(
                    rs.getLong("id"),
                    rs.getLong("devolucao_id"),
                    null,
                    rs.getDate("data_devolucao"),
                    rs.getInt("dias_atraso")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itensDevolucao;
    }

}

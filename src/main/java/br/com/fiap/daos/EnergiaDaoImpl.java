package br.com.fiap.daos;

import br.com.fiap.models.Energia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class EnergiaDaoImpl implements EnergiaDao {

    @Override
    public void save(Energia energia, Connection connection) throws SQLException {
        String sql = "INSERT INTO energia (id, id_bicicleta, energia_gerada, data_geracao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, energia.getId());
            stmt.setInt(2, energia.getIdBicicleta());
            stmt.setDouble(3, energia.getEnergiaGerada());
            stmt.setTimestamp(4, Timestamp.valueOf(energia.getDataGeracao()));
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Energia> findByBicicleta(int idBicicleta, Connection connection) throws SQLException {
        String sql = "SELECT * FROM energia WHERE id_bicicleta = ?";
        List<Energia> energias = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idBicicleta);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    energias.add(new Energia(
                            rs.getInt("id"),
                            rs.getInt("id_bicicleta"),
                            rs.getDouble("energia_gerada"),
                            rs.getTimestamp("data_geracao").toLocalDateTime()
                    ));
                }
            }
        }
        return energias;
    }

    @Override
    public Energia findById(int id, Connection connection) throws SQLException {
        String sql = "SELECT * FROM energia WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Energia(
                            rs.getInt("id"),
                            rs.getInt("id_bicicleta"),
                            rs.getDouble("energia_gerada"),
                            rs.getTimestamp("data_geracao").toLocalDateTime()
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Energia> findAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM energia";
        List<Energia> energias = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                energias.add(new Energia(
                        rs.getInt("id"),
                        rs.getInt("id_bicicleta"),
                        rs.getDouble("energia_gerada"),
                        rs.getTimestamp("data_geracao").toLocalDateTime()
                ));
            }
        }
        return energias;
    }

    @Override
    public void update(Energia energia, Connection connection) throws SQLException {
        String sql = "UPDATE energia SET id_bicicleta = ?, energia_gerada = ?, data_geracao = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, energia.getIdBicicleta());
            stmt.setDouble(2, energia.getEnergiaGerada());
            stmt.setTimestamp(3, Timestamp.valueOf(energia.getDataGeracao()));
            stmt.setInt(4, energia.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id, Connection connection) throws SQLException {
        String sql = "DELETE FROM energia WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

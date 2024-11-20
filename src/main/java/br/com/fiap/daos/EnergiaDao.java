package br.com.fiap.daos;

import br.com.fiap.models.Energia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EnergiaDao {
    void save(Energia energia, Connection connection) throws SQLException;

    List<Energia> findByBicicleta(int idBicicleta, Connection connection) throws SQLException;

    Energia findById(int id, Connection connection) throws SQLException;

    List<Energia> findAll(Connection connection) throws SQLException;

    void update(Energia energia, Connection connection) throws SQLException;

    void delete(int id, Connection connection) throws SQLException;
}

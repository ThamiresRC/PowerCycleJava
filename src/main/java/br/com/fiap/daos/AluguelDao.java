package br.com.fiap.daos;

import br.com.fiap.exceptions.AluguelNotFound;
import br.com.fiap.models.Aluguel;
import br.com.fiap.exceptions.AluguelNotSavedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AluguelDao {

    List<Aluguel> findAll() throws SQLException;

    void save(Aluguel aluguel, Connection connection) throws SQLException, AluguelNotSavedException;

    Aluguel update(Aluguel aluguel, Connection connection) throws SQLException, AluguelNotFound;

    void deleteById(int id, Connection connection) throws SQLException, AluguelNotFound;
}

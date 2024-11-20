package br.com.fiap.daos;

import br.com.fiap.models.Bicicleta;
import br.com.fiap.exceptions.BicicletaNaoDisponivelException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BicicletaDao {

    List<Bicicleta> findAll() throws SQLException;

    void save(Bicicleta bicicleta, Connection connection) throws SQLException;

    Bicicleta update(Bicicleta bicicleta, Connection connection) throws SQLException;

    void deleteById(int id, Connection connection) throws SQLException;

    Bicicleta findById(int id) throws SQLException, BicicletaNaoDisponivelException;

    void alterarDisponibilidade(int id, boolean disponivel, Connection connection) throws SQLException, BicicletaNaoDisponivelException;
}

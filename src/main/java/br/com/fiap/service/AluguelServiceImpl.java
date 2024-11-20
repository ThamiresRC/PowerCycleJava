package br.com.fiap.service;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.daos.AluguelDao;
import br.com.fiap.daos.AluguelDaoFactory;
import br.com.fiap.exceptions.AluguelNotSavedException;
import br.com.fiap.exceptions.AluguelNotFound;
import br.com.fiap.exceptions.AluguelServiceException;
import br.com.fiap.models.Aluguel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class AluguelServiceImpl implements AluguelService {

    private AluguelDao dao = AluguelDaoFactory.create();

    @Override
    public List<Aluguel> listarTodos() throws AluguelServiceException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            throw new AluguelServiceException("Erro ao listar aluguéis", e);
        }
    }

    @Override
    public Aluguel create(Aluguel aluguel) throws AluguelNotSavedException, SQLException {
        if (aluguel.getId() != 0) {
            throw new AluguelNotSavedException("ID do aluguel já existe.");
        }

        Connection conn = DatabaseConnectionFactory.create().get();
        try {
            this.dao.save(aluguel, conn);
            conn.commit();
        } catch (SQLException | AluguelNotSavedException e) {
            conn.rollback();
            throw e;
        }
        return aluguel;
    }

    @Override
    public Aluguel update(Aluguel aluguel) throws AluguelNotFound, AluguelNotSavedException {
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            aluguel = this.dao.update(aluguel, conn);
            conn.commit();
        } catch (SQLException e) {
            throw new AluguelNotSavedException("Erro ao atualizar o aluguel.");
        }
        return aluguel;
    }

    @Override
    public void delete(int id) throws AluguelNotFound, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}

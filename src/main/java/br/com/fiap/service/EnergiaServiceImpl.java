package br.com.fiap.service;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.daos.EnergiaDao;
import br.com.fiap.daos.EnergiaDaoFactory;
import br.com.fiap.exceptions.EnergiaNotSavedException;
import br.com.fiap.exceptions.EnergiaNotFoundException;
import br.com.fiap.exceptions.EnergiaServiceException;
import br.com.fiap.models.Energia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class EnergiaServiceImpl implements EnergiaService {

    private EnergiaDao dao = EnergiaDaoFactory.create();

    @Override
    public List<Energia> listarTodos() throws EnergiaServiceException {
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            return dao.findAll(conn);
        } catch (SQLException e) {
            throw new EnergiaServiceException("Erro ao listar energias", e);
        }
    }

    @Override
    public Energia create(Energia energia) throws EnergiaNotSavedException, SQLException {
        if (energia.getId() != 0) {
            throw new EnergiaNotSavedException("ID da energia já existe.");
        }

        Connection conn = DatabaseConnectionFactory.create().get();
        try {
            dao.save(energia, conn);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new EnergiaNotSavedException("Falha ao salvar a energia", e);
        }
        return energia;
    }

    @Override
    public Energia update(Energia energia) throws EnergiaNotFoundException, EnergiaNotSavedException {
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            dao.update(energia, conn);
            conn.commit();
        } catch (SQLException e) {
            throw new EnergiaNotSavedException("Erro ao atualizar a energia.", e);
        }
        return energia;
    }

    @Override
    public void delete(int id) throws EnergiaNotFoundException, SQLException {
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            dao.delete(id, connection);
            connection.commit();
        } catch (SQLException e) {
            throw new EnergiaNotFoundException("Erro ao excluir energia com ID " + id, e);
        }
    }


}

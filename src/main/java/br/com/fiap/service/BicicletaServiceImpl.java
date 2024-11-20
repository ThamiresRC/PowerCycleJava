package br.com.fiap.service;

import br.com.fiap.daos.BicicletaDao;
import br.com.fiap.daos.BicicletaDaoFactory;
import br.com.fiap.exceptions.BicicletaNaoDisponivelException;
import br.com.fiap.models.Bicicleta;
import br.com.fiap.config.DatabaseConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class BicicletaServiceImpl implements BicicletaService {

    private final BicicletaDao bicicletaDao;

    public BicicletaServiceImpl() {
        this.bicicletaDao = BicicletaDaoFactory.create();
    }

    @Override
    public List<Bicicleta> listarTodas() throws SQLException {
        return bicicletaDao.findAll();
    }

    @Override
    public Bicicleta create(Bicicleta bicicleta) throws SQLException {
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            bicicletaDao.save(bicicleta, connection);
        }
        return bicicleta;
    }

    @Override
    public Bicicleta update(Bicicleta bicicleta) throws SQLException, BicicletaNaoDisponivelException {
        if (!bicicleta.isDisponivel()) {
            throw new BicicletaNaoDisponivelException("Não é possível atualizar a bicicleta, pois ela está indisponível.");
        }
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            return bicicletaDao.update(bicicleta, connection);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            bicicletaDao.deleteById(id, connection);
        }
    }

    @Override
    public void acumularEnergia(int id, double distancia) throws SQLException, BicicletaNaoDisponivelException {
        Bicicleta bicicleta = bicicletaDao.findById(id);
        if (bicicleta == null) {
            throw new SQLException("Bicicleta não encontrada.");
        }
        if (!bicicleta.isDisponivel()) {
            throw new BicicletaNaoDisponivelException("Bicicleta não disponível para acumular energia.");
        }
        bicicleta.acumularEnergia(distancia);
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            bicicletaDao.update(bicicleta, connection);
        }
    }
}

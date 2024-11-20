package br.com.fiap.service;

import br.com.fiap.exceptions.BicicletaNaoDisponivelException;
import br.com.fiap.models.Bicicleta;

import java.sql.SQLException;
import java.util.List;

public interface BicicletaService {

    List<Bicicleta> listarTodas() throws SQLException;

    Bicicleta create(Bicicleta bicicleta) throws SQLException;

    Bicicleta update(Bicicleta bicicleta) throws SQLException, BicicletaNaoDisponivelException;

    void delete(int id) throws SQLException;

    void acumularEnergia(int id, double distancia) throws SQLException, BicicletaNaoDisponivelException;
}

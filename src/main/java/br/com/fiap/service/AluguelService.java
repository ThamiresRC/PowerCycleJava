package br.com.fiap.service;

import br.com.fiap.exceptions.AluguelNotSavedException;
import br.com.fiap.exceptions.AluguelNotFound;
import br.com.fiap.exceptions.AluguelServiceException;
import br.com.fiap.models.Aluguel;

import java.sql.SQLException;
import java.util.List;

public interface AluguelService {

    List<Aluguel> listarTodos() throws AluguelServiceException;

    Aluguel create(Aluguel aluguel) throws AluguelNotSavedException, SQLException;

    Aluguel update(Aluguel aluguel) throws AluguelNotFound, AluguelNotSavedException;

    void delete(int id) throws AluguelNotFound, SQLException;
}

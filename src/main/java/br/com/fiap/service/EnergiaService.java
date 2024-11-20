package br.com.fiap.service;

import br.com.fiap.exceptions.EnergiaNotFoundException;
import br.com.fiap.exceptions.EnergiaNotSavedException;
import br.com.fiap.exceptions.EnergiaServiceException;
import br.com.fiap.models.Energia;

import java.sql.SQLException;
import java.util.List;

public interface EnergiaService {

    List<Energia> listarTodos() throws EnergiaServiceException;

    Energia create(Energia energia) throws EnergiaNotSavedException, SQLException;

    Energia update(Energia energia) throws EnergiaNotFoundException, EnergiaNotSavedException;

    void delete(int id) throws EnergiaNotFoundException, SQLException;
}

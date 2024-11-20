package br.com.fiap.daos;

import br.com.fiap.exceptions.UsuarioNotFoundException;
import br.com.fiap.models.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao {

    List<Usuario> findAll() throws SQLException;

    void save(Usuario usuario, Connection connection) throws SQLException;

    Usuario update(Usuario usuario, Connection connection) throws SQLException, UsuarioNotFoundException;

    void deleteById(int id, Connection connection) throws SQLException, UsuarioNotFoundException;
}

package br.com.fiap.service;

import br.com.fiap.exceptions.UsuarioNotFoundException;
import br.com.fiap.models.Usuario;
import br.com.fiap.dtos.UsuarioDto;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioService {

    List<Usuario> listarTodos() throws SQLException;

    Usuario create(UsuarioDto usuarioDto) throws SQLException;

    Usuario update(UsuarioDto usuarioDto) throws UsuarioNotFoundException, SQLException;

    void delete(int id) throws UsuarioNotFoundException, SQLException;
}

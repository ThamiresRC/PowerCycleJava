package br.com.fiap.service;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.daos.UsuarioDao;
import br.com.fiap.daos.UsuarioDaoFactory;
import br.com.fiap.exceptions.UsuarioNotFoundException;
import br.com.fiap.models.Usuario;
import br.com.fiap.dtos.UsuarioDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDao dao = UsuarioDaoFactory.create();

    @Override
    public List<Usuario> listarTodos() throws SQLException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar usuários", e);
        }
    }

    @Override
    public Usuario create(UsuarioDto usuarioDto) throws SQLException {
        if (usuarioDto.getId() != 0) {
            throw new IllegalArgumentException("ID do usuário já existe.");
        }

        Usuario usuario = new Usuario(
                usuarioDto.getId(),
                usuarioDto.getNome(),
                usuarioDto.getEmail(),
                usuarioDto.getIdade(),
                usuarioDto.getCpf()
        );

        Connection conn = DatabaseConnectionFactory.create().get();
        try {
            this.dao.save(usuario, conn);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException("Erro ao salvar o usuário", e);
        }
        return usuario;
    }

    @Override
    public Usuario update(UsuarioDto usuarioDto) throws UsuarioNotFoundException, SQLException {
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            Usuario usuario = new Usuario(
                    usuarioDto.getId(),
                    usuarioDto.getNome(),
                    usuarioDto.getEmail(),
                    usuarioDto.getIdade(),
                    usuarioDto.getCpf()
            );
            usuario = this.dao.update(usuario, conn);
            conn.commit();
            return usuario;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o usuário", e);
        }
    }

    @Override
    public void delete(int id) throws UsuarioNotFoundException, SQLException {
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            this.dao.deleteById(id, connection);
            connection.commit();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir o usuário", e);
        }
    }
}

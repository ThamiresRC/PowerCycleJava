package br.com.fiap.daos;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.exceptions.UsuarioNotFoundException;
import br.com.fiap.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class UsuarioDaoImpl implements UsuarioDao {

    private static final Logger logger = Logger.getLogger(UsuarioDaoImpl.class.getName());

    @Override
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getInt("idade"),
                        rs.getString("cpf")
                );
                usuario.setAlugueisEmAberto(rs.getInt("alugueis_em_aberto"));
                usuarios.add(usuario);
            }

            logger.info("Total de usuários encontrados: " + usuarios.size());

        } catch (SQLException e) {
            logger.warning("Erro ao recuperar usuários do banco de dados: " + e.getMessage());
            throw e;
        }
        return usuarios;
    }

    @Override
    public void save(Usuario usuario, Connection connection) throws SQLException{
        String sql = "INSERT INTO usuarios (nome, email, idade, cpf, alugueis_em_aberto) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getIdade());
            stmt.setString(4, usuario.getCpf());
            stmt.setInt(5, usuario.getAlugueisEmAberto());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setId(generatedKeys.getInt(1));
                        logger.info("Usuário inserido com sucesso. ID gerado: " + usuario.getId());
                    }
                }
            } else {
                logger.warning("Nenhuma linha foi afetada ao tentar inserir usuário.");
            }
        } catch (SQLException e) {
            logger.severe("Erro ao inserir usuário no banco de dados: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Usuario update(Usuario usuario, Connection connection) throws SQLException, UsuarioNotFoundException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, idade = ?, cpf = ?, alugueis_em_aberto = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getIdade());
            stmt.setString(4, usuario.getCpf());
            stmt.setInt(5, usuario.getAlugueisEmAberto());
            stmt.setInt(6, usuario.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Usuário atualizado com sucesso. ID: " + usuario.getId());
                return usuario;
            } else {
                logger.warning("Nenhuma linha foi afetada ao tentar atualizar usuário com ID: " + usuario.getId());
                throw new UsuarioNotFoundException(usuario.getId());
            }
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar usuário no banco de dados: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteById(int id, Connection connection) throws SQLException, UsuarioNotFoundException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Nenhuma linha foi afetada ao tentar deletar usuário com ID: " + id);
                throw new UsuarioNotFoundException(id);
            } else {
                logger.info("Usuário deletado com sucesso. ID: " + id);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao deletar usuário do banco de dados: " + e.getMessage());
            throw e;
        }
    }
}

package br.com.fiap.daos;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.exceptions.AluguelNotFound;
import br.com.fiap.exceptions.AluguelNotSavedException;
import br.com.fiap.models.Aluguel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class AluguelDaoImpl implements AluguelDao {

    private static final Logger logger = Logger.getLogger(AluguelDaoImpl.class.getName());

    @Override
    public List<Aluguel> findAll() throws SQLException {
        List<Aluguel> alugueis = new ArrayList<>();
        String sql = "SELECT * FROM ALUGUEL";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluguel aluguel = new Aluguel(
                        rs.getInt("id"),
                        rs.getInt("idBicicleta"),
                        rs.getInt("idUsuario"),
                        rs.getTimestamp("inicioAluguel").toLocalDateTime()
                );
                alugueis.add(aluguel);
            }

            logger.info("Total de alugueis encontrados: " + alugueis.size());

        } catch (SQLException e) {
            logger.warning("Erro ao recuperar alugueis do banco de dados: " + e.getMessage());
            throw e;
        }
        return alugueis;
    }

    @Override
    public void save(Aluguel aluguel, Connection connection) throws SQLException, AluguelNotSavedException {
        String sql = "INSERT INTO ALUGUEL (idBicicleta, idUsuario, inicioAluguel) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, aluguel.getIdBicicleta());
            stmt.setInt(2, aluguel.getIdUsuario());
            stmt.setTimestamp(3, Timestamp.valueOf(aluguel.getInicioAluguel()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        aluguel.setId(generatedKeys.getInt(1));
                        logger.info("Aluguel inserido com sucesso. ID gerado: " + aluguel.getId());
                    }
                }
            } else {
                logger.warning("Nenhuma linha foi afetada ao tentar inserir aluguel.");
                throw new AluguelNotSavedException();
            }
        } catch (SQLException e) {
            logger.severe("Erro ao inserir aluguel no banco de dados: " + e.getMessage());
            throw e;
        }
    }


    @Override
    public Aluguel update(Aluguel aluguel, Connection connection) throws SQLException, AluguelNotFound {
        String sql = "UPDATE ALUGUEL SET idBicicleta = ?, idUsuario = ?, inicioAluguel = ?, fimAluguel = ?, distanciaPercorrida = ?, tempoDeUso = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, aluguel.getIdBicicleta());
            stmt.setInt(2, aluguel.getIdUsuario());
            stmt.setTimestamp(3, Timestamp.valueOf(aluguel.getInicioAluguel()));
            stmt.setTimestamp(4, Timestamp.valueOf(aluguel.getFimAluguel()));
            stmt.setDouble(5, aluguel.getDistanciaPercorrida());
            stmt.setDouble(6, aluguel.getTempoDeUso());
            stmt.setInt(7, aluguel.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Aluguel atualizado com sucesso. ID: " + aluguel.getId());
                return aluguel;
            } else {
                logger.warning("Nenhuma linha foi afetada ao tentar atualizar aluguel com ID: " + aluguel.getId());
                throw new AluguelNotFound(aluguel.getId());
            }
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar aluguel no banco de dados: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteById(int id, Connection connection) throws SQLException, AluguelNotFound {
        String sql = "DELETE FROM ALUGUEL WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Nenhuma linha foi afetada ao tentar deletar aluguel com ID: " + id);
                throw new AluguelNotFound(id);
            } else {
                logger.info("Aluguel deletado com sucesso. ID: " + id);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao deletar aluguel do banco de dados: " + e.getMessage());
            throw e;
        }
    }
}


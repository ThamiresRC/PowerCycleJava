package br.com.fiap.daos;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.models.Bicicleta;
import br.com.fiap.exceptions.BicicletaNaoDisponivelException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class BicicletaDaoImpl implements BicicletaDao {

    private static final Logger logger = Logger.getLogger(BicicletaDaoImpl.class.getName());

    @Override
    public List<Bicicleta> findAll() throws SQLException {
        List<Bicicleta> bicicletas = new ArrayList<>();
        String sql = "SELECT * FROM BICICLETA";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bicicleta bicicleta = new Bicicleta(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getBoolean("disponivel")
                );
                bicicleta.setEnergiaAcumulada(rs.getDouble("energia_acumulada"));
                bicicletas.add(bicicleta);
            }

            logger.info("Total de bicicletas encontradas: " + bicicletas.size());

        } catch (SQLException e) {
            logger.warning("Erro ao recuperar bicicletas do banco de dados: " + e.getMessage());
            throw e;
        }
        return bicicletas;
    }

    @Override
    public void save(Bicicleta bicicleta, Connection connection) throws SQLException {
        String sql = "INSERT INTO BICICLETA (modelo, disponivel, energia_acumulada) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, bicicleta.getModelo());
            stmt.setBoolean(2, bicicleta.isDisponivel());
            stmt.setDouble(3, bicicleta.getEnergiaAcumulada());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        bicicleta.setId(generatedKeys.getInt(1));
                        logger.info("Bicicleta inserida com sucesso. ID gerado: " + bicicleta.getId());
                    }
                }
            } else {
                logger.warning("Nenhuma linha foi afetada ao tentar inserir bicicleta.");
            }
        } catch (SQLException e) {
            logger.severe("Erro ao inserir bicicleta no banco de dados: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Bicicleta update(Bicicleta bicicleta, Connection connection) throws SQLException {
        String sql = "UPDATE BICICLETA SET modelo = ?, disponivel = ?, energia_acumulada = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bicicleta.getModelo());
            stmt.setBoolean(2, bicicleta.isDisponivel());
            stmt.setDouble(3, bicicleta.getEnergiaAcumulada());
            stmt.setInt(4, bicicleta.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Bicicleta atualizada com sucesso. ID: " + bicicleta.getId());
                return bicicleta;
            } else {
                logger.warning("Nenhuma linha foi afetada ao tentar atualizar bicicleta com ID: " + bicicleta.getId());
                throw new BicicletaNaoDisponivelException();
            }
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar bicicleta no banco de dados: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteById(int id, Connection connection) throws SQLException, BicicletaNaoDisponivelException {
        String sql = "DELETE FROM BICICLETA WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Nenhuma linha foi afetada ao tentar deletar bicicleta com ID: " + id);
                throw new BicicletaNaoDisponivelException();
            } else {
                logger.info("Bicicleta deletada com sucesso. ID: " + id);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao deletar bicicleta do banco de dados: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Bicicleta findById(int id) throws SQLException, BicicletaNaoDisponivelException {
        String sql = "SELECT * FROM BICICLETA WHERE id = ?";
        try (Connection conn = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Bicicleta bicicleta = new Bicicleta(
                            rs.getInt("id"),
                            rs.getString("modelo"),
                            rs.getBoolean("disponivel")
                    );
                    bicicleta.setEnergiaAcumulada(rs.getDouble("energia_acumulada"));
                    return bicicleta;
                } else {
                    logger.warning("Bicicleta não encontrada com ID: " + id);
                    throw new BicicletaNaoDisponivelException();
                }
            }

        } catch (SQLException e) {
            logger.severe("Erro ao buscar bicicleta pelo ID: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void alterarDisponibilidade(int id, boolean disponivel, Connection connection) throws SQLException, BicicletaNaoDisponivelException {
        String sql = "UPDATE BICICLETA SET disponivel = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, disponivel);
            stmt.setInt(2, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Nenhuma linha foi afetada ao tentar alterar disponibilidade da bicicleta com ID: " + id);
                throw new BicicletaNaoDisponivelException();
            } else {
                logger.info("Disponibilidade da bicicleta alterada com sucesso. ID: " + id);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao alterar disponibilidade da bicicleta: " + e.getMessage());
            throw e;
        }
    }
}

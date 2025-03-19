package game.gameproject.services;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.infoPlayerDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusService {

    public int getPlayerNivel(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT nivel FROM tb_player_status WHERE id_player_status = ?"; // Pode estar errada!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("nivel");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorno padrão se não encontrar o nível
    }

    public int getPlayerPontos(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT pontos FROM tb_player_status WHERE id_player_status = ?"; // Pode estar errada!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("pontos");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorno padrão se não encontrar o nível
    }

    public int getPlayerVida(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT vida FROM tb_player_status WHERE id_player_status = ?"; // Pode estar errada!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("vida");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorno padrão se não encontrar o nível
    }

    public int getPlayerStamina(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT stamina FROM tb_player_status WHERE id_player_status = ?"; // Pode estar errada!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("stamina");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorno padrão se não encontrar o nível
    }

    public int getPlayerForca(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT forca FROM tb_player_status WHERE id_player_status = ?"; // Pode estar errada!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("forca");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorno padrão se não encontrar o nível
    }

    public int getPlayerDinheiro(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT dinheiro FROM tb_player_status WHERE id_player_status = ?"; // Pode estar errada!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("dinheiro");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorno padrão se não encontrar o nível
    }

    public boolean atualizarStatusBanco(int id, int nivel, int pontos, int vida, int stamina, int forca, int dinheiro) {
        // SQL para atualizar os valores
        String query = "UPDATE tb_player_status SET nivel = ?, pontos = ?, vida = ?, stamina = ?, forca = ?, dinheiro = ? WHERE id_player_status = ?";

        try (Connection connection = DatabaseConfig.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Define os parâmetros da query
                statement.setInt(1, nivel);   // Atualiza o campo 'nivel'
                statement.setInt(2, pontos);   // Atualiza o campo 'pontos'
                statement.setInt(3, vida);     // Atualiza o campo 'vida'
                statement.setInt(4, stamina);  // Atualiza o campo 'stamina'
                statement.setInt(5, forca);    // Atualiza o campo 'forca'
                statement.setInt(6, dinheiro); // Atualiza o campo 'dinheiro'
                statement.setInt(7, id);       // Define o ID do jogador para atualização

                // Executa a query
                int rowsAffected = statement.executeUpdate();

                // Se pelo menos uma linha foi afetada, a atualização foi bem-sucedida
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false se ocorrer algum erro ou se não afetar nenhuma linha
    }
}

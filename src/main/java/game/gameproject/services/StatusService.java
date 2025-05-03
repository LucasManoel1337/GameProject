package game.gameproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.gameproject.bdd.DatabaseConfig;

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
    
    public int getPlayerMana(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT mana FROM tb_player_status WHERE id_player_status = ?"; // Pode estar errada!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("mana");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorno padrão se não encontrar o nível
    }
    
    public int getPlayerForcaMana(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT forcaMana FROM tb_player_status WHERE id_player_status = ?"; // Pode estar errada!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("forcaMana");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorno padrão se não encontrar o nível
    }
    
    public int getPlayerXpAtual(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT xpAtual FROM tb_player_status WHERE id_player_status = ?"; // Pode estar errada!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("xpAtual");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorno padrão se não encontrar o nível
    }
    
    public int getPlayerXpMaxima(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT xpMaxima FROM tb_player_status WHERE id_player_status = ?"; // Pode estar errada!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("xpMaxima");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorno padrão se não encontrar o nível
    }
    
    public String getPlayerClasse(int id) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT classe FROM tb_player_status WHERE id_player_status = ?"; // Verifique se essa tabela e coluna estão corretas!
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getString("classe");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Sem classe definida"; // Retorno padrão caso não encontre
    }
    
    public static boolean isOp(int idPlayer) {
        String sql = "SELECT op FROM tb_player_ops WHERE id_player = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idPlayer);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("op");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar se o jogador é OP:");
            e.printStackTrace();
        }

        // Retorna false (não é OP) se não encontrou ou houve erro
        return false;
    }

    public boolean atualizarStatusBanco(int id, int nivel, int pontos, int vida, int stamina, int forca, int mana, int forcaMana, int dinheiro, String classe, int xpAtual, int xpMaxima) {
        // SQL para atualizar os valores
        String query = "UPDATE tb_player_status SET nivel = ?, pontos = ?, vida = ?, stamina = ?, forca = ?, mana = ?, forcaMana = ?, dinheiro = ?, classe = ?, xpAtual = ?, xpMaxima = ? WHERE id_player_status = ?";

        try (Connection connection = DatabaseConfig.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Define os parâmetros da query
                statement.setInt(1, nivel);   // Atualiza o campo 'nivel'
                statement.setInt(2, pontos);   // Atualiza o campo 'pontos'
                statement.setInt(3, vida);     // Atualiza o campo 'vida'
                statement.setInt(4, stamina);  // Atualiza o campo 'stamina'
                statement.setInt(5, forca);    // Atualiza o campo 'forca'
                statement.setInt(6, mana);    // Atualiza o campo 'forca'
                statement.setInt(7, forcaMana);    // Atualiza o campo 'forca'
                statement.setInt(8, dinheiro); // Atualiza o campo 'dinheiro'
                statement.setString(9, classe); // Atualiza o campo 'dinheiro'
                statement.setInt(10, xpAtual); // Atualiza o campo 'dinheiro'
                statement.setInt(11, xpMaxima); // Atualiza o campo 'dinheiro'
                statement.setInt(12, id);       // Define o ID do jogador para atualização

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

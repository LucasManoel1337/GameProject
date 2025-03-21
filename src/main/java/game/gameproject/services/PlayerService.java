package game.gameproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import game.gameproject.bdd.DatabaseConfig;

public class PlayerService {

    public void salvarCoordenadas(int idPlayer, int coordenadaX, int coordenadaY, String sprite) {
        String sql = "INSERT INTO tb_player_coordenadas (id_player, x, y, sprite, online) " +
                     "VALUES (?, ?, ?, ?, true) " +
                     "ON DUPLICATE KEY UPDATE x = VALUES(x), y = VALUES(y), sprite = VALUES(sprite), online = true";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idPlayer);
            stmt.setInt(2, coordenadaX);
            stmt.setInt(3, coordenadaY);
            stmt.setString(4, sprite);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void setOnline(int idPlayer) {
        String sql = "UPDATE tb_player_coordenadas SET online = true WHERE id_player = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idPlayer);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public int setOffline(int idPlayer) {
        String checkOnlineSql = "SELECT online FROM tb_player_coordenadas WHERE id_player = ?";
        String updateOfflineSql = "UPDATE tb_player_coordenadas SET online = false WHERE id_player = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkOnlineSql)) {
            
            checkStmt.setInt(1, idPlayer);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                boolean isOnline = rs.getBoolean("online");
                if (isOnline) {
                    // Só atualiza se estiver online
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateOfflineSql)) {
                        updateStmt.setInt(1, idPlayer);
                        int rowsAffected = updateStmt.executeUpdate();
                        
                        if (rowsAffected > 0) {
                            System.out.println("Jogador " + idPlayer + " agora está offline.");
                            return 1; // Sucesso
                        } else {
                            System.err.println("Falha ao atualizar status do jogador.");
                            return 0; // Falha ao atualizar
                        }
                    }
                } else {
                    System.out.println("Jogador já está offline.");
                    return 1; // Já estava offline, considerado sucesso
                }
            } else {
                System.err.println("Jogador não encontrado no banco de dados.");
                return 0; // Jogador não encontrado
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Erro no banco
        }
    }


    public static int getPlayersOnline() {
        String sql = "SELECT COUNT(*) FROM tb_player_coordenadas WHERE online = TRUE";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1); // Retorna o número de jogadores online
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 em caso de erro
    }
    
}

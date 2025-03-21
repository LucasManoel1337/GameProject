package game.gameproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import game.gameproject.bdd.DatabaseConfig;

public class PlayerService {

    public void salvarCoordenadas(int idPlayer, int coordenadaX, int coordenadaY, int sprite) {
        String sql = "INSERT INTO tb_player_coordenadas (id_player, x, y, sprite, online) " +
                     "VALUES (?, ?, ?, ?, true) " +
                     "ON DUPLICATE KEY UPDATE x = VALUES(x), y = VALUES(y), sprite = VALUES(sprite), online = true";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idPlayer);
            stmt.setInt(2, coordenadaX);
            stmt.setInt(3, coordenadaY);
            stmt.setInt(4, sprite);
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
    
    public void setOffline(int idPlayer) {
        String sql = "UPDATE tb_player_coordenadas SET false = true WHERE id_player = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idPlayer);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

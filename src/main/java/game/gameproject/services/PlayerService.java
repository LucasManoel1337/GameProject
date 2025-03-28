package game.gameproject.services;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.Jogador;

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
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateOfflineSql)) {
                        updateStmt.setInt(1, idPlayer);
                        int rowsAffected = updateStmt.executeUpdate();
                        
                        if (rowsAffected > 0) {
                            System.out.println("Jogador " + idPlayer + " agora está offline.");
                            return 1;
                        } else {
                            System.err.println("Falha ao atualizar status do jogador.");
                            return 0;
                        }
                    }
                } else {
                    System.out.println("Jogador já está offline.");
                    return 1;
                }
            } else {
                System.err.println("Jogador não encontrado no banco de dados.");
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static int getPlayersOnline() {
        String sql = "SELECT COUNT(*) FROM tb_player_coordenadas WHERE online = TRUE";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public List<Jogador> buscarJogadores(int id) {
        List<Jogador> jogadores = new ArrayList<>();
        
        // Obtém o ID do jogador atual (supondo que 'playerInfo' é uma instância de um objeto que contém os dados do jogador atual)
        int idJogadorAtual = id;

        // Modifica a consulta para excluir o jogador atual
        String sql = "SELECT p.id_player, p.x, p.y, p.sprite, l.usuario FROM tb_player_coordenadas p "
                + "JOIN tb_login l ON p.id_player = l.id "
                + "WHERE p.online = 1 AND p.id_player != ? ORDER BY p.id_player";

        try (Connection conn = DatabaseConfig.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Passa o ID do jogador atual como parâmetro para a consulta
            stmt.setInt(1, idJogadorAtual);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idPlayer = rs.getInt("id_player");
                String nomePlayer = rs.getString("usuario");
                int xPlayer = rs.getInt("x");
                int yPlayer = rs.getInt("y");
                String spritePath = rs.getString("sprite");

                Image sprite = carregarSprite(spritePath);

                Jogador jogador = new Jogador(idPlayer, nomePlayer, xPlayer, yPlayer, sprite);
                jogadores.add(jogador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jogadores;
    }

    public Image carregarSprite(String spritePath) {
        try {
            String caminhoCorrigido = spritePath.replace("modelo1d", "modelo1");

            File file = new File(spritePath);
            if (file.exists()) {
                return ImageIO.read(file);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}

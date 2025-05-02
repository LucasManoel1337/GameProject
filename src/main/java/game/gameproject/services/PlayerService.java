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
import game.gameproject.dto.chatDto;

public class PlayerService {
	
	chatDto CD = new chatDto();

	public void salvarCoordenadas(int idPlayer, int coordenadaX, int coordenadaY, String sprite) {
	    String sql = "INSERT INTO tb_player_coordenadas (id_player, x, y, sprite, online, digitando) " +
	                 "VALUES (?, ?, ?, ?, true, ?) " +
	                 "ON DUPLICATE KEY UPDATE x = VALUES(x), y = VALUES(y), sprite = VALUES(sprite), online = true";

	    try (Connection conn = DatabaseConfig.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, idPlayer);
	        stmt.setInt(2, coordenadaX);
	        stmt.setInt(3, coordenadaY);
	        stmt.setString(4, sprite);
	        stmt.setBoolean(5, CD.isChatAtivo()); // Agora corretamente o 5º parâmetro

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
        String sql = "UPDATE tb_player_coordenadas SET online = ? WHERE id_player = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, false);  // Força o valor de 'online' para false (0)
            stmt.setInt(2, idPlayer);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✔️ Jogador " + idPlayer + " foi forçado a ficar offline.");
                return 1;
            } else {
                System.err.println("❌ Nenhuma linha foi atualizada. ID do jogador pode estar incorreto.");
                return 0;
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao forçar jogador offline: " + e.getMessage());
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
        
        int idJogadorAtual = id;

        String sql = "SELECT p.id_player, p.x, p.y, p.sprite, l.usuario, p.digitando FROM tb_player_coordenadas p "
                   + "JOIN tb_login l ON p.id_player = l.id "
                   + "WHERE p.online = 1 AND p.id_player != ? ORDER BY p.id_player";

        try (Connection conn = DatabaseConfig.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idJogadorAtual);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idPlayer = rs.getInt("id_player");
                String nomePlayer = rs.getString("usuario");
                int xPlayer = rs.getInt("x");
                int yPlayer = rs.getInt("y");
                String spritePath = rs.getString("sprite");
                boolean digitandoBoolean = rs.getBoolean("digitando");

                Image sprite = carregarSprite(spritePath);

                Jogador jogador = new Jogador(idPlayer, nomePlayer, xPlayer, yPlayer, sprite, digitandoBoolean);

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
    
    public int buscarCoordenadaX(int idPlayer) {
        String sql = "SELECT x FROM tb_player_coordenadas WHERE id_player = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPlayer);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("x");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // valor padrão caso não encontre
    }

    public int buscarCoordenadaY(int idPlayer) {
        String sql = "SELECT y FROM tb_player_coordenadas WHERE id_player = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPlayer);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("y");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // valor padrão caso não encontre
    }

    public int buscarMapa(int idPlayer) {
        String sql = "SELECT mapa FROM tb_player_coordenadas WHERE id_player = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPlayer);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("mapa");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1; // valor padrão caso não encontre
    }

    public String buscarSprite(int idPlayer) {
        String sprite = null;

        String sqlSprite = "SELECT sprite FROM tb_player_coordenadas WHERE id_player = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlSprite)) {

            stmt.setInt(1, idPlayer);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sprite = rs.getString("sprite");
            }

            // Se sprite estiver vazio ou nulo, buscar a classe do player
            if (sprite == null || sprite.isEmpty()) {
                String sqlClasse = "SELECT classe FROM tb_players WHERE id_player = ?";
                try (PreparedStatement stmtClasse = conn.prepareStatement(sqlClasse)) {
                    stmtClasse.setInt(1, idPlayer);
                    ResultSet rsClasse = stmtClasse.executeQuery();

                    if (rsClasse.next()) {
                        String classe = rsClasse.getString("classe");
                        sprite = "imagens/player/" + classe + "/modeloCosta1.png";
                    } else {
                        sprite = "imagens/player/default/modeloCosta1.png"; // fallback final
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            sprite = "imagens/player/default/modeloCosta1.png"; // fallback em caso de erro
        }

        return sprite;
    }
    
    public boolean inverterStatusDigitando(int idPlayer) throws SQLException {
        String sqlSelect = "SELECT digitando FROM tb_player_coordenadas WHERE id_player = ?";
        String sqlUpdate = "UPDATE tb_player_coordenadas SET digitando = ? WHERE id_player = ?";
        String sqlInsert = "INSERT INTO tb_player_coordenadas (id_player, digitando) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {

            // Configura o parâmetro do PreparedStatement
            stmtSelect.setInt(1, idPlayer);

            try (ResultSet resultSet = stmtSelect.executeQuery()) {
                if (resultSet.next()) {
                    // Valor de digitando existe, inverte
                    boolean digitando = resultSet.getBoolean("digitando");
                    boolean novoValor = !digitando; // Inverte o valor (0 -> 1 ou 1 -> 0)

                    // Atualiza o valor na tabela
                    try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                        stmtUpdate.setBoolean(1, novoValor);
                        stmtUpdate.setInt(2, idPlayer);
                        stmtUpdate.executeUpdate();
                    }

                    return novoValor; // Retorna o novo valor invertido
                } else {
                    // Caso não exista, insere um valor padrão (0 ou 1) para o idPlayer
                    boolean valorInicial = false; // Inserir 0 por padrão
                    try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
                        stmtInsert.setInt(1, idPlayer);
                        stmtInsert.setBoolean(2, valorInicial);
                        stmtInsert.executeUpdate();
                    }
                    return valorInicial; // Retorna o valor inserido (0)
                }
            }
        }
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
        
        public static boolean isJogadorExistente(String nick) {
            String sql = "SELECT usuario FROM tb_login WHERE usuario = ?";

            try (Connection connection = DatabaseConfig.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, nick);

                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next(); // Se encontrou algum registro
                }

            } catch (SQLException e) {
                System.err.println("Erro ao verificar jogador no banco de dados:");
                e.printStackTrace();
            }

            return false;
        }
}

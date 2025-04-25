package game.gameproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.gameproject.bdd.DatabaseConfig;

public class ChatGlobalService {

    public void enviarMensagem(int idPlayer, String nickPlayer, String mensagem) {
        String sql = "INSERT INTO tb_chatGlobal (idPlayer, dataEHora, nickPlayer, mensagem) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idPlayer);
            stmt.setObject(2, LocalDateTime.now()); // Data e hora atual
            stmt.setString(3, nickPlayer);
            stmt.setString(4, mensagem);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao enviar mensagem para o chat global:");
            e.printStackTrace();
        }
    }
    
    public String obterUltimasMensagens() {
        StringBuilder mensagens = new StringBuilder();

        // Subquery: pega as 30 últimas mensagens por ordem de envio (mais recentes),
        // depois ordena ASC para mostrar da mais antiga para a mais recente.
        String query = """
            SELECT * FROM (
                SELECT * FROM tb_chatGlobal ORDER BY id DESC LIMIT 30
            ) AS ultimas
            ORDER BY id ASC
            """;

        try (Connection conn = DatabaseConfig.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String jogador = rs.getString("nickPlayer");
                String mensagem = rs.getString("mensagem");

                mensagens.append(jogador).append(": ").append(mensagem).append("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mensagens.toString();
    }


    }

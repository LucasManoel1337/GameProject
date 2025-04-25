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
        String query = "SELECT * FROM tb_chatGlobal ORDER BY dataEHora DESC LIMIT 30"; // Obtém as 30 últimas mensagens

        List<String> mensagensList = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String jogador = rs.getString("nickPlayer");  // Nome do jogador
                String mensagem = rs.getString("mensagem");   // Mensagem
                String data = rs.getString("dataEHora");      // Data de envio (não utilizado diretamente)

                // Adicionando a mensagem formatada à lista
                mensagensList.add(jogador + ": " + mensagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Agora, invertemos a ordem da lista para exibir as mais recentes por último
        Collections.reverse(mensagensList);

        // Concatenando as mensagens na ordem correta
        for (String msg : mensagensList) {
            mensagens.append(msg).append("\n");
        }

        return mensagens.toString(); // Retorna todas as mensagens concatenadas
    }
    }

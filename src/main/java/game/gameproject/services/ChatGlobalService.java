package game.gameproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
}

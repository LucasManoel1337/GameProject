package game.gameproject.services;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.PlayerCoordenadasDto;
import game.gameproject.cache.JogadoresOnlineCache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerCoordenadasService {

    public void buscarEAtualizarJogadoresOnline(int meuId) {
        List<PlayerCoordenadasDto> jogadoresOnline = new ArrayList<>();
        String sql = "SELECT id_player, x, y, sprite FROM tb_player_coordenadas WHERE online = true AND id_player != ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, meuId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_player");
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                int sprite = rs.getInt("sprite");

                jogadoresOnline.add(new PlayerCoordenadasDto(id, x, y, sprite));
            }

            // Atualiza o cache com os jogadores online
            JogadoresOnlineCache.atualizarLista(jogadoresOnline);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

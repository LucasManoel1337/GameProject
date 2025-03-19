package game.gameproject;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.controller.GameFrame;
import game.gameproject.controller.LauncherFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.AutenticacaoService;
import java.sql.SQLException;

public class GameProject {

    public static void main(String[] args) throws SQLException {
        DatabaseConfig.configureDatabase();
        
        AutenticacaoService autenticacaoService = new AutenticacaoService();
        infoPlayerDto playerInfo = autenticacaoService.autenticarUsuario();

        if (playerInfo != null) {
            // Usuário autenticado, você pode acessar playerInfo.getNickPlayer() e playerInfo.getIdPlayer()
            System.out.println("ID: " +playerInfo.getIdPlayer());
            System.out.println("Nick: " +playerInfo.getNickPlayer());
            
            GameFrame GF = new GameFrame();
        } else {
            // Token inválido ou erro na autenticação
            System.out.println("Falha na autenticação.");
            LauncherFrame LF = new LauncherFrame();
        }
    }
}

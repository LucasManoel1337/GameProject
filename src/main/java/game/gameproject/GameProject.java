package game.gameproject;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.controller.GameFrame;
import game.gameproject.controller.LauncherFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.AutenticacaoService;
import game.gameproject.services.VersionGameService.VersaoService;

import java.sql.SQLException;

public class GameProject {
	
	public static void main(String[] args) throws SQLException {
	    DatabaseConfig.configureDatabase();

	    if (!VersaoService.validarVersaoEManutencao()) {
	        // Versão inválida ou manutenção ativa. Não continua o fluxo.
	        return;
	    }

	    AutenticacaoService autenticacaoService = new AutenticacaoService();
	    infoPlayerDto playerInfo = autenticacaoService.autenticarUsuario();

	    if (playerInfo != null) {
	        if (playerInfo.getClasse() != null) {
	            GameFrame GF = new GameFrame();
	        } else {
	            GameFrame GF = new GameFrame();
	            GF.switchToEscolherClassePanel();
	        }
	    } else {
	        System.out.println("Falha na autenticação.");
	        LauncherFrame LF = new LauncherFrame();
	        LF.setVisible(true);
	    }
	}
}

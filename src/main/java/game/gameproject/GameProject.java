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
	    DatabaseConfig.configureDatabase(); //Conecta com o banco de dados e valida se está tudo ok

	    if (!VersaoService.validarVersaoEManutencao()) { //Verificar a versao do client e do mesmo, caso eles nao sejam as mesmas
	        return; //Caso nao for a mesma irá parar por aqui ppara dentro do metodo valdiarVersaoEManutenção chamar o fluxo de atualização ou manutenção
	    }

	    AutenticacaoService autenticacaoService = new AutenticacaoService(); //Instancia a classe
	    infoPlayerDto playerInfo = autenticacaoService.autenticarUsuario(); //Verifica o token mesmo se não existir

	    if (playerInfo != null) { //caso não estiver vazio
	        if (playerInfo.getClasse() != null) { //Caso estiver nao estiver vazio
	            GameFrame GF = new GameFrame(); //irá direto pro jogo
	        } else { // Se não
	            GameFrame GF = new GameFrame(); //Irá direto pro jogo
	            GF.switchToEscolherClassePanel(); //Porém irá mudar para a tela das classes
	        }
	    } else { // Caso o playerinfo estiver vazio
	        System.out.println("Falha na autenticação.");
	        LauncherFrame LF = new LauncherFrame(); //Irá instanciar o laucher/tela de login
	        LF.setVisible(true); //E deixará ela visivel
	    }
	}
}

package game.gameproject;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.controller.GameFrame;
import game.gameproject.controller.LauncherFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.AutenticacaoService;
import game.gameproject.services.VersionGameService.VersaoService;

import java.net.InetAddress;
import java.sql.SQLException;

public class GameProject {
	
	public static void main(String[] args) throws SQLException {
		if (!isInternetAvailable()) {
			LauncherFrame LF = new LauncherFrame(); //Irá instanciar o laucher/tela de login
	        LF.switchToSemInternetPanel(); // Troca para a tela de sem internet
	        LF.setVisible(true); // deixa visivel
            return; // para o restante do fluxo
        }
		
		try {
	        DatabaseConfig.configureDatabase();
	    } catch (Exception e) {
	        System.err.println("⚠️ Erro de banco de dados:");
	        e.printStackTrace();

	        LauncherFrame LF = new LauncherFrame(); //Irá instanciar o laucher/tela de login
	        LF.switchToErroBancoPanel(); // Troca para a tela de erro banco
	        LF.setVisible(true); // deixa visivel
	        return; // para o restante do fluxo
	    }

	    if (!VersaoService.validarVersaoEManutencao()) { //Verificar a versao do client e do mesmo, caso eles nao sejam as mesmas
	        return; //Caso nao for a mesma irá parar por aqui ppara dentro do metodo valdiarVersaoEManutenção chamar o fluxo de atualização ou manutenção
	    }

	    AutenticacaoService autenticacaoService = new AutenticacaoService(); //Instancia a classe
	    infoPlayerDto playerInfo = autenticacaoService.autenticarUsuario(); //Verifica o token mesmo se não existir

	    if (playerInfo != null) { //caso não estiver vazio
	        if (playerInfo.getClasse() != null) { //Caso estiver nao estiver vazio
	            GameFrame GF = new GameFrame(playerInfo); //irá direto pro jogo
	        } else { // Se não
	            GameFrame GF = new GameFrame(playerInfo); //Irá direto pro jogo
	            GF.switchToEscolherClassePanel(); //Porém irá mudar para a tela das classes
	        }
	    } else { // Caso o playerinfo estiver vazio
	        System.out.println("Falha na autenticação.");
	        LauncherFrame LF = new LauncherFrame(); //Irá instanciar o laucher/tela de login
	        LF.setVisible(true); //E deixará ela visivel
	    }
	}
	
	private static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(2000); // 2 segundos de timeout
        } catch (Exception e) {
            return false;
        }
    }
}

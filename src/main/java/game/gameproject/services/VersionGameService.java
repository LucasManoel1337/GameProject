package game.gameproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.controller.LauncherFrame;
import game.gameproject.dto.VersoesDto;

public class VersionGameService {

    public class VersaoService {

        // Método principal
    	public static boolean validarVersaoEManutencao() {

    	    String sql = "SELECT manutencao, versao FROM tb_versao LIMIT 1";
    	    VersoesDto dto = new VersoesDto();

    	    try (Connection conn = DatabaseConfig.getConnection();
    	         PreparedStatement ps = conn.prepareStatement(sql);
    	         ResultSet rs = ps.executeQuery()) {

    	        if (!rs.next()) {
    	            System.err.println("Registro de versão não encontrado.");
    	            return false;
    	        }

    	        boolean emManutencao = rs.getBoolean("manutencao");
    	        String versaoBanco = rs.getString("versao");
    	        String versaoClient = dto.getVersaoGame();

    	        if (emManutencao) {
    	        	LauncherFrame LF = new LauncherFrame(); //Instancia o laucher/e chama a tela de login
    	            LF.switchToManutencaoPanel(); //Troca para a tela de manutenção
    	            LF.setVisible(true); //E deixa visivel
    	            return false;
    	        }

    	        if (!versaoBanco.equals(versaoClient)) {
    	            LauncherFrame LF = new LauncherFrame(); //Instancia o laucher/e chama a tela de login
    	            LF.switchToAtualizarPanel(); //Troca para a tela de atualizar o jogo
    	            LF.setVisible(true); //E deixa visivel
    	            return false;
    	        }

    	        System.out.println("✔️  Versão compatível.");
    	        return true;

    	    } catch (SQLException e) {
    	        System.err.println("Erro na verificação: " + e.getMessage());
    	        return false;
    	    }
    	}
}
}
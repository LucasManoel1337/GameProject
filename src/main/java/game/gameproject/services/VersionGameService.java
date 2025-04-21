package game.gameproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
    	            JOptionPane.showMessageDialog(
    	                null,
    	                "O jogo está em manutenção.",
    	                "Manutenção",
    	                JOptionPane.WARNING_MESSAGE
    	            );
    	            return false;
    	        }

    	        if (!versaoBanco.equals(versaoClient)) {
    	            LauncherFrame LF = new LauncherFrame();
    	            LF.switchToAtualizarPanel();
    	            LF.setVisible(true);
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
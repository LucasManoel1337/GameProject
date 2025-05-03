package game.gameproject.services.chatCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.StatusService;

public class OpCommands {

	private infoPlayerDto playerInfo;
	 public StatusService playerService = new StatusService();

	 public OpCommands(infoPlayerDto playerInfo) {
		 this.playerInfo = playerInfo;
	 }
	 
	 public void buscarComando(String comando) {
	        Pattern pattern = Pattern.compile("//op (\\w+) (true|false)");
	        Matcher matcher = pattern.matcher(comando.toLowerCase());

	        if (matcher.matches()) {
	            String nick = matcher.group(1);
	            boolean opAtivo = Boolean.parseBoolean(matcher.group(2));

	            definirOperador(nick, opAtivo);
	            System.out.println("Agora o OP está "+opAtivo+" para o jogador "+nick);  
	            
	            if(nick.equals(playerInfo.getNickPlayer())) {
	            	playerInfo.setOp(false);
	            }
	        } else {
	            System.out.println("Comando de OP inválido. Use: //op <nick> <true|false>");
	        }
	    }
	 
	 public void definirOperador(String nick, boolean op) {
	        String buscarIdSql = "SELECT id FROM tb_login WHERE usuario = ?";
	        String verificarOpSql = "SELECT * FROM tb_player_ops WHERE id_player = ?";
	        String atualizarOpSql = "UPDATE tb_player_ops SET op = ? WHERE id_player = ?";
	        String inserirOpSql = "INSERT INTO tb_player_ops (id_player, op) VALUES (?, ?)";

	        try (Connection conn = DatabaseConfig.getConnection();
	             PreparedStatement buscarIdStmt = conn.prepareStatement(buscarIdSql)) {

	            // 1. Buscar o ID da conta pelo nick
	            buscarIdStmt.setString(1, nick);
	            ResultSet rs = buscarIdStmt.executeQuery();

	            if (!rs.next()) {
	                System.out.println("Nick '" + nick + "' não encontrado.");
	                return;
	            }

	            int idConta = rs.getInt("id");

	            // 2. Verificar se o ID já está em tb_player_ops
	            try (PreparedStatement verificarStmt = conn.prepareStatement(verificarOpSql)) {
	                verificarStmt.setInt(1, idConta);
	                ResultSet rsVerificacao = verificarStmt.executeQuery();

	                if (rsVerificacao.next()) {
	                    // 3. Atualizar se já existir
	                    try (PreparedStatement atualizarStmt = conn.prepareStatement(atualizarOpSql)) {
	                        atualizarStmt.setBoolean(1, op);
	                        atualizarStmt.setInt(2, idConta);
	                        atualizarStmt.executeUpdate();
	                        System.out.println("Permissão de operador atualizada para o ID: " + idConta);
	                    }
	                } else {
	                    // 4. Inserir se não existir
	                    try (PreparedStatement inserirStmt = conn.prepareStatement(inserirOpSql)) {
	                        inserirStmt.setInt(1, idConta);
	                        inserirStmt.setBoolean(2, op);
	                        inserirStmt.executeUpdate();
	                        System.out.println("Permissão de operador adicionada para o ID: " + idConta);
	                    }
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Erro ao definir operador: " + e.getMessage());
	        }
	    }
}

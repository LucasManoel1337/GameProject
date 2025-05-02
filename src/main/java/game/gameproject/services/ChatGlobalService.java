package game.gameproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.support.CommandsChatSupport;

public class ChatGlobalService {
	
	PlayerService PS = new PlayerService();
	private infoPlayerDto playerInfo;

	// Modificando o construtor para inicializar CCS após a inicialização de playerInfo
	public ChatGlobalService(infoPlayerDto playerInfo) {
		this.playerInfo = playerInfo;
	}

	public void enviarMensagem(int idPlayer, String nickPlayer, String mensagem, infoPlayerDto playerInfo) {
	    // Verifica se é um comando
	    if (mensagem.startsWith("//")) {
	        if (!PS.isOp(idPlayer)) {
	            System.out.println("Jogador " + nickPlayer + " tentou usar o comando: '" + mensagem + "' sem permissão.");
	            return;
	        }
	        CommandsChatSupport CCS = new CommandsChatSupport(playerInfo);
	        
	        System.out.println("Jogador " + nickPlayer + " com permissão de comandos, repassando comando para o CommandExecutor.");
	        CCS.buscarComando(mensagem, playerInfo);
	        return;
	    }

	    // Mensagem normal (não é comando)
	    String sql = "INSERT INTO tb_chatGlobal (idPlayer, dataEHora, nickPlayer, mensagem) VALUES (?, ?, ?, ?)";

	    try (Connection connection = DatabaseConfig.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql)) {

	        stmt.setInt(1, idPlayer);
	        stmt.setObject(2, LocalDateTime.now());
	        stmt.setString(3, nickPlayer);
	        stmt.setString(4, mensagem);

	        stmt.executeUpdate();
	        System.out.println("Mensagem enviada para o chat global: " + mensagem);

	    } catch (SQLException e) {
	        System.err.println("Erro ao enviar mensagem para o chat global:");
	        e.printStackTrace();
	    }
	}

    
    public String obterUltimasMensagens() {
        StringBuilder mensagens = new StringBuilder();

        // Subquery: pega as 30 últimas mensagens por ordem de envio (mais recentes),
        // depois ordena ASC para mostrar da mais antiga para a mais recente.
        String query = """
            SELECT * FROM (
                SELECT * FROM tb_chatGlobal ORDER BY id DESC LIMIT 30
            ) AS ultimas
            ORDER BY id ASC
            """; 

        try (Connection conn = DatabaseConfig.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String jogador = rs.getString("nickPlayer");
                String mensagem = rs.getString("mensagem");

                mensagens.append("[G] ").append(jogador).append(": ").append(mensagem).append("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mensagens.toString();
    }
}

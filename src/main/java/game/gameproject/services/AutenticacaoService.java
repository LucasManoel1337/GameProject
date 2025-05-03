package game.gameproject.services;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.infoPlayerDto;
import java.io.BufferedReader;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacaoService {

    private final StatusService playerService = new StatusService();

    // Método para autenticar o usuário com o token do config.json
    public infoPlayerDto autenticarUsuario() {
        // Verifica se o arquivo config.json existe
        File configFile = new File("config.json");
        if (!configFile.exists()) {
            System.out.println("Arquivo config.json não encontrado!");
            return null; // Retorna null, indicando que a autenticação falhou
        }

        // Lê o token do arquivo config.json
        String token = lerTokenDoArquivo(configFile);
        if (token == null) {
            System.out.println("Token não encontrado no config.json!");
            return null; // Retorna null, indicando que a autenticação falhou
        }

        // Verifica se o token é válido no banco de dados e retorna o infoPlayerDto
        return validarTokenNoBanco(token);
    }

    // Lê o token do arquivo config.json
    private String lerTokenDoArquivo(File configFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // Cria o JSONObject a partir do conteúdo lido
            JSONObject json = new JSONObject(stringBuilder.toString());
            return json.optString("token", null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Verifica se o token é válido no banco de dados e retorna o infoPlayerDto
    private infoPlayerDto validarTokenNoBanco(String token) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT id, usuario FROM tb_login WHERE token = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, token);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // Cria o objeto infoPlayerDto
                    infoPlayerDto IPDto = new infoPlayerDto("playerNickname");
                    
                    // Preenche as propriedades antes de definir o isOp
                    IPDto.setIdPlayer(resultSet.getInt("id"));
                    IPDto.setNickPlayer(resultSet.getString("usuario"));
                    
                    // Preenchendo todas as propriedades
                    IPDto.setNivel(playerService.getPlayerNivel(resultSet.getInt("id")));
                    IPDto.setPontos(playerService.getPlayerPontos(resultSet.getInt("id")));
                    IPDto.setVidaMaxima(playerService.getPlayerVida(resultSet.getInt("id")));
                    IPDto.setStaminaMaxima(playerService.getPlayerStamina(resultSet.getInt("id")));
                    IPDto.setForca(playerService.getPlayerVida(resultSet.getInt("id")));
                    IPDto.setManaMaxima(playerService.getPlayerMana(resultSet.getInt("id")));
                    IPDto.setForcaMana(playerService.getPlayerForcaMana(resultSet.getInt("id")));
                    IPDto.setDinheiro(playerService.getPlayerDinheiro(resultSet.getInt("id")));
                    IPDto.setClasse(playerService.getPlayerClasse(resultSet.getInt("id")));
                    
                    IPDto.setVidaAtual(playerService.getPlayerVida(resultSet.getInt("id")));
                    IPDto.setManaAtual(playerService.getPlayerMana(resultSet.getInt("id")));
                    IPDto.setStaminaAtual(playerService.getPlayerStamina(resultSet.getInt("id")));
                    
                    IPDto.setXpAtual(playerService.getPlayerXpAtual(resultSet.getInt("id")));
                    IPDto.setXpMaxima(playerService.getPlayerXpMaxima(resultSet.getInt("id")));
                    
                    // Agora, podemos verificar se o jogador é "Op" (Administrador)
                    IPDto.setOp(playerService.isOp(resultSet.getInt("id")));  // Define o status de "Op" por último

                    // Retorna o infoPlayerDto preenchido
                    return IPDto;
                } else {
                    System.out.println("Token inválido.");
                    return null; // Retorna null se o token não for encontrado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Retorna null em caso de erro
        }
    }
}

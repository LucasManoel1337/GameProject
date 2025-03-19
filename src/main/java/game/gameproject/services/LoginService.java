package game.gameproject.services;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.infoPlayerDto;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    // Método para autenticar o usuário
    public int login(String usuario, String senha) {
        // Tenta validar usuário e senha e obter o ID do usuário e o token
        UserCredentials userCredentials = validarUsuarioESenha(usuario, senha);

        if (userCredentials != null) {
            // Preenche o infoPlayerDto com o ID do usuário
            infoPlayerDto IPDto = new infoPlayerDto("playerNickname");
            IPDto.setIdPlayer(userCredentials.getId());  // Armazena o ID do jogador no DTO
            IPDto.setNickPlayer(usuario);  // Armazena o nome do usuário no DTO

            // Cria o config.json com o token
            criarConfigJson(userCredentials.getToken());
            return 0; // Login bem-sucedido
        } else {
            return 1; // Usuário ou senha incorretos
        }
    }

    // Método para validar usuário e senha no banco de dados, e retornar as credenciais
    private UserCredentials validarUsuarioESenha(String usuario, String senha) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT id, usuario, senha, token FROM tb_login WHERE usuario = ? AND senha = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, usuario);
                statement.setString(2, senha);
                ResultSet resultSet = statement.executeQuery();

                // Se encontrar o usuário e a senha, retorna as credenciais
                if (resultSet.next()) {
                    int idPlayer = resultSet.getInt("id");
                    String token = resultSet.getString("token");
                    
                    return new UserCredentials(idPlayer, token); // Retorna ID e token
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se não encontrar o usuário ou senha
    }

    // Método para criar o arquivo config.json com o token
    private void criarConfigJson(String token) {
        JSONObject json = new JSONObject();
        json.put("token", token);

        try (FileWriter file = new FileWriter("config.json")) {
            file.write(json.toString(4));  // Escreve o JSON no arquivo com indentação
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Classe interna para armazenar as credenciais do usuário
    private static class UserCredentials {
        private int id;
        private String token;

        public UserCredentials(int id, String token) {
            this.id = id;
            this.token = token;
        }

        public int getId() {
            return id;
        }

        public String getToken() {
            return token;
        }
    }
}

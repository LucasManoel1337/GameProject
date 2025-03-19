package game.gameproject.services;

import game.gameproject.bdd.DatabaseConfig;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RegistrarService {

    // Método para registrar o usuário
    public int registrarUsuario(String usuario, String senha) {
        // Valida se o usuário já existe
        if (usuarioExistente(usuario)) {
            System.out.println("Usuário já existe!");
            return 0;  // Retorna 0 se o usuário já existe
        }

        // Gera um token de 255 caracteres
        String token = gerarToken();

        try {
            // Salva o usuário, senha e token no banco de dados
            if (salvarUsuarioNoBanco(usuario, senha, token)) {
                // Cria o arquivo config.json com o token
                criarConfigJson(token);
                return 1;  // Retorna 1 se o registro for bem-sucedido
            } else {
                return 2;  // Retorna 2 se ocorreu um erro ao salvar
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2;  // Retorna 2 em caso de erro inesperado
        }
    }

    // Verifica se o usuário já existe no banco de dados
    private boolean usuarioExistente(String usuario) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT COUNT(*) FROM tb_login WHERE usuario = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, usuario);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Gera um token aleatório de 255 caracteres
    private String gerarToken() {
        return UUID.randomUUID().toString().replace("-", "") + UUID.randomUUID().toString().replace("-", "");
    }

    // Salva o usuário, senha e token no banco de dados
    private boolean salvarUsuarioNoBanco(String usuario, String senha, String token) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "INSERT INTO tb_login (usuario, senha, token) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, usuario);
                statement.setString(2, senha); // A senha já está hashada
                statement.setString(3, token);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cria o arquivo config.json com o token
    private void criarConfigJson(String token) {
        JSONObject json = new JSONObject();
        json.put("token", token);

        File file = new File("config.json");

        // Verifica se o arquivo já existe
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(json.toString());
                System.out.println("config.json criado com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("config.json já existe.");
        }
    }
}

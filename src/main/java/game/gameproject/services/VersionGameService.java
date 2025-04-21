package game.gameproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.VersoesDto;

public class VersionGameService {

    public class VersaoService {

        // Método principal
        public static void validarVersaoEManutencao() {
            // Tenta popular se necessário
            verificarEInserirVersaoSeNecessario();

            String sql = "SELECT manutencao, versao FROM tb_versao LIMIT 1";
            VersoesDto dto = new VersoesDto();

            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    System.err.println("Registro de versão não encontrado na tabela tb_versao.");
                    System.exit(1);
                }

                boolean emManutencao = rs.getBoolean("manutencao");
                String versaoBanco = rs.getString("versao");
                String versaoClient = dto.getVersaoGame();

                if (emManutencao) {
                    JOptionPane.showMessageDialog(
                        null,
                        "O jogo está em manutenção no momento. Fique atento aos canais de comunicação do jogo.",
                        "Manutenção em andamento",
                        JOptionPane.WARNING_MESSAGE
                    );
                    System.exit(1);
                }

                if (!versaoBanco.equals(versaoClient)) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Versão do jogo desatualizada. Favor atualizar o jogo para continuar.",
                        "Atualização Necessária",
                        JOptionPane.WARNING_MESSAGE
                    );
                    System.exit(1);
                }

                System.out.println("✔️  Versão compatível. Iniciando o jogo...");

            } catch (SQLException e) {
                System.err.println("Erro ao verificar versão/manutenção: " + e.getMessage());
                System.exit(1);
            }
        }

        // Novo método para popular a tabela caso esteja vazia
        private static void verificarEInserirVersaoSeNecessario() {
            String sqlVerifica = "SELECT COUNT(*) FROM tb_versao";
            String sqlInsert = "INSERT INTO tb_versao (versao, manutencao) VALUES (?, ?)";

            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement psVerifica = conn.prepareStatement(sqlVerifica);
                 ResultSet rs = psVerifica.executeQuery()) {

                if (rs.next() && rs.getInt(1) == 0) {
                    try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
                        psInsert.setString(1, new VersoesDto().getVersaoGame());
                        psInsert.setBoolean(2, false); // Manutenção desativada por padrão
                        psInsert.executeUpdate();
                        System.out.println("✔️  Versão inicial inserida com sucesso.");
                    }
                }

            } catch (SQLException e) {
                System.err.println("Erro ao tentar inserir versão inicial: " + e.getMessage());
                System.exit(1);
            }
        }
    }
}

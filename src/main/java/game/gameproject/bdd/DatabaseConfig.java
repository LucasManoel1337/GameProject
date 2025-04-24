package game.gameproject.bdd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.core.MySQLDatabase;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import liquibase.database.jvm.JdbcConnection;

public class DatabaseConfig {

    private static HikariDataSource dataSource;

    // Configura o HikariCP e o Liquibase
    public static void configureDatabase() {
        // Configuração do HikariCP
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://u941_PYh6qcwt7j:42B8Fy6U2%40z.6N%2BI2VrJ%40E2z@181.215.45.73:3306/s941_DDG_Dev");
        config.setUsername("u941_PYh6qcwt7j");
        config.setPassword("42B8Fy6U2@z.6N+I2VrJ@E2z");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        // Outras configurações do HikariCP, se necessário
        dataSource = new HikariDataSource(config);
        
        // Usar a conexão do HikariCP para inicializar o Liquibase
        try (Connection connection = dataSource.getConnection()) {
            // Cria a conexão JDBC necessária para o Liquibase
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            
            // Configura o banco de dados para o Liquibase
            Database database = new MySQLDatabase();
            database.setConnection(jdbcConnection); // Passando a conexão correta

            // Inicializa o Liquibase com o changelog
            Liquibase liquibase = new Liquibase(
                    "db/changelog/db.changelog-master.xml", // Caminho para o arquivo de changelog
                    new ClassLoaderResourceAccessor(),
                    database
            );

            // Executa as migrações
            liquibase.update("");
            System.out.println("Migrações do Liquibase aplicadas com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao aplicar as migrações do Liquibase.");
        }
    }

    // Método para obter a conexão do HikariCP
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Método para fechar o pool de conexões quando o aplicativo for encerrado
    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
    
    public static long getDatabasePing() {
        String sql = "SELECT 1"; // Consulta leve para testar a conexão

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            long startTime = System.nanoTime(); // Início do tempo
            stmt.executeQuery(); // Executa a consulta
            long endTime = System.nanoTime(); // Fim do tempo

            return (endTime - startTime) / 1_000_000; // Converte nanossegundos para milissegundos (ms)

        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Retorna -1 em caso de erro
        }
    }
}

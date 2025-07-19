import java.sql.Connection;
import java.sql.SQLException;
import util.ConexaoPostgres;

public class DemoClinica {
    public static void main(String[] args) {
        // Testar conexão básica
        try (Connection conexao = ConexaoPostgres.getConexao()) {
            System.out.println("Conexão com o banco estabelecida!");
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return; // Sair se não conseguir conectar
        }
    }
}

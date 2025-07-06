import java.sql.Connection;
import java.sql.SQLException;

import util.ConexaoPostgres;

public class DemoClinica {
    public static void main(String[] args) {
        try (Connection conexao = ConexaoPostgres.getConexao()) {
            System.out.println("Conexão com o banco realizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco: " + e.getMessage());
        }
    }
}

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgres {
    public static void main(String[] args) {
        // URL de conexão padrão PostgreSQL
        String url = "jdbc:postgresql://localhost:5432/seubanco";
        String usuario = "seu_usuario";
        String senha = "sua_senha";

        try {
            // Tenta conectar
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão com o PostgreSQL realizada com sucesso!");
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}

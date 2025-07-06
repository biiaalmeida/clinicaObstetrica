package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgres {

    private static final String URL = "jdbc:postgresql://localhost:5432/clinica_obstetrica"; // ajuste aqui
    private static final String USUARIO = "postgres";
    private static final String SENHA = "240604";

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL não encontrado!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}

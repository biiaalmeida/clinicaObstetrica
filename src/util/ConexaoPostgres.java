package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgres {

    private static final String URL = "jdbc:postgresql://localhost:5432/clinica_obstetrica";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "240604";

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado! Verifique se o driver está no classpath.", e);
        }
    }
}
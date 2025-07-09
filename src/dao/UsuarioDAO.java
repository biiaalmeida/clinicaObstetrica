package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.ConexaoPostgres;
import model.Usuario;

public class UsuarioDAO {
    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO Usuario (email, nomeUsuario, senha) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoPostgres.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
            return false;
        }
    }
}

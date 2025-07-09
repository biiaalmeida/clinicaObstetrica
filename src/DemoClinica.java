import java.sql.Connection;
import java.sql.SQLException;

import util.ConexaoPostgres;
import model.Usuario;
import dao.UsuarioDAO;

public class DemoClinica {
    public static void main(String[] args) {
        try (Connection conexao = ConexaoPostgres.getConexao()) {
            System.out.println("Conexão com o banco realizada com sucesso!");

            // Criando um novo usuário
            Usuario novoUsuario = new Usuario("ana@email.com", "AnaBeatriz", "senha123");

            // Chamando o DAO para inserir
            UsuarioDAO dao = new UsuarioDAO();
            boolean sucesso = dao.inserir(novoUsuario);

            if (sucesso) {
                System.out.println("Usuário inserido com sucesso!");
            } else {
                System.out.println("Falha ao inserir usuário.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco: " + e.getMessage());
        }
    }
}

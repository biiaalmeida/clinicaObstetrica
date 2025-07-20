package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.PacienteModel;
import util.ConexaoPostgres;

public class PacienteDAO {
     
    public boolean cadastrarPaciente(PacienteModel paciente) {
        String sqlUsuario = "INSERT INTO usuario (nome_usuario, email, senha) VALUES (?, ?, ?)";
        String sqlPaciente = "INSERT INTO paciente (email, cpf, idade, telefone_contato, endereco, tipo_plano_saude, tipo_sanguineo, alergias, num_gestacoes_anteriores, vacinas, peso, condicoes_pre_ex) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoPostgres.getConexao()) {
            
            connection.setAutoCommit(false);
            
            try {
                try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                    stmtUsuario.setString(1, paciente.getNomeUsuario());
                    stmtUsuario.setString(2, paciente.getEmail());
                    stmtUsuario.setString(3, paciente.getSenha());
                    stmtUsuario.executeUpdate();
                }
                
                try (PreparedStatement stmtPaciente = connection.prepareStatement(sqlPaciente)) {
                    stmtPaciente.setString(1, paciente.getEmail());
                    stmtPaciente.setString(2, paciente.getCpf());
                    stmtPaciente.setInt(3, paciente.getIdade());
                    stmtPaciente.setString(4, paciente.getTelefoneContato());
                    stmtPaciente.setString(5, paciente.getEndereco());
                    stmtPaciente.setString(6, paciente.getTipoPlanoSaude());
                    stmtPaciente.setString(7, paciente.getTipoSanguineo());
                    stmtPaciente.setString(8, paciente.getAlergias());
                    stmtPaciente.setInt(9, paciente.getNumGestacoesAnteriores());
                    stmtPaciente.setString(10, paciente.getVacinas());
                    stmtPaciente.setFloat(11, paciente.getPeso());
                    stmtPaciente.setString(12, paciente.getCondicoesPreEx());
                    stmtPaciente.executeUpdate();
                }
                
                connection.commit();
                System.out.println("Paciente cadastrado com sucesso!");
                return true;
                
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
            return false;
        }
    }   

    public boolean editarPaciente(PacienteModel paciente) {
        String sqlUsuario = "UPDATE usuario SET nome_usuario = ?, senha = ? WHERE email = ?";
        String sqlPaciente = "UPDATE paciente SET idade = ?, telefone_contato = ?, endereco = ?, tipo_plano_saude = ?, tipo_sanguineo = ?, alergias = ?, num_gestacoes_anteriores = ?, vacinas = ?, peso = ?, condicoes_pre_ex = ? WHERE cpf = ?";

        try (Connection connection = ConexaoPostgres.getConexao()) {
            
            connection.setAutoCommit(false);
            
            try {
                try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                    stmtUsuario.setString(1, paciente.getNomeUsuario());
                    stmtUsuario.setString(2, paciente.getSenha());
                    stmtUsuario.setString(3, paciente.getEmail());
                    stmtUsuario.executeUpdate();
                }
                
                try (PreparedStatement stmtPaciente = connection.prepareStatement(sqlPaciente)) {
                    stmtPaciente.setInt(1, paciente.getIdade());
                    stmtPaciente.setString(2, paciente.getTelefoneContato());
                    stmtPaciente.setString(3, paciente.getEndereco());
                    stmtPaciente.setString(4, paciente.getTipoPlanoSaude());
                    stmtPaciente.setString(5, paciente.getTipoSanguineo());
                    stmtPaciente.setString(6, paciente.getAlergias());
                    stmtPaciente.setInt(7, paciente.getNumGestacoesAnteriores());
                    stmtPaciente.setString(8, paciente.getVacinas());
                    stmtPaciente.setFloat(9, paciente.getPeso());
                    stmtPaciente.setString(10, paciente.getCondicoesPreEx());
                    stmtPaciente.setString(11, paciente.getCpf());
                    stmtPaciente.executeUpdate();
                }
                
                connection.commit();
                System.out.println("Paciente editado com sucesso!");
                return true;
                
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao editar paciente: " + e.getMessage());
            return false;
        }
    }

    public static PacienteModel buscarPaciente(String cpf) {
        String sql = "SELECT p.cpf, p.idade, p.telefone_contato, p.endereco, p.tipo_plano_saude, p.tipo_sanguineo, p.alergias, p.num_gestacoes_anteriores, p.vacinas, p.peso, p.condicoes_pre_ex, u.email, u.senha, u.nome_usuario " +
                    "FROM paciente p JOIN usuario u ON p.email = u.email WHERE p.cpf = ?";
        PacienteModel paciente = null;
        
        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement buscarPaciente = connection.prepareStatement(sql)) {

            buscarPaciente.setString(1, cpf);
            try (ResultSet resultSet = buscarPaciente.executeQuery()) {
                if (resultSet.next()) {
                    paciente = new PacienteModel();
                    paciente.setNomeUsuario(resultSet.getString("nome_usuario"));
                    paciente.setEmail(resultSet.getString("email"));
                    paciente.setSenha(resultSet.getString("senha"));
                    paciente.setCpf(resultSet.getString("cpf"));
                    paciente.setIdade(resultSet.getInt("idade"));
                    paciente.setTelefoneContato(resultSet.getString("telefone_contato"));
                    paciente.setEndereco(resultSet.getString("endereco"));
                    paciente.setTipoPlanoSaude(resultSet.getString("tipo_plano_saude"));
                    paciente.setTipoSanguineo(resultSet.getString("tipo_sanguineo"));
                    paciente.setAlergias(resultSet.getString("alergias"));
                    paciente.setNumGestacoesAnteriores(resultSet.getInt("num_gestacoes_anteriores"));
                    paciente.setVacinas(resultSet.getString("vacinas"));
                    paciente.setPeso(resultSet.getFloat("peso"));
                    paciente.setCondicoesPreEx(resultSet.getString("condicoes_pre_ex"));
                } else {
                    System.out.println("Paciente não encontrado com CPF: " + cpf);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }

        return paciente;
    }

    public static PacienteModel buscarPorEmail(String email) {
        String sql = "SELECT p.cpf, p.idade, p.telefone_contato, p.endereco, p.tipo_plano_saude, p.tipo_sanguineo, p.alergias, p.num_gestacoes_anteriores, p.vacinas, p.peso, p.condicoes_pre_ex, u.email, u.senha, u.nome_usuario " +
                    "FROM paciente p JOIN usuario u ON p.email = u.email WHERE u.email = ?";
        PacienteModel paciente = null;

        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement buscarPaciente = connection.prepareStatement(sql)) {

            buscarPaciente.setString(1, email);
            try (ResultSet resultSet = buscarPaciente.executeQuery()) {
                if (resultSet.next()) {
                    paciente = new PacienteModel();
                    paciente.setNomeUsuario(resultSet.getString("nome_usuario"));
                    paciente.setEmail(resultSet.getString("email"));
                    paciente.setSenha(resultSet.getString("senha"));
                    paciente.setCpf(resultSet.getString("cpf"));
                    paciente.setIdade(resultSet.getInt("idade"));
                    paciente.setTelefoneContato(resultSet.getString("telefone_contato"));
                    paciente.setEndereco(resultSet.getString("endereco"));
                    paciente.setTipoPlanoSaude(resultSet.getString("tipo_plano_saude"));
                    paciente.setTipoSanguineo(resultSet.getString("tipo_sanguineo"));
                    paciente.setAlergias(resultSet.getString("alergias"));
                    paciente.setNumGestacoesAnteriores(resultSet.getInt("num_gestacoes_anteriores"));
                    paciente.setVacinas(resultSet.getString("vacinas"));
                    paciente.setPeso(resultSet.getFloat("peso"));
                    paciente.setCondicoesPreEx(resultSet.getString("condicoes_pre_ex"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente por email: " + e.getMessage());
        }

        return paciente;
    }
}
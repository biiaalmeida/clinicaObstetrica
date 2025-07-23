package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.MedicoModel;
import util.ConexaoPostgres; 

public class MedicoDAO {
    
    public boolean cadastrarMedico(MedicoModel medico) {
        String sqlUsuario = "INSERT INTO Usuario (nomeUsuario, email, senha) VALUES (?, ?, ?)";
        String sqlMedico = "INSERT INTO Medico (crm, especialidade, email) VALUES (?, ?, ?)";
    
        try (Connection connection = ConexaoPostgres.getConexao()) {
            
            connection.setAutoCommit(false);
            
            try {
                try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                    stmtUsuario.setString(1, medico.getNomeUsuario());
                    stmtUsuario.setString(2, medico.getEmail());
                    stmtUsuario.setString(3, medico.getSenha());
                    stmtUsuario.executeUpdate();
                }
                
                try (PreparedStatement stmtMedico = connection.prepareStatement(sqlMedico)) {
                    stmtMedico.setString(1, medico.getCrm());
                    stmtMedico.setString(2, medico.getEspecialidade());
                    stmtMedico.setString(3, medico.getEmail());
                    stmtMedico.executeUpdate();
                }
                
                connection.commit();
                System.out.println("Médico cadastrado com sucesso!");
                return true;
                
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar médico: " + e.getMessage());
            return false;
        }
    }

    public boolean editarMedico(MedicoModel medico) {
        String sqlUsuario = "UPDATE Usuario SET nomeUsuario = ?, senha = ? WHERE email = ?";
        String sqlMedico = "UPDATE Medico SET especialidade = ? WHERE crm = ?";
   
        try (Connection connection = ConexaoPostgres.getConexao()) {
            
            connection.setAutoCommit(false);
            
            try {
                try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                    stmtUsuario.setString(1, medico.getNomeUsuario());
                    stmtUsuario.setString(2, medico.getSenha());
                    stmtUsuario.setString(3, medico.getEmail());
                    stmtUsuario.executeUpdate();
                }
                
                try (PreparedStatement stmtMedico = connection.prepareStatement(sqlMedico)) {
                    stmtMedico.setString(1, medico.getEspecialidade());
                    stmtMedico.setString(2, medico.getCrm());
                    stmtMedico.executeUpdate();
                }
        
                connection.commit();
                System.out.println("Medico editado com sucesso!");
                return true;
                
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao editar médico: " + e.getMessage());
            return false;
        }
    }

    public static MedicoModel buscarMedico(String crm) {
        String sql = "SELECT m.crm, m.especialidade, u.email, u.senha, u.nomeUsuario " +
                "FROM Medico m JOIN Usuario u ON m.email = u.email WHERE m.crm = ?";
   
        MedicoModel medico = null;
    
        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement buscarMedico = connection.prepareStatement(sql)) {
        
            buscarMedico.setString(1, crm);
            try (ResultSet resultSet = buscarMedico.executeQuery()) { 
                if (resultSet.next()) {
                    medico = new MedicoModel();
                    medico.setCrm(resultSet.getString("crm"));
                    medico.setEspecialidade(resultSet.getString("especialidade"));
                    medico.setEmail(resultSet.getString("email"));
                    medico.setSenha(resultSet.getString("senha"));
                    medico.setNomeUsuario(resultSet.getString("nomeUsuario"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar médico: " + e.getMessage());
        }
        
        return medico;
    }

    public static MedicoModel buscarMedPorEmail(String email) {
        String sql = "SELECT m.crm, m.especialidade, u.email, u.senha, u.nomeUsuario " +
                "FROM Medico m JOIN Usuario u ON m.email = u.email WHERE u.email = ?";
   
        MedicoModel medico = null;
    
        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement buscarMedico = connection.prepareStatement(sql)) {
        
            buscarMedico.setString(1, email);
            try (ResultSet resultSet = buscarMedico.executeQuery()) { 
                if (resultSet.next()) {
                    medico = new MedicoModel();
                    medico.setCrm(resultSet.getString("crm"));
                    medico.setEspecialidade(resultSet.getString("especialidade"));
                    medico.setEmail(resultSet.getString("email"));
                    medico.setSenha(resultSet.getString("senha"));
                    medico.setNomeUsuario(resultSet.getString("nomeUsuario"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar médico: " + e.getMessage());
        }
        
        return medico;
    }
}
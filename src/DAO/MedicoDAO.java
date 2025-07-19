package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet; 

import util.ConexaoPostgres;
import model.MedicoModel; 

public class MedicoDAO {
    
    public boolean cadastrarMedico(MedicoModel medico) {
        try (Connection connection = ConexaoPostgres.getConexao()) {
            // Primeiro inserir na tabela usuario
            String sqlUsuario = "INSERT INTO usuario (email, senha, nomeusuario) VALUES (?, ?, ?)";
            try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                stmtUsuario.setString(1, medico.getEmail()); 
                stmtUsuario.setString(2, medico.getSenha()); 
                stmtUsuario.setString(3, medico.getNomeUsuario()); 
                stmtUsuario.executeUpdate();
            }
            
            // Depois inserir na tabela medico
            String sqlMedico = "INSERT INTO medico (crm, especialidade, email) VALUES (?, ?, ?)";
            try (PreparedStatement stmtMedico = connection.prepareStatement(sqlMedico)) {
                stmtMedico.setString(1, medico.getCrm());
                stmtMedico.setString(2, medico.getEspecialidade()); 
                stmtMedico.setString(3, medico.getEmail());
                stmtMedico.executeUpdate();
            }
            
            System.out.println("Médico cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar médico: " + e.getMessage());
            return false;
        }
    }

    public boolean editarMedico(MedicoModel medico) {
        String sql = "UPDATE medico SET especialidade = ? WHERE crm = ?";

        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement editarMedico = connection.prepareStatement(sql)) {

            editarMedico.setString(1, medico.getEspecialidade()); 
            editarMedico.setString(2, medico.getCrm()); 
            
            editarMedico.executeUpdate();
            System.out.println("Médico editado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar médico: " + e.getMessage());
            return false;
        }
    }

    public MedicoModel buscarMedico(String crm) {
        String sql = "SELECT m.crm, m.especialidade, u.email, u.senha, u.nomeusuario " +
                    "FROM medico m JOIN usuario u ON m.email = u.email WHERE m.crm = ?"; //usar join para pegar dados do usuário
    
        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement buscarMedico = connection.prepareStatement(sql)) {
        
            buscarMedico.setString(1, crm);
            try (ResultSet resultSet = buscarMedico.executeQuery()) { 
                if (resultSet.next()) {
                    MedicoModel medico = new MedicoModel();
                    medico.setCrm(resultSet.getString("crm"));
                    medico.setEspecialidade(resultSet.getString("especialidade"));
                    medico.setEmail(resultSet.getString("email"));
                    medico.setSenha(resultSet.getString("senha"));
                    medico.setNomeUsuario(resultSet.getString("nomeusuario"));
                    
                    return medico;
                } else {
                    System.out.println("Médico não encontrado.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar médico: " + e.getMessage());
            return null;
        }
    }

    public MedicoModel buscarPorCrm(String crm) {
        return buscarMedico(crm); 
    }

}
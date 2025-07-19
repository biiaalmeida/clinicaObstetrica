package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.ConsultaModel;
import model.MedicoModel;
import model.PacienteModel;
import util.ConexaoPostgres;

public class ConsultaDAO {
    
    public boolean cadastrarConsulta(ConsultaModel consulta) {
        String sql = "INSERT INTO consulta (dataConsulta, dataPrevistaParto, dataUltimaMenstruacao, tipoParto, qtdSemanas, cpf, crm) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoPostgres.getConexao();
             PreparedStatement novaConsulta = conexao.prepareStatement(sql)) {
            
            novaConsulta.setObject(1, consulta.getDataConsulta());
            novaConsulta.setObject(2, consulta.getDataPrevistaParto());
            novaConsulta.setObject(3, consulta.getDataUltimaMenstruacao());
            novaConsulta.setString(4, consulta.getTipoParto());
            novaConsulta.setString(5, consulta.getQtdSemanas());
            novaConsulta.setString(6, consulta.getPaciente().getCpf());
            novaConsulta.setString(7, consulta.getMedico().getCrm());

            novaConsulta.executeUpdate();
            System.out.println("Consulta cadastrada com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar consulta: " + e.getMessage());
            return false;
        }
    }

    public ConsultaModel buscarConsulta(int codigoConsulta) {
        String sql = "SELECT * FROM consulta WHERE codigoConsulta = ?";
        
        try (Connection conexao = ConexaoPostgres.getConexao();
             PreparedStatement buscaConsulta = conexao.prepareStatement(sql)) {

            buscaConsulta.setInt(1, codigoConsulta);
            
            try (ResultSet rs = buscaConsulta.executeQuery()) {
                if (rs.next()) {
                    return criarConsultaFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consulta: " + e.getMessage());
        }
        return null;
    }
    
    public List<ConsultaModel> listarConsultas() {
        List<ConsultaModel> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consulta";
        
        try (Connection conexao = ConexaoPostgres.getConexao();
             PreparedStatement listaConsultas = conexao.prepareStatement(sql);
             ResultSet rs = listaConsultas.executeQuery()) {
            
            while (rs.next()) {
                ConsultaModel consulta = criarConsultaFromResultSet(rs);
                if (consulta != null) {
                    consultas.add(consulta);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }
        
        return consultas;
    }
    
    public List<ConsultaModel> buscarConsultasPorPaciente(String cpf) {
        List<ConsultaModel> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE cpf = ?";
        
        try (Connection conexao = ConexaoPostgres.getConexao();
             PreparedStatement buscaConsultasPorPaciente = conexao.prepareStatement(sql)) {
            
            buscaConsultasPorPaciente.setString(1, cpf);
            
            try (ResultSet rs = buscaConsultasPorPaciente.executeQuery()) {
                while (rs.next()) {
                    ConsultaModel consulta = criarConsultaFromResultSet(rs);
                    if (consulta != null) {
                        consultas.add(consulta);
                    }
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consultas por paciente: " + e.getMessage());
        }
        
        return consultas;
    }
    
    private ConsultaModel criarConsultaFromResultSet(ResultSet rs) throws SQLException {
        ConsultaModel consulta = new ConsultaModel();
        consulta.setCodigoConsulta(rs.getInt("codigoConsulta"));
        consulta.setDataConsulta(rs.getObject("dataConsulta", LocalDate.class));
        consulta.setDataPrevistaParto(rs.getObject("dataPrevistaParto", LocalDate.class));
        consulta.setDataUltimaMenstruacao(rs.getObject("dataUltimaMenstruacao", LocalDate.class));
        consulta.setTipoParto(rs.getString("tipoParto"));
        consulta.setQtdSemanas(rs.getString("qtdSemanas"));
        
        String cpfPaciente = rs.getString("cpf");
        String crmMedico = rs.getString("crm");
        
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();
        
        PacienteModel paciente = pacienteDAO.buscarPaciente(cpfPaciente);
        MedicoModel medico = medicoDAO.buscarMedico(crmMedico);
        
        if (paciente != null && medico != null) {
            consulta.setPaciente(paciente);
            consulta.setMedico(medico);
            return consulta;
        } else {
            System.out.println("Aviso: Paciente ou médico não encontrado para consulta " + rs.getInt("codigoConsulta"));
            return null; 
        }
    }
}
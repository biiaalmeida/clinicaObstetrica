package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;

import util.ConexaoPostgres;

import model.ConsultaModel;
import model.MedicoModel;
import model.Paciente;
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
            novaConsulta.setString(6, consulta.getPaciente().getcpf());
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
        
        try (Connection conn = ConexaoPostgres.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigoConsulta);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Criar objeto consulta
                    ConsultaModel consulta = new ConsultaModel();
                    consulta.setCodigoConsulta(rs.getInt("codigoConsulta"));
                    consulta.setDataConsulta(rs.getObject("dataConsulta", LocalDate.class));
                    consulta.setDataPrevistaParto(rs.getObject("dataPrevistaParto", LocalDate.class));
                    consulta.setDataUltimaMenstruacao(rs.getObject("dataUltimaMenstruacao", LocalDate.class));
                    consulta.setTipoParto(rs.getString("tipoParto"));
                    consulta.setQtdSemanas(rs.getString("qtdSemanas"));

                    // FORMA CORRETA: Usar DAOs para buscar objetos relacionados
                    String cpfPaciente = rs.getString("cpf");
                    String crmMedico = rs.getString("crm");
                    
                    // Criar instâncias dos DAOs
                    PacienteDAO pacienteDAO = new PacienteDAO();
                    MedicoDAO medicoDAO = new MedicoDAO();
                    
                    // Buscar os objetos usando os DAOs
                    Paciente paciente = pacienteDAO.buscarPorCpf(cpfPaciente);
                    MedicoModel medico = medicoDAO.buscarPorCrm(crmMedico);
                    
                    // Definir os objetos na consulta
                    consulta.setPaciente(paciente);
                    consulta.setMedico(medico);

                    return consulta;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consulta: " + e.getMessage());
        }
        return null;
    }
}
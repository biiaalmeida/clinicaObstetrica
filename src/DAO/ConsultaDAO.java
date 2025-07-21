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
        String sql = "INSERT INTO Consulta (codigoConsulta, dataConsulta, dataPrevistaParto, dataUltimaMenstruacao, tipoParto, qtdSemanas, cpfPaciente, crmMedico) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conexao = ConexaoPostgres.getConexao();
             PreparedStatement novaConsulta = conexao.prepareStatement(sql)) {
            
            novaConsulta.setInt(1, consulta.getCodigoConsulta());
            novaConsulta.setObject(2, consulta.getDataConsulta());
            novaConsulta.setObject(3, consulta.getDataPrevistaParto());
            novaConsulta.setObject(4, consulta.getDataUltimaMenstruacao());
            novaConsulta.setString(5, consulta.getTipoParto());
            novaConsulta.setString(6, consulta.getQtdSemanas());
            novaConsulta.setString(7, consulta.getPaciente().getCpf());
            novaConsulta.setString(8, consulta.getMedico().getCrm());

            novaConsulta.executeUpdate();
            System.out.println("Consulta cadastrada com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar consulta: " + e.getMessage());
            return false;
        }
    }

    public static ConsultaModel buscarConsulta(int codigoConsulta) {
        String sql = "SELECT * FROM Consulta WHERE codigoConsulta = ?";
        ConsultaDAO dao = new ConsultaDAO();
        
        try (Connection conexao = ConexaoPostgres.getConexao();
             PreparedStatement buscaConsulta = conexao.prepareStatement(sql)) {

            buscaConsulta.setInt(1, codigoConsulta);
            
            try (ResultSet rs = buscaConsulta.executeQuery()) {
                if (rs.next()) {
                    return dao.criarConsultaFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consulta: " + e.getMessage());
        }
        return null;
    }
    
    public List<ConsultaModel> listarConsultas() {
        List<ConsultaModel> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta";
        
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
    
    public static List<ConsultaModel> buscarConsultasPorPaciente(String cpf) {
        List<ConsultaModel> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta WHERE cpfPaciente = ?";
        ConsultaDAO dao = new ConsultaDAO();
        
        try (Connection conexao = ConexaoPostgres.getConexao();
             PreparedStatement buscaConsultasPorPaciente = conexao.prepareStatement(sql)) {
            
            buscaConsultasPorPaciente.setString(1, cpf);
            
            try (ResultSet rs = buscaConsultasPorPaciente.executeQuery()) {
                while (rs.next()) {
                    ConsultaModel consulta = dao.criarConsultaFromResultSet(rs);
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

        String cpfPaciente = rs.getString("cpfPaciente");
        PacienteModel paciente = PacienteDAO.buscarPaciente(cpfPaciente);
        consulta.setPaciente(paciente);

        String crmMedico = rs.getString("crmMedico");
        MedicoModel medico = MedicoDAO.buscarMedico(crmMedico);
        consulta.setMedico(medico);

        return consulta;
    }

    public static List<ConsultaModel> buscarConsultasPorMedico(String crm) {
        List<ConsultaModel> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta WHERE crmMedico = ?";
        ConsultaDAO dao = new ConsultaDAO();

        try (Connection conexao = ConexaoPostgres.getConexao();
             PreparedStatement buscaConsultasPorMedico = conexao.prepareStatement(sql)) {
        
             buscaConsultasPorMedico.setString(1, crm);
        
            try (ResultSet rs = buscaConsultasPorMedico.executeQuery()) {
                while (rs.next()) {
                    ConsultaModel consulta = dao.criarConsultaFromResultSet(rs);
                    if (consulta != null) {
                        consultas.add(consulta);
                    }
                }
            }
        
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consultas por médico: " + e.getMessage());
        }
    
        return consultas;
    }

    public static List<ConsultaModel> buscarConsultaMedPac(String cpf, String crm) {
        List<ConsultaModel> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta WHERE cpfPaciente = ? AND crmMedico = ?";
        ConsultaDAO dao = new ConsultaDAO();

        try (Connection conexao = ConexaoPostgres.getConexao();
            PreparedStatement buscaConsultaMedPac = conexao.prepareStatement(sql)) {
            
            buscaConsultaMedPac.setString(1, cpf);
            buscaConsultaMedPac.setString(2, crm);
            
            try (ResultSet rs = buscaConsultaMedPac.executeQuery()) {
                while (rs.next()) {
                    ConsultaModel consulta = dao.criarConsultaFromResultSet(rs);
                    if (consulta != null) {
                        consultas.add(consulta);
                    }
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consulta médico-paciente: " + e.getMessage());
        }
        
        return consultas;
    }

    public static List<ConsultaModel> buscarUltimaConsulta(String cpf) {
        List<ConsultaModel> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta WHERE cpfPaciente = ? ORDER BY dataConsulta DESC LIMIT 1";
        ConsultaDAO dao = new ConsultaDAO();

        try (Connection conexao = ConexaoPostgres.getConexao();
            PreparedStatement buscaUltimaConsulta = conexao.prepareStatement(sql)) {
            
            buscaUltimaConsulta.setString(1, cpf);
            
            try (ResultSet rs = buscaUltimaConsulta.executeQuery()) {
                if (rs.next()) {
                    ConsultaModel consulta = dao.criarConsultaFromResultSet(rs);
                    consultas.add(consulta);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar última consulta: " + e.getMessage());
        }
        
        return consultas;
    }
}
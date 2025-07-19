package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import util.ConexaoPostgres;

public class ConsultaControle {
    // Método para imprimir todas as consultas de um médico
    public void imprimirConsultaMedico(String crm) {
        String sql = "SELECT * FROM consulta WHERE crm = ?";
        try (Connection conexao = ConexaoPostgres.getConexao();
                PreparedStatement selectConsultaMed = conexao.prepareStatement(sql)) {

            selectConsultaMed.setString(1, crm);
            try (java.sql.ResultSet rs = selectConsultaMed.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Consulta: " + rs.getInt("codigoConsulta") +
                            ", Data: " + rs.getObject("dataConsulta", LocalDate.class) +
                            ", Paciente: " + rs.getString("cpf"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao imprimir consultas do médico: " + e.getMessage());
        }
    }

    // Método para imprimir todas as consultas de um paciente com um médico
    // específico
    public void imprimirMedPac(String cpf, String crm) {
        String sql = "SELECT * FROM consulta WHERE cpf = ? AND crm = ?";
        try (Connection conexao = ConexaoPostgres.getConexao();
                PreparedStatement selectConsultaMedPac = conexao.prepareStatement(sql)) {

            selectConsultaMedPac.setString(1, cpf);
            selectConsultaMedPac.setString(2, crm);
            try (java.sql.ResultSet rs = selectConsultaMedPac.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Consulta: " + rs.getInt("codigoConsulta") +
                            ", Data: " + rs.getObject("dataConsulta", LocalDate.class) +
                            ", Médico: " + rs.getString("crm"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao imprimir consultas do paciente com médico: " + e.getMessage());
        }
    }

    // Método para imprimir a última consulta de um paciente
    public void imprimirUltimaConsulta(String cpf) {
        String sql = "SELECT * FROM consulta WHERE pacienteCpf = ? ORDER BY dataConsulta DESC LIMIT 1";
        try (Connection conexao = ConexaoPostgres.getConexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Última Consulta: " + rs.getInt("codigoConsulta") +
                            ", Data: " + rs.getObject("dataConsulta", LocalDate.class) +
                            ", Médico: " + rs.getString("medicoCrm"));
                } else {
                    System.out.println("Nenhuma consulta encontrada para o paciente.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar última consulta do paciente: " + e.getMessage());
        }
    }

    // Método para listar todas as consultas de um paciente
    public void listarConsulta(String cpf) {
        String sql = "SELECT * FROM consulta WHERE cpf = ?";
        try (Connection conexao = ConexaoPostgres.getConexao();
                PreparedStatement selectConsulta = conexao.prepareStatement(sql)) {

            selectConsulta.setString(1, cpf);
            try (java.sql.ResultSet rs = selectConsulta.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Consulta: " + rs.getInt("codigoConsulta") +
                            ", Data: " + rs.getObject("dataConsulta", LocalDate.class) +
                            ", Médico: " + rs.getString("crm"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas do paciente: " + e.getMessage());
        }
    }

}

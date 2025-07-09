package model;

import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.ConexaoPostgres;

public class Consulta {
    private int codigoConsulta;
    private LocalDate dataConsulta;
    private LocalDate dataPrevistaParto;
    private LocalDate dataUltimaMenstruacao;
    private String tipoParto;
    private String qtdSemanas;

    private Paciente paciente;
    private Medico medico;

    public Consulta(int codigoConsulta, LocalDate dataConsulta, LocalDate dataPrevistaParto,
            LocalDate dataUltimaMenstruacao, String tipoParto, String qtdSemanas,
            Paciente paciente, Medico medico) {
        this.codigoConsulta = codigoConsulta;
        this.dataConsulta = dataConsulta;
        this.dataPrevistaParto = dataPrevistaParto;
        this.dataUltimaMenstruacao = dataUltimaMenstruacao;
        this.tipoParto = tipoParto;
        this.qtdSemanas = qtdSemanas;
        this.paciente = paciente;
        this.medico = medico;
    }

    // Construtor vazio
    public Consulta() {
    }

    // Getters e Setters
    public int getCodigoConsulta() {
        return codigoConsulta;
    }

    public void setCodigoConsulta(int codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalDate getDataPrevistaParto() {
        return dataPrevistaParto;
    }

    public void setDataPrevistaParto(LocalDate dataPrevistaParto) {
        this.dataPrevistaParto = dataPrevistaParto;
    }

    public LocalDate getDataUltimaMenstruacao() {
        return dataUltimaMenstruacao;
    }

    public void setDataUltimaMenstruacao(LocalDate dataUltimaMenstruacao) {
        this.dataUltimaMenstruacao = dataUltimaMenstruacao;
    }

    public String getTipoParto() {
        return tipoParto;
    }

    public void setTipoParto(String tipoParto) {
        this.tipoParto = tipoParto;
    }

    public String getQtdSemanas() {
        return qtdSemanas;
    }

    public void setQtdSemanas(String qtdSemanas) {
        this.qtdSemanas = qtdSemanas;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    // É melhor retornar boolean para indicar sucesso ou falha na operação
    public boolean cadastrarConsulta(Paciente paciente, Medico medico) {
        String sql = "INSERT INTO consulta (dataConsulta, dataPrevistaParto, dataUltimaMenstruacao, tipoParto, qtdSemanas, pacienteCpf, medicoCrm) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoPostgres.getConexao();
                PreparedStatement novaConsulta = conexao.prepareStatement(sql)) {
            novaConsulta.setObject(1, dataConsulta);
            novaConsulta.setObject(2, dataPrevistaParto);
            novaConsulta.setObject(3, dataUltimaMenstruacao);
            novaConsulta.setString(4, tipoParto);
            novaConsulta.setString(5, qtdSemanas);
            novaConsulta.setString(6, paciente.getCpf());
            novaConsulta.setString(7, medico.getCrm());

            novaConsulta.executeUpdate();
            System.out.println("Consulta cadastrada com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar consulta: " + e.getMessage());
            return false;
        }
    }

    public Consulta buscarConsulta(int codigoConsulta) {
        String sql = "SELECT * FROM Consulta WHERE codigoConsulta = ?";
        try (Connection conn = ConexaoPostgres.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigoConsulta);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
               if (rs.next()) {
                  Consulta consulta = new Consulta();
                  consulta.setCodigoConsulta(rs.getInt("codigoConsulta"));
                  consulta.setDataConsulta(rs.getObject("dataConsulta", LocalDate.class));
                  consulta.setDataPrevistaParto(rs.getObject("dataPrevistaParto", LocalDate.class));
                  consulta.setDataUltimaMenstruacao(rs.getObject("dataUltimaMenstruacao", LocalDate.class));
                  consulta.setTipoParto(rs.getString("tipoParto"));
                  consulta.setQtdSemanas(rs.getString("qtdSemanas"));

                  // Supondo que existam métodos para buscar Paciente e Medico por CPF/CRM
                  String pacienteCpf = rs.getString("pacienteCpf");
                  String medicoCrm = rs.getString("medicoCrm");
                  Paciente paciente = new Paciente().buscarPaciente(pacienteCpf);
                  Medico medico = new Medico().buscarMedico(medicoCrm);

                  consulta.setPaciente(paciente);
                  consulta.setMedico(medico);

                  return consulta;
               }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consulta: " + e.getMessage());
        }
        return null; // Placeholder
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "codigoConsulta=" + codigoConsulta +
                ", dataConsulta=" + dataConsulta +
                ", dataPrevistaParto=" + dataPrevistaParto +
                ", dataUltimaMenstruacao=" + dataUltimaMenstruacao +
                ", tipoParto='" + tipoParto + '\'' +
                ", qtdSemanas='" + qtdSemanas + '\'' +
                ", paciente=" + (paciente != null ? paciente.getCpf() : "N/A") +
                ", medico=" + (medico != null ? medico.getCrm() : "N/A") +
                '}';
    }
}
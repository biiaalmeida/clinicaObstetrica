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
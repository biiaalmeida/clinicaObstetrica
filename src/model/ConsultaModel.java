package model;

import java.time.LocalDate;

public class ConsultaModel {
    private int codigoConsulta;
    private LocalDate dataConsulta;
    private LocalDate dataPrevistaParto;
    private LocalDate dataUltimaMenstruacao;
    private String tipoParto;
    private String qtdSemanas;

    private PacienteModel paciente;
    private MedicoModel medico;

    public ConsultaModel(int codigoConsulta, LocalDate dataConsulta, LocalDate dataPrevistaParto,
            LocalDate dataUltimaMenstruacao, String tipoParto, String qtdSemanas,
            PacienteModel paciente, MedicoModel medico) {
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
    public ConsultaModel() {
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

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }

    public MedicoModel getMedico() {
        return medico;
    }

    public void setMedico(MedicoModel medico) {
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
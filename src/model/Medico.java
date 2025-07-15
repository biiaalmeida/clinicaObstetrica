package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.time.LocalDate;
=======
import util.ConexaoPostegres;
>>>>>>> 5f2a909db76a17da7cbf093c8db0e128a80da8c2

import util.ConexaoPostgres;

public class Medico extends Usuario {
    private String crm;
    private String especialidade;

<<<<<<< HEAD
    public Medico(String nomeUsuario, String email, String senha, String crm, String especialidade) {
        super(nomeUsuario, senha, email);
=======
    public Medico(String crm, String especialidade) {
>>>>>>> 5f2a909db76a17da7cbf093c8db0e128a80da8c2
        this.crm = crm;
        this.especialidade = especialidade;
    }

<<<<<<< HEAD
=======
    // Coloque todos os métodos (getters, setters, cadastrarMedico, etc) aqui dentro
}
>>>>>>> 5f2a909db76a17da7cbf093c8db0e128a80da8c2
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public boolean cadastrarMedico() {

        String sql = "INSERT INTO medico (crm, especialidade) VALUES (?, ?)";

        try (Connection connection = ConexaoPostgres.getConexao();
                PreparedStatement novoMedico = connection.prepareStatement(sql)) {

<<<<<<< HEAD
            novoMedico.setString(1, this.crm);
            novoMedico.setString(2, this.especialidade);
=======
            novoMedico.setString(1, getCrm());
            novoMedico.setString(2, getEspecialidade());
>>>>>>> 5f2a909db76a17da7cbf093c8db0e128a80da8c2

            novoMedico.executeUpdate();
            System.out.println("Médico cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar médico: " + e.getMessage());
            return false;
        }
    }

    public boolean editarMedico() {
        String sql = "UPDATE medico SET especialidade = ? WHERE crm = ?";

        try (Connection connection = ConexaoPostgres.getConexao();
                PreparedStatement editarMedico = connection.prepareStatement(sql)) {

<<<<<<< HEAD
            editarMedico.setString(1, this.especialidade);
            editarMedico.setString(2, this.crm);

=======
            editarMedico.setString(1, getCrm());
            editarMedico.setString(2, getEspecialidade());
            
>>>>>>> 5f2a909db76a17da7cbf093c8db0e128a80da8c2
            editarMedico.executeUpdate();
            System.out.println("Médico editado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar médico: " + e.getMessage());
            return false;
        }
    }

    public void buscarMedico(String crm) {
        String sql = "SELECT * FROM medico WHERE crm = ?";
        try (Connection connection = ConexaoPostgres.getConexao();
                PreparedStatement buscarMedico = connection.prepareStatement(sql)) {

<<<<<<< HEAD
            buscarMedico.setString(1, crm);
            ResultSet resultSet = buscarMedico.executeQuery();

            if (resultSet.next()) {
                this.crm = resultSet.getString("crm");
                this.especialidade = resultSet.getString("especialidade");
                System.out.println("Médico encontrado: " + this.toString());
            } else {
                System.out.println("Médico não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar médico: " + e.getMessage());
=======
         PreparedStatement buscarMedico = connection.prepareStatement(sql)) {
        
        buscarMedico.setString(1, crm);
        ResultSet resultSet = buscarMedico.executeQuery();
        
        if (resultSet.next()) {
            String especialidade = resultSet.getString("especialidade");
            return new Medico(crm, especialidade, especialidade, especialidade, especialidade);
        } else {
            System.out.println("Médico não encontrado.");
            return null;
>>>>>>> 5f2a909db76a17da7cbf093c8db0e128a80da8c2
        }
    }

<<<<<<< HEAD
    public void imprimirMedico(String crm) {
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

    @Override
    public String toString() {
        return "Medico{" +
                "crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
=======
public void imprimirMedico(String crm){
    Medico medico = buscarMedico(crm);
    if (medico != null) {
        System.out.println(medico.toString());
    } else {
        System.out.println("Médico não encontrado.");
>>>>>>> 5f2a909db76a17da7cbf093c8db0e128a80da8c2
    }
}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Medico {
    private String crm;
    private String especialidade;

    public Medico(String nomeUsuario, String email, String senha,String crm, String especialidade) {
        super(nomeUsuario, senha, email);
        this.crm = crm;
        this.especialidade = especialidade;
    }
}
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

    @Override
    public String toString() {
        return "Medico{" +
                "crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }   
public boolean cadastrarMedico (){

    String sql = "INSERT INTO medico (crm, especialidade) VALUES (?, ?)";

    try (Connection connection = ConexaoPostegres.getConnection();
        PreparedStatement novoMedico = connection.prepareStatement(sql)) {

            novoMedico.setString(1, this.crm);
            novoMedico.setString(2, this.especialidade);
            
            novoMedico.executeUpdate();
            System.out.println("Médico cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar médico: " + e.getMessage());
            return false;
        }
}

public boolean editarMedico(){
    String sql = "UPDATE medico SET especialidade = ? WHERE crm = ?";

    try (Connection connection = ConexaoPostegres.getConnection();
        PreparedStatement editarMedico = connection.prepareStatement(sql)) {

            editarMedico.setString(1, this.especialidade);
            editarMedico.setString(2, this.crm);
            
            editarMedico.executeUpdate();
            System.out.println("Médico editado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar médico: " + e.getMessage());
            return false;
        }
}

public Medico buscarMedico(String crm){
    String sql = "SELECT * FROM medico WHERE crm = ?";
    
    try (Connection connection = ConexaoPostegres.getConnection();

         PreparedStatement buscarMedico = connection.prepareStatement(sql)) {
        
        buscarMedico.setString(1, crm);
        ResultSet resultSet = buscarMedico.executeQuery();
        
        if (resultSet.next()) {
            String especialidade = resultSet.getString("especialidade");
            return new Medico(crm, especialidade);
        } else {
            System.out.println("Médico não encontrado.");
            return null;
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar médico: " + e.getMessage());
        return null;
    }
}

public vois imprimirMedico(String crm){
    Medico medico = buscarMedico(crm);
    if (medico != null) {
        System.out.println(medico.toString());
    } else {
        System.out.println("Médico não encontrado.");
    }
}
    
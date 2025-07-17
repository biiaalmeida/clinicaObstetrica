package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConexaoPostgres;

public class Medico extends Usuario {
    private String crm;
    private String especialidade;

    public Medico(String nomeUsuario, String email, String senha, String crm, String especialidade) {
        super(nomeUsuario, email, senha);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    // Construtor vazio
    public Medico() {
        super("", "", "");
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


}
    
package model;

public class MedicoModel extends UsuarioModel {
    private String crm;
    private String especialidade;

    public MedicoModel(String nomeUsuario, String email, String senha, String crm, String especialidade) {
        super(nomeUsuario, email, senha);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    // Construtor vazio
    public MedicoModel() {
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
    
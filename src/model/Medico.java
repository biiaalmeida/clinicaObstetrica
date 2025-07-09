package model;

public class Medico {
    private String crm;
    private String especialidade;

    public Medico(String nomeUsuario, String email, String senha,String crm, String especialidade) {
        super(nomeUsuario, senha, email);
        this.crm = crm;
        this.especialidade = especialidade;
    }
}
    public String getcrm() {
        return crm;
    }

    public void setcrm(String crm) {
        this.crm = crm;
    }

    public String getespecialidade() {
        return especialidade;
    }

    public void setespecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }

    
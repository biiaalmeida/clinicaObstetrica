package model;

public class PacienteModel extends UsuarioModel{
    private String cpf;
    private int idade;
    private String telefoneContato;
    private String endereco;
    private String tipoPlanoSaude;
    private String tipoSanguineo;
    private String alergias;
    private int numGestacoesAnteriores;
    private String vacinas;
    private Float peso;
    private String condicoesPreEx;

    public PacienteModel(String nomeUsuario, String email, String senha, String cpf, int idade, String telefoneContato, String endereco, String tipoPlanoSaude, String tipoSanguineo, 
    String alergias, int numGestacoesAnteriores, String vacinas, Float peso, String condicoesPreEx) {

        super(nomeUsuario, email, senha);
        this.cpf = cpf;
        this.idade = idade;
        this.telefoneContato = telefoneContato;
        this.endereco = endereco;
        this.tipoPlanoSaude = tipoPlanoSaude;
        this.tipoSanguineo = tipoSanguineo;
        this.alergias = alergias;
        this.numGestacoesAnteriores = numGestacoesAnteriores;
        this.vacinas = vacinas;
        this.peso = peso;
        this.condicoesPreEx = condicoesPreEx;
    }

    public PacienteModel() {
        super("", "", "");
    }
 
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoPlanoSaude() {
        return tipoPlanoSaude;
    }

    public void setTipoPlanoSaude(String tipoPlanoSaude) {
        this.tipoPlanoSaude = tipoPlanoSaude;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public int getNumGestacoesAnteriores() {
        return numGestacoesAnteriores;
    }

    public void setNumGestacoesAnteriores(int numGestacoesAnteriores) {
        this.numGestacoesAnteriores = numGestacoesAnteriores;
    }

    public String getVacinas() {
        return vacinas;
    }

    public void setVacinas(String vacinas) {
        this.vacinas = vacinas;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getCondicoesPreEx() {
        return condicoesPreEx;
    }   

    public void setCondicoesPreEx(String condicoesPreEx) {
        this.condicoesPreEx = condicoesPreEx;
    }
    
    
    @Override   
    public String toString() {
        return "Paciente{" +
                super.toString() +
                ", cpf='" + cpf + '\'' +
                ", idade=" + idade +
                ", telefoneContato='" + telefoneContato + '\'' +
                ", endereco='" + endereco + '\'' +
                ", tipoPlanoSaude='" + tipoPlanoSaude + '\'' +
                ", tipoSanguineo='" + tipoSanguineo + '\'' +
                ", alergias='" + alergias + '\'' +
                ", numGestacoesAnteriores=" + numGestacoesAnteriores +
                ", vacinas='" + vacinas + '\'' +
                ", peso=" + peso +
                ", condicoesPreEx='" + condicoesPreEx + '\'' +
                '}';
    }
}

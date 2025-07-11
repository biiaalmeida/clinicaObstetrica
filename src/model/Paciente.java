package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Paciente extends Usuario{
    private String cpf;
    private int idade;
    private String telefoneContato;
    private String tipoPlanoSaude;
    private String tipoSanguineo;
    private String alergias;
    private int numGestacoesAnteriores;
    private String vacinas;
    private Float peso;
    private String condicoesPreEx;

    public Paciente(String nomeUsuario, String email, String senha, String cpf, int idade, String telefoneContato,String tipoPlanoSaude, String tipoSanguineo, 
    String alergias, int numGestacoesAnteriores, String vacinas, Float peso, String condicoesPreEx) {

        super(nomeUsuario, email, senha);
        this.cpf = cpf;
        this.idade = idade;
        this.telefoneContato = telefoneContato;
        this.tipoPlanoSaude = tipoPlanoSaude;
        this.tipoSanguineo = tipoSanguineo;
        this.alergias = alergias;
        this.numGestacoesAnteriores = numGestacoesAnteriores;
        this.vacinas = vacinas;
        this.peso = peso;
        this.condicoesPreEx = condicoesPreEx;
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
                "nomeUsuario='" + getNomeUsuario() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cpf='" + cpf + '\'' +
                ", idade=" + idade +
                ", telefoneContato='" + telefoneContato + '\'' +
                ", tipoPlanoSaude='" + tipoPlanoSaude + '\'' +
                ", tipoSanguineo='" + tipoSanguineo + '\'' +
                ", alergias='" + alergias + '\'' +
                ", numGestacoesAnteriores=" + numGestacoesAnteriores +
                ", vacinas='" + vacinas + '\'' +
                ", peso=" + peso +
                ", condicoesPreEx='" + condicoesPreEx + '\'' +
                '}';
    }

    public boolean cadastrarPaciente() {

        String sql = "INSERT INTO paciente (nome_usuario, email, senha, cpf, idade, telefone_contato, tipo_plano_saude,tipo_sanguineo, alergias, num_gestacoes_anteriores, vacinas, peso, condicoes_pre_ex) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoPostgres.getConnection();
             PreparedStatement novoPaciente = connection.prepareStatement(sql)) {

            novoPaciente.setString(1, getNomeUsuario());
            novoPaciente.setString(2, getEmail());
            novoPaciente.setString(3, getSenha());
            novoPaciente.setString(4, cpf);
            novoPaciente.setInt(5, idade);
            novoPaciente.setString(6, telefoneContato);
            novoPaciente.setString(7, tipoPlanoSaude);
            novoPaciente.setString(8, tipoSanguineo);
            novoPaciente.setString(9, alergias);
            novoPaciente.setInt(10, numGestacoesAnteriores);
            novoPaciente.setString(11, vacinas);
            novoPaciente.setFloat(12, peso);
            novoPaciente.setString(13, condicoesPreEx);

            novoPaciente.executeUpdate();
            System.out.println("Paciente cadastrado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
            return false; 
        }
    }   
}

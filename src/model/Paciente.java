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
 


    public String getcpf() {
        return cpf;
    }

    public void setcpf(String cpf) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, getNomeUsuario());
            preparedStatement.setString(2, getEmail());
            preparedStatement.setString(3, getSenha());
            preparedStatement.setString(4, cpf);
            preparedStatement.setInt(5, idade);
            preparedStatement.setString(6, telefoneContato);
            preparedStatement.setString(7, tipoPlanoSaude);
            preparedStatement.setString(8, tipoSanguineo);
            preparedStatement.setString(9, alergias);
            preparedStatement.setInt(10, numGestacoesAnteriores);
            preparedStatement.setString(11, vacinas);
            preparedStatement.setFloat(12, peso);
            preparedStatement.setString(13, condicoesPreEx);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
            }
    }   
}

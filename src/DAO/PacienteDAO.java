package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.PacienteModel;
import util.ConexaoPostgres;

public class PacienteDAO {
     
    public boolean cadastrarPaciente(PacienteModel paciente) {
        String sql = "INSERT INTO paciente (nome_usuario, email, senha, cpf, idade, telefone_contato, endereco, tipo_plano_saude, tipo_sanguineo, alergias, num_gestacoes_anteriores, vacinas, peso, condicoes_pre_ex) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement novoPaciente = connection.prepareStatement(sql)) {

            novoPaciente.setString(1, paciente.getNomeUsuario());
            novoPaciente.setString(2, paciente.getEmail());
            novoPaciente.setString(3, paciente.getSenha());
            novoPaciente.setString(4, paciente.getCpf());
            novoPaciente.setInt(5, paciente.getIdade());
            novoPaciente.setString(6, paciente.getTelefoneContato());
            novoPaciente.setString(7, paciente.getEndereco());
            novoPaciente.setString(8, paciente.getTipoPlanoSaude());
            novoPaciente.setString(9, paciente.getTipoSanguineo());
            novoPaciente.setString(10, paciente.getAlergias());
            novoPaciente.setInt(11, paciente.getNumGestacoesAnteriores());
            novoPaciente.setString(12, paciente.getVacinas());
            novoPaciente.setFloat(13, paciente.getPeso());
            novoPaciente.setString(14, paciente.getCondicoesPreEx());

            novoPaciente.executeUpdate();
            System.out.println("Paciente cadastrado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
            return false; 
        }
    }   

    public boolean editarPaciente(PacienteModel paciente) {
        String sql = "UPDATE paciente SET nome_usuario = ?, email = ?, senha = ?, idade = ?, telefone_contato = ?, endereco = ?, tipo_plano_saude = ?, tipo_sanguineo = ?, alergias = ?, num_gestacoes_anteriores = ?, vacinas = ?, peso = ?, condicoes_pre_ex = ? WHERE cpf = ?";

        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement editarPaciente = connection.prepareStatement(sql)) {

            editarPaciente.setString(1, paciente.getNomeUsuario());
            editarPaciente.setString(2, paciente.getEmail());
            editarPaciente.setString(3, paciente.getSenha());
            editarPaciente.setInt(4, paciente.getIdade());
            editarPaciente.setString(5, paciente.getTelefoneContato());
            editarPaciente.setString(6, paciente.getEndereco());
            editarPaciente.setString(7, paciente.getTipoPlanoSaude());
            editarPaciente.setString(8, paciente.getTipoSanguineo());
            editarPaciente.setString(9, paciente.getAlergias());
            editarPaciente.setInt(10, paciente.getNumGestacoesAnteriores());
            editarPaciente.setString(11, paciente.getVacinas());
            editarPaciente.setFloat(12, paciente.getPeso());
            editarPaciente.setString(13, paciente.getCondicoesPreEx());
            editarPaciente.setString(14, paciente.getCpf()); 

            editarPaciente.executeUpdate();
            System.out.println("Paciente editado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao editar paciente: " + e.getMessage());
            return false; 
        }
    }

    public PacienteModel buscarPaciente(String cpf) {
        String sql = "SELECT * FROM paciente WHERE cpf = ?";
        PacienteModel paciente = null;
        
        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement buscarPaciente = connection.prepareStatement(sql)) {

            buscarPaciente.setString(1, cpf);
            ResultSet resultSet = buscarPaciente.executeQuery();

            if (resultSet.next()) {
                paciente = new PacienteModel();
                paciente.setNomeUsuario(resultSet.getString("nome_usuario"));
                paciente.setEmail(resultSet.getString("email"));
                paciente.setSenha(resultSet.getString("senha"));
                paciente.setCpf(resultSet.getString("cpf"));
                paciente.setIdade(resultSet.getInt("idade"));
                paciente.setTelefoneContato(resultSet.getString("telefone_contato"));
                paciente.setEndereco(resultSet.getString("endereco"));
                paciente.setTipoPlanoSaude(resultSet.getString("tipo_plano_saude"));
                paciente.setTipoSanguineo(resultSet.getString("tipo_sanguineo"));
                paciente.setAlergias(resultSet.getString("alergias"));
                paciente.setNumGestacoesAnteriores(resultSet.getInt("num_gestacoes_anteriores"));
                paciente.setVacinas(resultSet.getString("vacinas"));
                paciente.setPeso(resultSet.getFloat("peso"));
                paciente.setCondicoesPreEx(resultSet.getString("condicoes_pre_ex"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }

        return paciente;
    }

    public static PacienteModel buscarPorCpf(String cpf) {
        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.buscarPaciente(cpf);
    }
}
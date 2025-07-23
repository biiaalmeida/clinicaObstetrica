package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.PacienteModel;
import util.ConexaoPostgres;

public class PacienteDAO {
     
    // Método para cadastro básico (apenas Usuario + dados mínimos)
    public boolean cadastrarPacienteBasico(PacienteModel paciente) {
        String sqlUsuario = "INSERT INTO Usuario (nomeUsuario, email, senha) VALUES (?, ?, ?)";
        String sqlPaciente = "INSERT INTO paciente (cpf, nome, idade, telefonecontato, endereco, tipoplanosaude, tiposanguineo, alergias, numgestacoesanteriores, vacinas, peso, condicoespreex, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection connection = ConexaoPostgres.getConexao()) {
            
            connection.setAutoCommit(false);
            
            try {
                try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                    stmtUsuario.setString(1, paciente.getNomeUsuario());
                    stmtUsuario.setString(2, paciente.getEmail());
                    stmtUsuario.setString(3, paciente.getSenha());
                    stmtUsuario.executeUpdate();
                }
                
                try (PreparedStatement stmtPaciente = connection.prepareStatement(sqlPaciente)) {
                    stmtPaciente.setString(1, paciente.getCpf());
                    stmtPaciente.setString(2, paciente.getNomeUsuario());
                    stmtPaciente.setInt(3, paciente.getIdade());
                    stmtPaciente.setString(4, paciente.getTelefoneContato() != null ? paciente.getTelefoneContato() : "");
                    stmtPaciente.setString(5, paciente.getEndereco() != null ? paciente.getEndereco() : "");
                    stmtPaciente.setString(6, paciente.getTipoPlanoSaude() != null ? paciente.getTipoPlanoSaude() : "");
                    stmtPaciente.setString(7, paciente.getTipoSanguineo() != null ? paciente.getTipoSanguineo() : "");
                    stmtPaciente.setString(8, paciente.getAlergias() != null ? paciente.getAlergias() : "");
                    stmtPaciente.setInt(9, paciente.getNumGestacoesAnteriores());
                    stmtPaciente.setString(10, paciente.getVacinas() != null ? paciente.getVacinas() : "");
                    
                    // Peso deve ter um valor padrão, não pode ser null
                    stmtPaciente.setFloat(11, paciente.getPeso() != null ? paciente.getPeso() : 0.0f);
                    
                    stmtPaciente.setString(12, paciente.getCondicoesPreEx() != null ? paciente.getCondicoesPreEx() : "");
                    stmtPaciente.setString(13, paciente.getEmail());
                    stmtPaciente.executeUpdate();
                }
                
                connection.commit();
                return true;
                
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            
        } catch (SQLException e) {
            return false;
        }
    }

    // Método para cadastro completo
    public boolean cadastrarPaciente(PacienteModel paciente) {
        String sqlUsuario = "INSERT INTO Usuario (nomeUsuario, email, senha) VALUES (?, ?, ?)";
        String sqlPaciente = "INSERT INTO paciente (cpf, idade, telefoneContato, endereco, tipoPlanodeSaude, tipoSanguineo, alergias, numGestacoesAnteriores, vacinas, peso, condicoesPreEx, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoPostgres.getConexao()) {
            connection.setAutoCommit(false);
            try {
                try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                    stmtUsuario.setString(1, paciente.getNomeUsuario());
                    stmtUsuario.setString(2, paciente.getEmail());
                    stmtUsuario.setString(3, paciente.getSenha());
                    stmtUsuario.executeUpdate();
                }
                try (PreparedStatement stmtPaciente = connection.prepareStatement(sqlPaciente)) {
                    stmtPaciente.setString(1, paciente.getCpf());
                    stmtPaciente.setInt(2, paciente.getIdade());
                    stmtPaciente.setString(3, paciente.getTelefoneContato());
                    stmtPaciente.setString(4, paciente.getEndereco());
                    stmtPaciente.setString(5, paciente.getTipoPlanoSaude());
                    stmtPaciente.setString(6, paciente.getTipoSanguineo());
                    stmtPaciente.setString(7, paciente.getAlergias());
                    stmtPaciente.setInt(8, paciente.getNumGestacoesAnteriores());
                    stmtPaciente.setString(9, paciente.getVacinas());
                    // Tratamento para peso que pode ser null
                    if (paciente.getPeso() != null) {
                        stmtPaciente.setFloat(10, paciente.getPeso());
                    } else {
                        stmtPaciente.setNull(10, java.sql.Types.FLOAT);
                    }
                    stmtPaciente.setString(11, paciente.getCondicoesPreEx());
                    stmtPaciente.setString(12, paciente.getEmail());
                    stmtPaciente.executeUpdate();
                }
                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Erro SQL ao cadastrar paciente: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexão ao cadastrar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }   

    public boolean editarPaciente(PacienteModel paciente) {
        String sqlUsuario = "UPDATE Usuario SET nomeUsuario = ?, senha = ? WHERE email = ?";
        String sqlPaciente = "UPDATE paciente SET idade = ?, telefoneContato = ?, endereco = ?, tipoPlanodeSaude = ?, tipoSanguineo = ?, alergias = ?, numGestacoesAnteriores = ?, vacinas = ?, peso = ?, condicoesPreEx = ? WHERE cpf = ?";
    
        try (Connection connection = ConexaoPostgres.getConexao()) {
            
            connection.setAutoCommit(false);
            
            try {
                try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                    stmtUsuario.setString(1, paciente.getNomeUsuario());
                    stmtUsuario.setString(2, paciente.getSenha());
                    stmtUsuario.setString(3, paciente.getEmail());
                    stmtUsuario.executeUpdate();
                }
                try (PreparedStatement stmtPaciente = connection.prepareStatement(sqlPaciente)) {
                    stmtPaciente.setInt(1, paciente.getIdade());
                    stmtPaciente.setString(2, paciente.getTelefoneContato());
                    stmtPaciente.setString(3, paciente.getEndereco());
                    stmtPaciente.setString(4, paciente.getTipoPlanoSaude());
                    stmtPaciente.setString(5, paciente.getTipoSanguineo());
                    stmtPaciente.setString(6, paciente.getAlergias());
                    stmtPaciente.setInt(7, paciente.getNumGestacoesAnteriores());
                    stmtPaciente.setString(8, paciente.getVacinas());
                    // Tratamento para peso que pode ser null
                    if (paciente.getPeso() != null) {
                        stmtPaciente.setFloat(9, paciente.getPeso());
                    } else {
                        stmtPaciente.setNull(9, java.sql.Types.FLOAT);
                    }
                    stmtPaciente.setString(10, paciente.getCondicoesPreEx());
                    stmtPaciente.setString(11, paciente.getCpf());
                    stmtPaciente.executeUpdate();
                }
                
                connection.commit();
                return true;
                
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro SQL ao editar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static PacienteModel buscarPaciente(String cpf) {
        String sql = "SELECT p.cpf, p.idade, p.telefoneContato, p.endereco, p.tipoPlanodeSaude, p.tipoSanguineo, p.alergias, p.numGestacoesAnteriores, p.vacinas, p.peso, p.condicoesPreEx, u.email, u.senha, u.nomeUsuario " +
                "FROM paciente p JOIN Usuario u ON p.email = u.email WHERE p.cpf = ?";
    
        PacienteModel paciente = null;
        
        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement buscarPaciente = connection.prepareStatement(sql)) {

            buscarPaciente.setString(1, cpf);
            try (ResultSet resultSet = buscarPaciente.executeQuery()) {
                if (resultSet.next()) {
                    paciente = new PacienteModel();
                    paciente.setNomeUsuario(resultSet.getString("nomeUsuario"));
                    paciente.setEmail(resultSet.getString("email"));
                    paciente.setSenha(resultSet.getString("senha"));
                    paciente.setCpf(resultSet.getString("cpf"));
                    paciente.setIdade(resultSet.getInt("idade"));
                    paciente.setTelefoneContato(resultSet.getString("telefoneContato"));
                    paciente.setEndereco(resultSet.getString("endereco"));
                    paciente.setTipoPlanoSaude(resultSet.getString("tipoPlanodeSaude"));
                    paciente.setTipoSanguineo(resultSet.getString("tipoSanguineo"));
                    paciente.setAlergias(resultSet.getString("alergias"));
                    paciente.setNumGestacoesAnteriores(resultSet.getInt("numGestacoesAnteriores"));
                    paciente.setVacinas(resultSet.getString("vacinas"));
                    paciente.setPeso(resultSet.getFloat("peso"));
                    paciente.setCondicoesPreEx(resultSet.getString("condicoesPreEx"));
                }
            }

        } catch (SQLException e) {
            // Log error se necessário
        }

        return paciente;
    }

    public static PacienteModel buscarPorEmail(String email) {
        String sql = "SELECT p.cpf, p.idade, p.telefoneContato, p.endereco, p.tipoPlanodeSaude, p.tipoSanguineo, p.alergias, p.numGestacoesAnteriores, p.vacinas, p.peso, p.condicoesPreEx, u.email, u.senha, u.nomeUsuario " +
                "FROM paciente p JOIN Usuario u ON p.email = u.email WHERE u.email = ?";
        PacienteModel paciente = null;

        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement buscarPaciente = connection.prepareStatement(sql)) {

            buscarPaciente.setString(1, email);
            try (ResultSet resultSet = buscarPaciente.executeQuery()) {
                if (resultSet.next()) {
                    paciente = new PacienteModel();
                    paciente.setNomeUsuario(resultSet.getString("nomeUsuario"));
                    paciente.setEmail(resultSet.getString("email"));
                    paciente.setSenha(resultSet.getString("senha"));
                    paciente.setCpf(resultSet.getString("cpf"));
                    paciente.setIdade(resultSet.getInt("idade"));
                    paciente.setTelefoneContato(resultSet.getString("telefoneContato"));
                    paciente.setEndereco(resultSet.getString("endereco"));
                    paciente.setTipoPlanoSaude(resultSet.getString("tipoPlanodeSaude"));
                    paciente.setTipoSanguineo(resultSet.getString("tipoSanguineo"));
                    paciente.setAlergias(resultSet.getString("alergias"));
                    paciente.setNumGestacoesAnteriores(resultSet.getInt("numGestacoesAnteriores"));
                    paciente.setVacinas(resultSet.getString("vacinas"));
                    paciente.setPeso(resultSet.getFloat("peso"));
                    paciente.setCondicoesPreEx(resultSet.getString("condicoesPreEx"));

                }
            }

        } catch (SQLException e) {
            // Log error se necessário
        }

        return paciente;
    }
}
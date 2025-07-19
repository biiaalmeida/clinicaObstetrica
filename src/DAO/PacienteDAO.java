package dao;
public class PacienteDAO  {
     public boolean cadastrarPaciente() {

        String sql = "INSERT INTO paciente (nome_usuario, email, senha, cpf, idade, telefone_contato, endereco, tipo_plano_saude,tipo_sanguineo, alergias, num_gestacoes_anteriores, vacinas, peso, condicoes_pre_ex) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement novoPaciente = connection.prepareStatement(sql)) {

            novoPaciente.setString(1, getNomeUsuario());
            novoPaciente.setString(2, getEmail());
            novoPaciente.setString(3, getSenha());
            novoPaciente.setString(4, cpf);
            novoPaciente.setInt(5, idade);
            novoPaciente.setString(6, telefoneContato);
            novoPaciente.setString(7, endereco);
            novoPaciente.setString(8, tipoPlanoSaude);
            novoPaciente.setString(9, tipoSanguineo);
            novoPaciente.setString(10, alergias);
            novoPaciente.setInt(11, numGestacoesAnteriores);
            novoPaciente.setString(12, vacinas);
            novoPaciente.setFloat(13, peso);
            novoPaciente.setString(14, condicoesPreEx);

            novoPaciente.executeUpdate();
            System.out.println("Paciente cadastrado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
            return false; 
        }
    }   

    public boolean editarPaciente() {
        String sql = "UPDATE paciente SET nome_usuario = ?, email = ?, senha = ?, cpf = ?, idade = ?, telefone_contato = ?, endereco = ?, tipo_plano_saude = ?, tipo_sanguineo = ?, alergias = ?, num_gestacoes_anteriores = ?, vacinas = ?, peso = ?, condicoes_pre_ex = ? WHERE cpf = ?";

        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement editarPaciente = connection.prepareStatement(sql)) {

            editarPaciente.setString(1, getNomeUsuario());
            editarPaciente.setString(2, getEmail());
            editarPaciente.setString(3, getSenha());
            editarPaciente.setString(4, cpf);
            editarPaciente.setInt(5, idade);
            editarPaciente.setString(6, telefoneContato);
            editarPaciente.setString(7, endereco);
            editarPaciente.setString(8, tipoPlanoSaude);
            editarPaciente.setString(9, tipoSanguineo);
            editarPaciente.setString(10, alergias);
            editarPaciente.setInt(11, numGestacoesAnteriores);
            editarPaciente.setString(12, vacinas);
            editarPaciente.setFloat(13, peso);
            editarPaciente.setString(14, condicoesPreEx);
            editarPaciente.setString(15, cpf);

            editarPaciente.executeUpdate();
            System.out.println("Paciente editado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao editar paciente: " + e.getMessage());
            return false; 
        }
    
    }

    public Paciente buscarPaciente(String cpf) {
        String sql = "SELECT * FROM paciente WHERE cpf = ?";
        Paciente paciente = null;
        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement buscarPaciente = connection.prepareStatement(sql)) {

            buscarPaciente.setString(1, cpf);
            var resultSet = buscarPaciente.executeQuery();

            if (resultSet.next()) {
                paciente = new Paciente(
                        resultSet.getString("nome_usuario"),
                        resultSet.getString("email"),
                        resultSet.getString("senha"),
                        resultSet.getString("cpf"),
                        resultSet.getInt("idade"),
                        resultSet.getString("telefone_contato"),
                        resultSet.getString("endereco"),
                        resultSet.getString("tipo_plano_saude"),
                        resultSet.getString("tipo_sanguineo"),
                        resultSet.getString("alergias"),
                        resultSet.getInt("num_gestacoes_anteriores"),
                        resultSet.getString("vacinas"),
                        resultSet.getFloat("peso"),
                        resultSet.getString("condicoes_pre_ex")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }

        return paciente;

    }
}
   
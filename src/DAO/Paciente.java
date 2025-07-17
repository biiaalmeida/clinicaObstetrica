public class PacienteDAO  {
     public boolean cadastrarPaciente() {

        String sql = "INSERT INTO paciente (nome_usuario, email, senha, cpf, idade, telefone_contato, tipo_plano_saude,tipo_sanguineo, alergias, num_gestacoes_anteriores, vacinas, peso, condicoes_pre_ex) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoPostgres.getConexao();
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

    public boolean editarPaciente() {
        String sql = "UPDATE paciente SET nome_usuario = ?, email = ?, senha = ?, cpf = ?, idade = ?, telefone_contato = ?, tipo_plano_saude = ?, tipo_sanguineo = ?, alergias = ?, num_gestacoes_anteriores = ?, vacinas = ?, peso = ?, condicoes_pre_ex = ? WHERE cpf = ?";

        try (Connection connection = ConexaoPostgres.getConexao();
             PreparedStatement editarPaciente = connection.prepareStatement(sql)) {

            editarPaciente.setString(1, getNomeUsuario());
            editarPaciente.setString(2, getEmail());
            editarPaciente.setString(3, getSenha());
            editarPaciente.setString(4, cpf);
            editarPaciente.setInt(5, idade);
            editarPaciente.setString(6, telefoneContato);
            editarPaciente.setString(7, tipoPlanoSaude);
            editarPaciente.setString(8, tipoSanguineo);
            editarPaciente.setString(9, alergias);
            editarPaciente.setInt(10, numGestacoesAnteriores);
            editarPaciente.setString(11, vacinas);
            editarPaciente.setFloat(12, peso);
            editarPaciente.setString(13, condicoesPreEx);
            editarPaciente.setString(14, cpf);

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
   
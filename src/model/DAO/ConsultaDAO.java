public class ConsultaDAO {

public boolean cadastrarConsulta(Paciente paciente, Medico medico) {
        String sql = "INSERT INTO consulta (dataConsulta, dataPrevistaParto, dataUltimaMenstruacao, tipoParto, qtdSemanas, pacienteCpf, medicoCrm) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoPostgres.getConexao();
                PreparedStatement novaConsulta = conexao.prepareStatement(sql)) {
            novaConsulta.setObject(1, dataConsulta);
            novaConsulta.setObject(2, dataPrevistaParto);
            novaConsulta.setObject(3, dataUltimaMenstruacao);
            novaConsulta.setString(4, tipoParto);
            novaConsulta.setString(5, qtdSemanas);
            novaConsulta.setString(6, paciente.getCpf());
            novaConsulta.setString(7, medico.getCrm());

            novaConsulta.executeUpdate();
            System.out.println("Consulta cadastrada com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar consulta: " + e.getMessage());
            return false;
        }
    }
   public Consulta buscarConsulta(int codigoConsulta) {
        String sql = "SELECT * FROM Consulta WHERE codigoConsulta = ?";
        try (Connection conn = ConexaoPostgres.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigoConsulta);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Consulta consulta = new Consulta();
                    consulta.setCodigoConsulta(rs.getInt("codigoConsulta"));
                    consulta.setDataConsulta(rs.getObject("dataConsulta", LocalDate.class));
                    consulta.setDataPrevistaParto(rs.getObject("dataPrevistaParto", LocalDate.class));
                    consulta.setDataUltimaMenstruacao(rs.getObject("dataUltimaMenstruacao", LocalDate.class));
                    consulta.setTipoParto(rs.getString("tipoParto"));
                    consulta.setQtdSemanas(rs.getString("qtdSemanas"));

                    // Supondo que existam métodos para buscar Paciente e Medico por CPF/CRM
                    String pacienteCpf = rs.getString("pacienteCpf");
                    String medicoCrm = rs.getString("medicoCrm");
                    Paciente paciente = new Paciente().buscarPaciente(pacienteCpf);
                    Medico medico = new Medico().buscarMedico(medicoCrm);

                    consulta.setPaciente(paciente);
                    consulta.setMedico(medico);

                    return consulta;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consulta: " + e.getMessage());
        }
        return null;
    }
}
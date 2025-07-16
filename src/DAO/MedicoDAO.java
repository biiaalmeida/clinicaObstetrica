public class MedicoDAO  {
    public boolean cadastrarMedico (){
        try (Connection connection = ConexaoPostgres.getConexao()) {
            // Primeiro inserir na tabela usuario
            String sqlUsuario = "INSERT INTO usuario (email, senha, nomeusuario) VALUES (?, ?, ?)";
            try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                stmtUsuario.setString(1, this.getEmail());
                stmtUsuario.setString(2, this.getSenha());
                stmtUsuario.setString(3, this.getNomeUsuario());
                stmtUsuario.executeUpdate();
            }
            
            // Depois inserir na tabela medico
            String sqlMedico = "INSERT INTO medico (crm, especialidade, email) VALUES (?, ?, ?)";
            try (PreparedStatement stmtMedico = connection.prepareStatement(sqlMedico)) {
                stmtMedico.setString(1, this.crm);
                stmtMedico.setString(2, this.especialidade);
                stmtMedico.setString(3, this.getEmail());
                stmtMedico.executeUpdate();
            }
            
            System.out.println("Médico cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar médico: " + e.getMessage());
            return false;
        }
    }
  public boolean editarMedico(){
        String sql = "UPDATE medico SET especialidade = ? WHERE crm = ?";

        try (Connection connection = ConexaoPostgres.getConexao();
            PreparedStatement editarMedico = connection.prepareStatement(sql)) {

            editarMedico.setString(1, this.especialidade);
            editarMedico.setString(2, this.crm);
            
            editarMedico.executeUpdate();
            System.out.println("Médico editado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar médico: " + e.getMessage());
            return false;
        }
    }

    public Medico buscarMedico(String crm){
        String sql = "SELECT * FROM medico WHERE crm = ?";
    
        try (Connection connection = ConexaoPostgres.getConexao();
            PreparedStatement buscarMedico = connection.prepareStatement(sql)) {
        
            buscarMedico.setString(1, crm);
            ResultSet resultSet = buscarMedico.executeQuery();
              if (resultSet.next()) {
            String especialidade = resultSet.getString("especialidade");
            // Criar médico usando construtor vazio e setters
            Medico medico = new Medico();
            medico.setCrm(crm);
            medico.setEspecialidade(especialidade);
            return medico;
        } else {
                System.out.println("Médico não encontrado.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar médico: " + e.getMessage());
            return null;
        }
    }
}   


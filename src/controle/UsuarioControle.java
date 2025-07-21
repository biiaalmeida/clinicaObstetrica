package controle;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import model.MedicoModel;
import model.PacienteModel;
import model.UsuarioModel;

public class UsuarioControle {

    // Método para realizar login (alias para autenticar)
    public UsuarioModel realizarLogin(String email, String senha) {
        return autenticar(email, senha);
    }
    
    // Método para verificar tipo de usuário
    public String verificarTipoUsuario(String email) {
        return identificarTipoPorEmail(email);
    }

    public UsuarioModel autenticar(String email, String senha) {
        try {
            String tipoUsuario = identificarTipoPorEmail(email);
            
            if (tipoUsuario.equals("MEDICO")) {
                MedicoModel medico = MedicoDAO.buscarMedPorEmail(email);
                
                if (medico != null && medico.getSenha().equals(senha)) {
                    System.out.println("Médico autenticado com sucesso!");
                    return medico;
                } else {
                    System.out.println("Email ou senha incorretos para médico!");
                    return null;
                }
                
            } else {
                PacienteModel paciente = PacienteDAO.buscarPorEmail(email);
                
                if (paciente != null && paciente.getSenha().equals(senha)) {
                    System.out.println("Paciente autenticado com sucesso!");
                    return paciente;
                } else {
                    System.out.println("Email ou senha incorretos para paciente!");
                    return null;
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erro na autenticação: " + e.getMessage());
            return null;
        }
    }

    public boolean cadastrar(String nome, String email, String senha) {
        return cadastrar(nome, email, senha, null);
    }
    
    public boolean cadastrar(String nome, String email, String senha, String cpf) {
        try {
            String tipoUsuario = identificarTipoPorEmail(email);
            
            if (tipoUsuario.equals("MEDICO")) {
                MedicoModel novoMedico = new MedicoModel();
                novoMedico.setNomeUsuario(nome);
                novoMedico.setEmail(email);
                novoMedico.setSenha(senha);
                
                MedicoDAO medicoDAO = new MedicoDAO();
                boolean sucesso = medicoDAO.cadastrarMedico(novoMedico);
                
                if (sucesso) {
                    System.out.println("Médico cadastrado com sucesso!");
                    return true;
                } else {
                    System.out.println("Erro ao cadastrar médico!");
                    return false;
                }
                
            } else {
                if (cpf == null || cpf.trim().isEmpty()) {
                    System.out.println("CPF é obrigatório para cadastro de pacientes!");
                    return false;
                }
                
                PacienteModel novoPaciente = new PacienteModel();
                novoPaciente.setNomeUsuario(nome);
                novoPaciente.setEmail(email);
                novoPaciente.setSenha(senha);
                novoPaciente.setCpf(cpf.trim());
                novoPaciente.setIdade(0); // valores padrão
                novoPaciente.setTelefoneContato("");
                novoPaciente.setEndereco("");
                novoPaciente.setTipoPlanoSaude("");
                novoPaciente.setTipoSanguineo("");
                novoPaciente.setAlergias("");
                novoPaciente.setNumGestacoesAnteriores(0);
                novoPaciente.setVacinas("");
                novoPaciente.setPeso(0.0f);
                novoPaciente.setCondicoesPreEx("");
                
                PacienteDAO pacienteDAO = new PacienteDAO();
                boolean sucesso = pacienteDAO.cadastrarPacienteBasico(novoPaciente);
                
                if (sucesso) {
                    System.out.println("Paciente cadastrado com sucesso!");
                    return true;
                } else {
                    System.out.println("Erro ao cadastrar paciente!");
                    return false;
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
            return false;
        }
    }
    
    public boolean cadastrarMedicoCompleto(String nome, String email, String senha, String crm, String especialidade) {
        try {
            MedicoModel novoMedico = new MedicoModel();
            novoMedico.setNomeUsuario(nome);
            novoMedico.setEmail(email);
            novoMedico.setSenha(senha);
            novoMedico.setCrm(crm);
            novoMedico.setEspecialidade(especialidade);
            
            MedicoDAO medicoDAO = new MedicoDAO();
            boolean sucesso = medicoDAO.cadastrarMedico(novoMedico);
            
            if (sucesso) {
                System.out.println("Médico cadastrado com dados completos!");
                return true;
            } else {
                System.out.println("Erro ao cadastrar médico!");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Erro no cadastro do médico: " + e.getMessage());
            return false;
        }
    }
    
    public boolean cadastrarPacienteCompleto(String nome, String email, String senha, String cpf, int idade, String telefone) {
        try {
            PacienteModel novoPaciente = new PacienteModel();
            novoPaciente.setNomeUsuario(nome);
            novoPaciente.setEmail(email);
            novoPaciente.setSenha(senha);
            novoPaciente.setCpf(cpf);
            novoPaciente.setIdade(idade);
            novoPaciente.setTelefoneContato(telefone);
            
            // Preencher campos obrigatórios com valores padrão
            novoPaciente.setEndereco("");
            novoPaciente.setTipoPlanoSaude("");
            novoPaciente.setTipoSanguineo("");
            novoPaciente.setAlergias("");
            novoPaciente.setNumGestacoesAnteriores(0);
            novoPaciente.setVacinas("");
            novoPaciente.setPeso(0.0f);
            novoPaciente.setCondicoesPreEx("");
            
            PacienteDAO pacienteDAO = new PacienteDAO();
            boolean sucesso = pacienteDAO.cadastrarPaciente(novoPaciente);
            
            if (sucesso) {
                System.out.println("Paciente cadastrado com dados completos!");
                return true;
            } else {
                System.out.println("Erro ao cadastrar paciente!");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
            return false;
        }
    }
    
    public String identificarTipoPorEmail(String email) {
        if (email.endsWith("@medico") || email.endsWith("@medica")) {
            return "MEDICO";
        } else {
            return "PACIENTE";
        }
    }
    
    public String obterTipoUsuario(UsuarioModel usuario) {
        if (usuario instanceof MedicoModel) {
            return "MEDICO";
        } else if (usuario instanceof PacienteModel) {
            return "PACIENTE";
        } else {
            return "DESCONHECIDO";
        }
    }
}
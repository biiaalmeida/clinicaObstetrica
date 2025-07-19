package controle;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import model.MedicoModel;
import model.PacienteModel;
import model.UsuarioModel;

public class UsuarioControle {

    public UsuarioModel autenticar(String email, String senha) {
        try {
            String tipoUsuario = identificarTipoPorEmail(email);
            
            if (tipoUsuario.equals("MEDICO")) {
                MedicoModel medico = MedicoDAO.buscarPorEmail(email);
                
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
                PacienteModel novoPaciente = new PacienteModel();
                novoPaciente.setNomeUsuario(nome);
                novoPaciente.setEmail(email);
                novoPaciente.setSenha(senha);
                
                PacienteDAO pacienteDAO = new PacienteDAO();
                boolean sucesso = pacienteDAO.cadastrarPaciente(novoPaciente);
                
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
    
    public String identificarTipoPorEmail(String email) {
        if (email.endsWith("@medico")) {
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
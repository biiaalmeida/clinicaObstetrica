package controle;
import DAO.PacienteDAO;
import model.PacienteModel;

public class PacienteControle {
    public PacienteModel buscarPaciente(String cpf) {
        return PacienteDAO.buscarPorCpf(cpf);
    }

    public void imprimirDadosPaciente(String cpf) {
        PacienteModel paciente = buscarPaciente(cpf);
        if (paciente != null) {
            System.out.println(paciente.toString());
        } else {
            System.out.println("Paciente não encontrado.");
        }
    
    }
}
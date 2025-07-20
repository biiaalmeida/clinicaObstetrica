package controle;
import DAO.PacienteDAO;
import model.PacienteModel;

public class PacienteControle {
    public PacienteModel buscarPaciente(String cpf) {
        return PacienteDAO.buscarPaciente(cpf);
    }

    public void imprimirDadosPaciente(String cpf) {
        PacienteModel paciente = buscarPaciente(cpf);
        if (paciente != null) {
            System.out.println(paciente.toString());
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public boolean atualizarDadosPaciente(PacienteModel paciente) {
        PacienteDAO dao = new PacienteDAO();
        return dao.editarPaciente(paciente);
    }

    public boolean cadastrarPaciente(PacienteModel paciente) {
        PacienteDAO dao = new PacienteDAO();
        return dao.cadastrarPaciente(paciente);
    }
}
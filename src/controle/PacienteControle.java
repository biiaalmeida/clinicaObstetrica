package controle;

public class PacienteControle {
    public void imprimirDadosPaciente(String cpf) {
        PacienteControle paciente = buscarPaciente(cpf);
        if (paciente != null) {
            System.out.println(paciente.toString());
        } else {
            System.out.println("Paciente não encontrado.");
        }
    
    }
}
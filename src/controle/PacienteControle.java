package controle;

public class PacienteControle {
    public void imprimirDadosPaciente(String cpf) {
        Paciente paciente = buscarPaciente(cpf);
        if (paciente != null) {
            System.out.println(paciente.toString());
        } else {
            System.out.println("Paciente não encontrado.");
        }
    
    }
}
package view;
import java.util.Scanner;
import model.Paciente;

public class PacienteView {
    private void CadastrarPaciente(String email){
        system.out.println("=== CADASTRO DE PACIENTE ===");
        system.out.print("Nome: ");
        system.nome = scanner.nextLine();
        system.out.print("Email: ");
        system.email = scanner.nextLine();
        system.out.print("Data de Nascimento (dd/mm/aaaa): ");
        system.nascimento = scanner.nextLine();
        system.out.print("Telefone: ");
        system.telefone = scanner.nextLine();
        system.out.print("Endereço: "); 
        String endereco = scanner.nextLine();  

        Paciente paciente = new Paciente(nome, email, nascimento, telefone, endereco);
        usuarios.add(paciente);
        System.out.println("Cadastro de paciente iniciado.");
        
    }

    private void loginPaciente(String email, String senha){
        System.out.println("=== LOGIN PACIENTE ===");
        boolean autenticado = false;
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Paciente && usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                autenticado = true;
                System.out.println("Login de paciente realizado com sucesso!");
    
            }       
        }
    }

    private void menuPaciente(String email){
        System.out.println("=== MENU PACIENTE ===");
        System.out.println("1. Agendar Consulta");
        System.out.println("2. Ver Consultas Agendadas");
        System.out.println("3. Cancelar Consulta");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        switch (opcao) {
            case 1:
                agendarConsulta(email);
                break;
            case 2:
                verConsultasAgendadas(email);
                break;
            case 3:
                cancelarConsulta(email);
                break;
            case 4:
                System.out.println("Saindo do menu paciente.");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                menuPaciente(email);
                break;
        }
    }

    private void exibirPaciente(String email){
        System.out.println("=== EXIBIR PACIENTE ===");
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Paciente && usuario.getEmail().equals(email)) {
                Paciente paciente = (Paciente) usuario;
                System.out.println("Nome: " + paciente.getNome());
                System.out.println("Email: " + paciente.getEmail());
                System.out.println("Data de Nascimento: " + paciente.getNascimento());
                System.out.println("Telefone: " + paciente.getTelefone());
                System.out.println("Endereço: " + paciente.getEndereco());
                return;
            }
        }
        System.out.println("Paciente não encontrado.");
    }
}

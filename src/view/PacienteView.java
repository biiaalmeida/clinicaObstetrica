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
        system.out.print("CPF: ");
        system.cpf = scanner.nextLine();
        system.out.print("Idade: ");
        system.idade = scanner.nextLine();
        system.out.print("Telefone: ");
        system.telefone = scanner.nextLine();
        system.out.print("Endereço: "); 
        system.endereco = scanner.nextLine();
        system.out.print("Tipo de Plano de Saúde: ");
        system.tipoPlanoSaude = scanner.nextLine();
        system.out.print("Tipo Sanguíneo: ");
        system.tipoSanguineo = scanner.nextLine();
        system.out.print("Alergias: ");
        system.alergias = scanner.nextLine();
        system.out.print("Número de Gestações Anteriores: ");
        system.numGestacoesAnteriores = scanner.nextLine();
        system.out.print("Vacinas: ");
        system.vacinas = scanner.nextLine();
        system.out.print("Peso: ");
        system.peso = scanner.nextLine();
        system.out.print("Condições Pré-existentes: ");
        system.condicoesPreEx = scanner.nextLine();

        Paciente paciente = new Paciente(nome, email, cpf, idade, telefone, endereco, tipoPlanoSaude, tipoSanguineo, alergias, numGestacoesAnteriores, vacinas, peso, condicoesPreEx);
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

    private void exibirPaciente(String cpf){
        System.out.println("=== EXIBIR PACIENTE ===");
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Paciente && usuario.getCpf().equals(cpf)) {
                Paciente paciente = (Paciente) usuario;
                System.out.println("Nome: " + paciente.getNome());
                System.out.println("Email: " + paciente.getEmail());
                System.out.println("CPF: " + paciente.getCpf());
                System.out.println("Idade: " + paciente.getIdade());
                System.out.println("Telefone: " + paciente.getTelefoneContato());
                System.out.println("Endereço: " + paciente.getEndereco());
                System.out.println("Tipo de Plano de Saúde: " + paciente.getTipoPlanoSaude());
                System.out.println("Tipo Sanguíneo: " + paciente.getTipoSanguineo());
                System.out.println("Alergias: " + paciente.getAlergias());
                System.out.println("Número de Gestações Anteriores: " + paciente.getNumGestacoesAnteriores());
                System.out.println("Vacinas: " + paciente.getVacinas());
                System.out.println("Peso: " + paciente.getPeso());
                System.out.println("Condições Pré-existentes: " + paciente.getCondicoesPreEx());
                return;
            }
        }
        System.out.println("Paciente não encontrado.");
    }
}

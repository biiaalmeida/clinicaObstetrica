package view;

import controle.PacienteControle;
import java.util.Scanner;
import model.PacienteModel;



public class PacienteView{
    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        PacienteControle pacienteControle = new PacienteControle();
        int opcao;

        System.out.println("Digite seu CPF:");
        String cpf = scanner.nextLine();

        do { 
            System.out.println("=== BEM-VINDO, PACIENTE ===");
            System.out.println("1. Visualizar meus dados");
            System.out.println("2. Atualizar meus dados");
            System.out.println("3. Ver meu histórico de consultas");
            System.out.println("4. Imprimir minha última consulta");
            System.out.println("5. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println(" DADOS:");
                    pacienteControle.imprimirDadosPaciente(cpf);
                    break;
                case 2:
                    System.out.println("ATUALIZAR DADOS:");
                    PacienteModel paciente = new PacienteModel();
                    paciente.setCpf(cpf); // cpf já informado

                    System.out.print("Informe o novo nome: ");
                    paciente.setNomeUsuario(scanner.nextLine());
                    System.out.print("Informe o novo email: ");
                    paciente.setEmail(scanner.nextLine());
                    System.out.print("Informe a nova senha: ");
                    paciente.setSenha(scanner.nextLine());
                    System.out.print("Informe a nova idade: ");
                    paciente.setIdade(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Informe o novo telefone de contato: ");
                    paciente.setTelefoneContato(scanner.nextLine());   
                    System.out.print("Informe o novo endereço: ");
                    paciente.setEndereco(scanner.nextLine());
                    System.out.print("Informe o novo tipo de plano de saúde: ");
                    paciente.setTipoPlanoSaude(scanner.nextLine());
                    System.out.print("Informe o novo tipo sanguíneo: ");
                    paciente.setTipoSanguineo(scanner.nextLine());
                    System.out.print("Informe as alergias: ");
                    paciente.setAlergias(scanner.nextLine());
                    boolean atualizado = pacienteControle.atualizarDadosPaciente(paciente);
                    if (atualizado) {
                        System.out.println("Dados atualizados com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar os dados.");
                    }
                    break;
                case 3:
                    // Lógica para ver o histórico de consultas
                    System.out.println("HISTÓRICO DE CONSULTAS:");
                    break;
                case 4:
                    // Lógica para imprimir a última consulta
                    System.out.println("ÚLTIMA CONSULTA:");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            
        } while (opcao != 5);
        scanner.close();
        System.out.println("Obrigado por usar o sistema!");
    }
}



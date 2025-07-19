package view;

import controle.PacienteControle;
import java.util.Scanner;



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



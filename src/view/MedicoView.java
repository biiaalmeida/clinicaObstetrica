package view;

import controle.MedicoControle;
import java.util.Scanner;
import model.MedicoModel;


public class MedicoView {
    public void exibirMenuMedico() {
        Scanner scanner = new Scanner(System.in);
        MedicoControle medicoControle = new MedicoControle();
        int opcao;

        System.out.println("Digite seu CRM:");
        String crm = scanner.nextLine();

        do {
            System.out.println("=== BEM-VINDO, MÉDICO ===");
            System.out.println("1. Visualizar meus dados");
            System.out.println("2. Atualizar meus dados");
            System.out.println("3. Gerenciar consultas");
            System.out.println("4. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println(" DADOS:");
                    medicoControle.imprimirMedico(crm);
                    break;
                case 2:
                    System.out.println("ATUALIZAR DADOS:");
                    MedicoModel medico = new MedicoModel();
                    medico.setCrm(crm);

                    System.out.print("Informe o novo nome: ");
                    medico.setNomeUsuario(scanner.nextLine());
                    System.out.print("Informe o novo email: ");
                    medico.setEmail(scanner.nextLine());
                    System.out.print("Informe a nova senha: ");
                    medico.setSenha(scanner.nextLine());
                    System.out.print("Informe a nova especialidade: ");
                    medico.setEspecialidade(scanner.nextLine());
                    
                    boolean atualizado = medicoControle.atualizarDadosMedico(medico);
                    if (atualizado) {
                        System.out.println("Dados atualizados com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar os dados.");
                    }
                    break;

                case 3:
                    //gerenciar consultas aqui.
                    break;
                
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
        System.out.println("Obrigado por usar o sistema!");

        scanner.close();

    }
}


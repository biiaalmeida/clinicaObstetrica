package view;

import controle.MedicoControle;
import java.util.Scanner;
import model.MedicoModel;


public class MedicoView {
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenuMedico() {
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
                    System.out.println("Abrindo Gestão de Consultas...");
                    ConsultaView consultaView = new ConsultaView();
                    consultaView.menuConsulta();
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
                
        } while (opcao != 4);
        System.out.println("Obrigado por usar o sistema!");
        
    }

    public void cadastrarMedico() {
        MedicoModel medico = new MedicoModel();
        
        System.out.print("Informe o CRM: ");
        medico.setCrm(scanner.nextLine());
        System.out.print("Informe a especialidade: ");
        medico.setEspecialidade(scanner.nextLine());

        MedicoControle medicoControle = new MedicoControle();
        boolean cadastrado = medicoControle.cadastrarMedico(medico);
        
        if (cadastrado) {
            System.out.println("Médico cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar médico.");
        }
    }

    public void iniciar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iniciar'");
    }

}




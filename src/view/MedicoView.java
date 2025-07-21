package view;

import controle.MedicoControle;
import java.util.Scanner;
import model.MedicoModel;

public class MedicoView {
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenuMedico() {
        MedicoControle medicoControle = new MedicoControle();
        System.out.println("Digite seu CRM:");
        String crm = scanner.nextLine();
        
        // Buscar médico por CRM para obter o email
        MedicoModel medico = medicoControle.buscarMedico(crm);
        if (medico != null) {
            exibirMenuMedicoLogadoPorEmail(medico.getEmail());
        } else {
            System.out.println("Médico não encontrado com CRM: " + crm);
        }
    }

    public void exibirMenuMedicoLogadoPorEmail(String email) {
        MedicoControle medicoControle = new MedicoControle();
        int opcao;
            
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
                    medicoControle.imprimirMedicoPorEmail(email);
                    break;
                case 2:
                    System.out.println("ATUALIZAR DADOS:");
                    MedicoModel medico = new MedicoModel();
                    medico.setEmail(email);
                        
                    System.out.print("Informe o novo nome: ");
                    medico.setNomeUsuario(scanner.nextLine());
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
                    System.out.println("GERENCIAR CONSULTAS:");
                    gerenciarConsultas(email);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
        System.out.println("Obrigado por usar o sistema!");
    }
    
    private void gerenciarConsultas(String emailMedico) {
        // Primeiro precisa buscar o CRM do médico pelo email
        MedicoControle medicoControle = new MedicoControle();
        MedicoModel medico = DAO.MedicoDAO.buscarMedPorEmail(emailMedico);
        
        if (medico == null) {
            System.out.println("Erro: Médico não encontrado!");
            return;
        }
        
        String crmMedico = medico.getCrm();
        controle.ConsultaControle consultaControle = new controle.ConsultaControle();
        
        int opcao;
        do {
            System.out.println("\n=== GERENCIAR CONSULTAS ===");
            System.out.println("1. Listar minhas consultas");
            System.out.println("2. Agendar nova consulta");
            System.out.println("3. Buscar consulta por código");
            System.out.println("4. Voltar ao menu principal");
            System.out.print("Escolha uma opcao: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer
            
            switch (opcao) {
                case 1:
                    System.out.println("\n=== SUAS CONSULTAS ===");
                    consultaControle.imprimirConsultaMedico(crmMedico);
                    break;
                    
                case 2:
                    System.out.println("\n=== AGENDAR CONSULTA ===");
                    agendarConsulta(medico, consultaControle);
                    break;
                    
                case 3:
                    System.out.println("\n=== BUSCAR CONSULTA ===");
                    System.out.print("Digite o código da consulta: ");
                    int codigoConsulta = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer
                    consultaControle.imprimirConsulta(codigoConsulta);
                    break;
                    
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                    
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        } while (opcao != 4);
    }
    
    private void agendarConsulta(MedicoModel medico, controle.ConsultaControle consultaControle) {
        System.out.print("CPF do paciente: ");
        String cpfPaciente = scanner.nextLine().trim();

        // Buscar o paciente pelo CPF usando o método correto
        model.PacienteModel paciente = DAO.PacienteDAO.buscarPaciente(cpfPaciente);
        if (paciente == null) {
            System.out.println("Paciente não encontrado com CPF: " + cpfPaciente);
            return;
        }

        System.out.print("Data da consulta (YYYY-MM-DD): ");
        String dataConsultaStr = scanner.nextLine().trim();
        System.out.print("Data prevista do parto (YYYY-MM-DD): ");
        String dataPrevistaPartoStr = scanner.nextLine().trim();
        System.out.print("Data da última menstruação (YYYY-MM-DD): ");
        String dataUltimaMenstruacaoStr = scanner.nextLine().trim();
        System.out.print("Tipo de parto (Normal/Cesárea): ");
        String tipoParto = scanner.nextLine().trim();
        System.out.print("Quantidade de semanas: ");
        String qtdSemanas = scanner.nextLine().trim();

        try {
            java.time.LocalDate dataConsulta = java.time.LocalDate.parse(dataConsultaStr);
            java.time.LocalDate dataPrevistaParto = java.time.LocalDate.parse(dataPrevistaPartoStr);
            java.time.LocalDate dataUltimaMenstruacao = java.time.LocalDate.parse(dataUltimaMenstruacaoStr);

            model.ConsultaModel novaConsulta = new model.ConsultaModel();
            novaConsulta.setDataConsulta(dataConsulta);
            novaConsulta.setDataPrevistaParto(dataPrevistaParto);
            novaConsulta.setDataUltimaMenstruacao(dataUltimaMenstruacao);
            novaConsulta.setTipoParto(tipoParto);
            novaConsulta.setQtdSemanas(qtdSemanas);
            novaConsulta.setPaciente(paciente);
            novaConsulta.setMedico(medico);

            boolean sucesso = consultaControle.cadastrarConsulta(novaConsulta);
            if (sucesso) {
                System.out.println("Consulta agendada com sucesso!");
            } else {
                System.out.println("Erro ao agendar consulta!");
            }
        } catch (Exception e) {
            System.out.println("Formato de data inválido! Use YYYY-MM-DD");
        }
    }
}




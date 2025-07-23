package view;

import java.util.Scanner;

import DAO.MedicoDAO;
import controle.MedicoControle;
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
        MedicoModel medicoLogado = MedicoDAO.buscarMedPorEmail(email);
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
                    // Recarrega os dados do banco antes de mostrar
                    medicoLogado = MedicoDAO.buscarMedPorEmail(email);
                    System.out.println("DADOS ATUALIZADOS:");
                    System.out.println(medicoLogado);
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
                        // Recarrega os dados do banco e mostra imediatamente
                        medicoLogado = MedicoDAO.buscarMedPorEmail(email);
                        System.out.println("Veja abaixo seus dados atualizados:");
                        System.out.println(medicoLogado);
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

        // Solicitar todos os campos obrigatórios da consulta com laço de validação
        String dataConsulta = "";
        String dataPrevistaParto = "";
        String dataUltimaMenstruacao = "";
        String tipoParto = "";
        String qtdSemanas = "";

        // Validação obrigatória para todos os campos
        while (dataConsulta.isEmpty()) {
            System.out.print("Data da consulta (formato: YYYY-MM-DD): ");
            dataConsulta = scanner.nextLine().trim();
            if (dataConsulta.isEmpty()) System.out.println("Campo obrigatório! Digite a data da consulta.");
        }
        while (dataPrevistaParto.isEmpty()) {
            System.out.print("Data prevista do parto (formato: YYYY-MM-DD): ");
            dataPrevistaParto = scanner.nextLine().trim();
            if (dataPrevistaParto.isEmpty()) System.out.println("Campo obrigatório! Digite a data prevista do parto.");
        }
        while (dataUltimaMenstruacao.isEmpty()) {
            System.out.print("Data da última menstruação (formato: YYYY-MM-DD): ");
            dataUltimaMenstruacao = scanner.nextLine().trim();
            if (dataUltimaMenstruacao.isEmpty()) System.out.println("Campo obrigatório! Digite a data da última menstruação.");
        }
        while (tipoParto.isEmpty()) {
            System.out.print("Tipo de parto: ");
            tipoParto = scanner.nextLine().trim();
            if (tipoParto.isEmpty()) System.out.println("Campo obrigatório! Digite o tipo de parto.");
        }
        while (qtdSemanas.isEmpty()) {
            System.out.print("Quantidade de semanas de gestação: ");
            qtdSemanas = scanner.nextLine().trim();
            if (qtdSemanas.isEmpty()) System.out.println("Campo obrigatório! Digite a quantidade de semanas de gestação.");
        }

        // Criar nova consulta
        model.ConsultaModel novaConsulta = new model.ConsultaModel();
        novaConsulta.setMedico(medico);
        novaConsulta.setPaciente(paciente);

        // Converter Strings para LocalDate
        try {
            java.time.LocalDate data = java.time.LocalDate.parse(dataConsulta);
            novaConsulta.setDataConsulta(data);
        } catch (Exception e) {
            System.out.println("Formato de data da consulta inválido! Use YYYY-MM-DD");
            return;
        }
        try {
            java.time.LocalDate dataParto = java.time.LocalDate.parse(dataPrevistaParto);
            novaConsulta.setDataPrevistaParto(dataParto);
        } catch (Exception e) {
            System.out.println("Formato de data prevista do parto inválido! Use YYYY-MM-DD");
            return;
        }
        try {
            java.time.LocalDate dataMenstruacao = java.time.LocalDate.parse(dataUltimaMenstruacao);
            novaConsulta.setDataUltimaMenstrucao(dataMenstruacao);
        } catch (Exception e) {
            System.out.println("Formato de data da última menstruação inválido! Use YYYY-MM-DD");
            return;
        }

        novaConsulta.setTipoParto(tipoParto);
        novaConsulta.setQtdSemanas(qtdSemanas);

        boolean sucesso = consultaControle.cadastrarConsulta(novaConsulta);
        if (sucesso) {
            System.out.println("Consulta agendada com sucesso!");
        } else {
            System.out.println("Erro ao agendar consulta!");
        }
    }
}




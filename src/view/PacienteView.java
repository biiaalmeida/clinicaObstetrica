package view;

import DAO.ConsultaDAO;
import controle.PacienteControle;
import java.util.List;
import java.util.Scanner;
import model.ConsultaModel;
import model.PacienteModel;


public class PacienteView{
    public void exibirMenuPaciente() {
        try (Scanner scanner = new Scanner(System.in)) {
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
                    case 1-> {
                        System.out.println(" DADOS:");
                        pacienteControle.imprimirDadosPaciente(cpf);
                    }
                    case 2 -> {
                        System.out.println("ATUALIZAR DADOS:");
                        PacienteModel paciente = new PacienteModel();
                        paciente.setCpf(cpf);
                        
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
                    }
                        
                    case 3-> {
                        System.out.println("HISTÓRICO DE CONSULTAS:");
                        List<ConsultaModel> consultas = ConsultaDAO.buscarConsultasPorPaciente(cpf);
                        if (consultas.isEmpty()) {
                            System.out.println("Nenhuma consulta encontrada.");
                        } else {
                            for (ConsultaModel consulta : consultas) {
                                System.out.println(consulta);
                            }
                        }
                    }
                        
                    case 4-> {
                        System.out.println("IMPRIMIR ÚLTIMA CONSULTA:");
                        System.out.println("ÚLTIMA CONSULTA:");
                        List<ConsultaModel> ultimaConsulta = ConsultaDAO.buscarUltimaConsulta(cpf);
                        if (!ultimaConsulta.isEmpty()) {
                            ConsultaModel consulta = ultimaConsulta.get(0);
                            System.out.println("Consulta: " + consulta.getCodigoConsulta() +
                                    ", Data: " + consulta.getDataConsulta() +
                                    ", Médico: " + consulta.getMedico().getCrm());
                        } else {
                            System.out.println("Nenhuma consulta encontrada.");
                        }
                    }
                        
                   default-> {
                        System.out.println("Opção inválida! Tente novamente.");
                    }
                }
                
                
                
            } while (opcao != 5);
            System.out.println("Obrigado por usar o sistema!");
        }
    }
    
}



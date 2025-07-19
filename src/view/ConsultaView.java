package view;
import java.util.Scanner;
import java.util.List;
import controle.ConsultaControle;
import DAO.ConsultaDAO;
import model.ConsultaModel;

public class ConsultaView {
    private final ConsultaControle consultaControle;
    private final ConsultaDAO consultaDAO;

    public ConsultaView() {
        this.consultaControle = new ConsultaControle();
        this.consultaDAO = new ConsultaDAO();
    }

    // ===== MÉTODO PRINCIPAL =====
    public void iniciar() {
        System.out.println("=== SISTEMA DE CONSULTAS ===\n");
        
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                continuar = menuConsulta(scanner);
            }
        }
        
        System.out.println("Sistema encerrado!");
    }

    // ===== MENU PRINCIPAL =====
    private boolean menuConsulta(Scanner scanner) {
        System.out.println("=== GESTÃO DE CONSULTAS ===");
        System.out.println("1. Cadastrar Consulta");
        System.out.println("2. Buscar Consulta");
        System.out.println("3. Imprimir Consultas do Médico");
        System.out.println("4. Imprimir Consultas Médico-Paciente");
        System.out.println("5. Imprimir Todas as Consultas");
        System.out.println("6. Listar Consultas");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcao(scanner);
        
        switch (opcao) {
            case 1:
                cadastrarConsulta(scanner);
                break;
            case 2:
                buscarConsulta(scanner);
                break;
            case 3:
                imprimirConsultaMedico(scanner);
                break;
            case 4:
                imprimirConsultaMedPac(scanner);
                break;
            case 5:
                imprimirConsultas();
                break;
            case 6:
                listarConsultas();
                break;
            case 7:
                return false;
            default:
                System.out.println("Opção inválida! Tente novamente.\n");
        }
        
        return true;
    }

    // ===== CADASTRAR CONSULTA =====
    private void cadastrarConsulta(Scanner scanner) {
        System.out.println("\n=== CADASTRAR CONSULTA ===");
        
        System.out.print("CRM do Médico: ");
        String crmMedico = scanner.nextLine().trim();
        
        System.out.print("CPF do Paciente: ");
        String cpfPaciente = scanner.nextLine().trim();
        
        System.out.print("Data da Consulta (dd/mm/aaaa): ");
        String data = scanner.nextLine().trim();
        
        System.out.print("Horário (HH:mm): ");
        String horario = scanner.nextLine().trim();
        
        System.out.print("Motivo da Consulta: ");
        String motivo = scanner.nextLine().trim();
        
        // Chamando método do ConsultaControle
        boolean sucesso = consultaControle.cadastrarConsulta(crmMedico, cpfPaciente, data, horario, motivo);
        
        if (sucesso) {
            System.out.println("Consulta cadastrada com sucesso!\n");
        } else {
            System.out.println("Erro ao cadastrar consulta!\n");
        }
    }

    // ===== BUSCAR CONSULTA =====
    private void buscarConsulta(Scanner scanner) {
        System.out.println("\n=== BUSCAR CONSULTA ===");
        
        System.out.println("Buscar por:");
        System.out.println("1. ID da Consulta");
        System.out.println("2. CRM do Médico");
        System.out.println("3. CPF do Paciente");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerOpcao(scanner);
        
        switch (opcao) {
            case 1:
                buscarPorId(scanner);
                break;
            case 2:
                buscarPorMedico(scanner);
                break;
            case 3:
                buscarPorPaciente(scanner);
                break;
            default:
                System.out.println("Opção inválida!\n");
        }
    }

    private void buscarPorId(Scanner scanner) {
        System.out.print("ID da Consulta: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            // Chamando método do ConsultaDAO
            ConsultaModel consulta = consultaDAO.buscarPorId(id);
            
            if (consulta != null) {
                exibirDadosConsulta(consulta);
            } else {
                System.out.println("Consulta não encontrada!\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!\n");
        }
    }

    private void buscarPorMedico(Scanner scanner) {
        System.out.print("CRM do Médico: ");
        String crm = scanner.nextLine().trim();
        
        // Chamando método do ConsultaDAO
        List<ConsultaModel> consultas = consultaDAO.buscarPorMedico(crm);
        
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada para este médico!\n");
        } else {
            System.out.println("Consultas encontradas (" + consultas.size() + "):");
            for (ConsultaModel consulta : consultas) {
                exibirDadosConsulta(consulta);
            }
        }
    }

    private void buscarPorPaciente(Scanner scanner) {
        System.out.print("CPF do Paciente: ");
        String cpf = scanner.nextLine().trim();
        
        // Chamando método do ConsultaDAO
        List<ConsultaModel> consultas = consultaDAO.buscarPorPaciente(cpf);

        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada para este paciente!\n");
        } else {
            System.out.println("Consultas encontradas (" + consultas.size() + "):");
            for (ConsultaModel consulta : consultas) {
                exibirDadosConsulta(consulta);
            }
        }
    }

    // ===== IMPRIMIR CONSULTAS DO MÉDICO =====
    private void imprimirConsultaMedico(Scanner scanner) {
        System.out.println("\n=== CONSULTAS DO MÉDICO ===");
        
        System.out.print("CRM do Médico: ");
        String crm = scanner.nextLine().trim();
        
        // Chamando método do ConsultaControle
        consultaControle.imprimirConsultaMedico(crm);
    }

    // ===== IMPRIMIR CONSULTAS MÉDICO-PACIENTE =====
    private void imprimirConsultaMedPac(Scanner scanner) {
        System.out.println("\n=== CONSULTAS MÉDICO-PACIENTE ===");
        
        System.out.print("CRM do Médico: ");
        String crm = scanner.nextLine().trim();
        
        System.out.print("CPF do Paciente: ");
        String cpf = scanner.nextLine().trim();
        
        // Chamando método do ConsultaControle
        consultaControle.imprimirConsultaMedPac(crm, cpf);
    }

    // ===== IMPRIMIR TODAS AS CONSULTAS =====
    private void imprimirConsultas() {
        System.out.println("\n=== TODAS AS CONSULTAS ===");
        
        // Chamando método do ConsultaControle
        consultaControle.imprimirConsultas();
    }

    // ===== LISTAR CONSULTAS =====
    private void listarConsultas() {
        System.out.println("\n=== LISTA DE CONSULTAS ===");
        
        // Chamando método do ConsultaDAO
        List<ConsultaModel> consultas = consultaDAO.listarTodas();
        
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.\n");
        } else {
            System.out.println("Total de consultas: " + consultas.size());
            System.out.println("========================================");

            for (ConsultaModel consulta : consultas) {
                System.out.println("ID: " + consulta.getId());
                System.out.println("Médico: " + consulta.getNomeMedico() + " (CRM: " + consulta.getCrmMedico() + ")");
                System.out.println("Paciente: " + consulta.getNomePaciente() + " (CPF: " + consulta.getCpfPaciente() + ")");
                System.out.println("Data: " + consulta.getData());
                System.out.println("Horário: " + consulta.getHorario());
                System.out.println("Status: " + consulta.getStatus());
                System.out.println("========================================");
            }
            System.out.println();
        }
    }

    // ===== MÉTODOS AUXILIARES =====
    private void exibirDadosConsulta(ConsultaModel consulta) {
        System.out.println("========== DADOS DA CONSULTA ==========");
        System.out.println("ID: " + consulta.getId());
        System.out.println("Médico: " + consulta.getNomeMedico());
        System.out.println("CRM: " + consulta.getCrmMedico());
        System.out.println("Paciente: " + consulta.getNomePaciente());
        System.out.println("CPF: " + consulta.getCpfPaciente());
        System.out.println("Data: " + consulta.getData());
        System.out.println("Horário: " + consulta.getHorario());
        System.out.println("Motivo: " + consulta.getMotivo());
        System.out.println("Status: " + consulta.getStatus());
        System.out.println("======================================\n");
    }

    private int lerOpcao(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        ConsultaView sistema = new ConsultaView();
        sistema.iniciar();
    }
}
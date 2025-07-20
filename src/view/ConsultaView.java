package view;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import controle.ConsultaControle;
import java.time.LocalDate;
import java.util.Scanner;
import model.ConsultaModel;


public class ConsultaView {
    private final Scanner scanner = new Scanner(System.in);

    public void menuConsulta() {
        int opcao;
        do {
            System.out.println("=== MENU DE CONSULTAS ===");
            System.out.println("1. Cadastrar Consulta");
            System.out.println("2. Buscar Consulta");
            System.out.println("3. Imprimir Consultas do Médico");
            System.out.println("4. Imprimir Consultas Médico-Paciente");
            System.out.println("5. Imprimir Todas as Consultas");
            System.out.println("6. Listar Consultas");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("CADASTRAR CONSULTA:");
                    cadastrarConsulta();
                }
                case 2 -> {
                    System.out.println("BUSCAR CONSULTA:");
                    buscarConsulta();
                }
                case 3 -> {
                    System.out.println("IMPRIMIR CONSULTAS DO MÉDICO:");
                    imprimirConsultasMedico();
                }
                case 4 -> {
                    System.out.println("IMPRIMIR CONSULTAS MÉDICO-PACIENTE:");
                    imprimirConsultasMedicoPaciente();
                }
                case 5 -> {
                    System.out.println("IMPRIMIR TODAS AS CONSULTAS:");
                    listarConsultas();
                }
                case 6 -> {
                    System.out.println("LISTAR CONSULTAS:");
                    imprimirUltimaConsulta();
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        } while (opcao != 7);
        System.out.println("Obrigado por usar o sistema!");
        
    }

    public void cadastrarConsulta() {
        ConsultaModel consulta = new ConsultaModel();
        ConsultaControle consultaControle = new ConsultaControle();

        System.out.print("Informe a data da consulta (YYYY-MM-DD): ");
        consulta.setDataConsulta(LocalDate.parse(scanner.nextLine()));
        System.out.print("Informe a data prevista para o parto (YYYY-MM-DD): ");
        consulta.setDataPrevistaParto(LocalDate.parse(scanner.nextLine()));
        System.out.print("Informe a data da última menstruação (YYYY-MM-DD): ");
        consulta.setDataUltimaMenstruacao(LocalDate.parse(scanner.nextLine()));
        System.out.print("Informe o tipo de parto: ");
        consulta.setTipoParto(scanner.nextLine());
        System.out.print("Informe a quantidade de semanas: ");
        consulta.setQtdSemanas(scanner.nextLine());
        System.out.print("Informe o CPF do paciente: ");
        String cpf = scanner.nextLine();
        System.out.print("Informe o CRM do médico: ");
        String crm = scanner.nextLine();
        consulta.setPaciente(PacienteDAO.buscarPaciente(cpf));
        consulta.setMedico(MedicoDAO.buscarMedico(crm));

        boolean cadastrado = consultaControle.cadastrarConsulta(consulta);
        if (cadastrado) {
            System.out.println("Consulta cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar a consulta.");
        }
    }

    public void buscarConsulta() {
        System.out.print("Informe o código da consulta: ");
        int codigoConsulta = scanner.nextInt();
        scanner.nextLine();
        ConsultaControle consultaControle = new ConsultaControle();
        consultaControle.imprimirConsulta(codigoConsulta);
    }

    public void imprimirConsultasMedico() {
        System.out.print("Informe o CRM do médico: ");
        String crm = scanner.nextLine();
        ConsultaControle consultaControle = new ConsultaControle();
        consultaControle.imprimirConsultaMedico(crm);
    }

    public void imprimirConsultasMedicoPaciente() {
        System.out.print("Informe o CPF do paciente: ");
        String cpf = scanner.nextLine();
        System.out.print("Informe o CRM do médico: ");
        String crm = scanner.nextLine();
        ConsultaControle consultaControle = new ConsultaControle();
        consultaControle.imprimirConsultaMedPac(cpf, crm);
    }

    public void listarConsultas() {
        System.out.print("Informe o CPF do paciente: ");
        String cpf = scanner.nextLine();
        ConsultaControle consultaControle = new ConsultaControle();
        consultaControle.listarConsulta(cpf);
    }

    public void imprimirUltimaConsulta() {
        System.out.print("Informe o CPF do paciente: ");
        String cpf = scanner.nextLine();
        ConsultaControle consultaControle = new ConsultaControle();
        consultaControle.imprimirUltimaConsulta(cpf);
    }
}
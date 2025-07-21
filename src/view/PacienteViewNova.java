package view;

import DAO.ConsultaDAO;
import DAO.PacienteDAO;
import controle.PacienteControle;
import java.util.List;
import java.util.Scanner;
import model.ConsultaModel;
import model.PacienteModel;

public class PacienteViewNova {
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenuPaciente() {
        int opcao;

        do {
            System.out.println("=== MENU PACIENTE ===");
            System.out.println("1. Login (dados existentes)");
            System.out.println("2. Cadastrar novo paciente");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    exibirMenuPacienteLogado();
                    break;
                case 2:
                    cadastrarPaciente();
                    break;
                case 3:
                    System.out.println("Saindo do módulo de pacientes...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    private void exibirMenuPacienteLogado() {
        PacienteControle pacienteControle = new PacienteControle();
        
        System.out.println("Digite seu CPF:");
        String cpf = scanner.nextLine();

        PacienteModel paciente = pacienteControle.buscarPaciente(cpf);
        if (paciente != null) {
            exibirMenuPacienteLogado(paciente);
        } else {
            System.out.println("Paciente não encontrado com esse CPF.");
        }
    }
    
    // Método para login direto por email
    public void exibirMenuPacienteLogadoPorEmail(String email) {
        PacienteModel paciente = PacienteDAO.buscarPorEmail(email);
        
        if (paciente != null) {
            System.out.println("=== LOGIN REALIZADO COM SUCESSO ===");
            exibirMenuPacienteLogado(paciente);
        } else {
            System.out.println("Erro: Não foi possível carregar os dados do paciente.");
            System.out.println("Redirecionando para o menu de cadastro...");
            exibirMenuPaciente(); // Volta para o menu de opções
        }
    }
    
    public void exibirMenuPacienteLogado(PacienteModel pacienteLogado) {
        System.out.println("Bem-vindo, " + pacienteLogado.getNomeUsuario() + "!");
        
        int opcao;
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
                case 1: {
                    System.out.println("MEUS DADOS:");
                    System.out.println("Nome: " + pacienteLogado.getNomeUsuario());
                    System.out.println("Email: " + pacienteLogado.getEmail());
                    System.out.println("CPF: " + pacienteLogado.getCpf());
                    System.out.println("Idade: " + pacienteLogado.getIdade());
                    System.out.println("Telefone: " + pacienteLogado.getTelefoneContato());
                    System.out.println("Endereco: " + pacienteLogado.getEndereco());
                    System.out.println("Plano de Saude: " + pacienteLogado.getTipoPlanoSaude());
                    System.out.println("Tipo Sanguineo: " + pacienteLogado.getTipoSanguineo());
                    System.out.println("Alergias: " + pacienteLogado.getAlergias());
                    System.out.println("Gestacoes Anteriores: " + pacienteLogado.getNumGestacoesAnteriores());
                    System.out.println("Vacinas: " + pacienteLogado.getVacinas());
                    System.out.println("Peso: " + pacienteLogado.getPeso() + " kg");
                    System.out.println("Condicoes Pre-existentes: " + pacienteLogado.getCondicoesPreEx());
                    break;
                }
                case 2: {
                    System.out.println("ATUALIZAR DADOS:");
                    PacienteModel paciente = new PacienteModel();
                    paciente.setCpf(pacienteLogado.getCpf());

                    System.out.print("Informe o novo nome: ");
                    paciente.setNomeUsuario(scanner.nextLine());
                    System.out.print("Informe o novo email: ");
                    paciente.setEmail(scanner.nextLine());
                    System.out.print("Informe a nova senha: ");
                    paciente.setSenha(scanner.nextLine());
                    
                    System.out.print("Informe a nova idade: ");
                    try {
                        paciente.setIdade(scanner.nextInt());
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Valor inválido. Usando idade atual.");
                        paciente.setIdade(pacienteLogado.getIdade());
                        scanner.nextLine(); // limpar buffer
                    }
                    System.out.print("Informe o novo telefone de contato: ");
                    paciente.setTelefoneContato(scanner.nextLine());
                    System.out.print("Informe o novo endereço: ");
                    paciente.setEndereco(scanner.nextLine());
                    System.out.print("Informe o novo tipo de plano de saúde: ");
                    paciente.setTipoPlanoSaude(scanner.nextLine());
                    System.out.print("Informe as alergias: ");
                    paciente.setAlergias(scanner.nextLine());
                    
                    System.out.print("Informe o número de gestações anteriores: ");
                    try {
                        paciente.setNumGestacoesAnteriores(scanner.nextInt());
                        scanner.nextLine(); // limpar buffer
                    } catch (Exception e) {
                        System.out.println("Valor inválido. Usando 0 como padrão.");
                        paciente.setNumGestacoesAnteriores(0);
                        scanner.nextLine(); // limpar buffer
                    }
                    
                    System.out.print("Informe as vacinas: ");
                    paciente.setVacinas(scanner.nextLine());
                    
                    System.out.print("Informe o peso (kg): ");
                    try {
                        paciente.setPeso(scanner.nextFloat());
                        scanner.nextLine(); // limpar buffer
                    } catch (Exception e) {
                        System.out.println("Valor inválido. Usando 0.0 como padrão.");
                        paciente.setPeso(0.0f);
                        scanner.nextLine(); // limpar buffer
                    }
                    
                    System.out.print("Informe condições pré-existentes: ");
                    paciente.setCondicoesPreEx(scanner.nextLine());
                    
                    PacienteControle pacienteControle = new PacienteControle();
                    boolean atualizado = pacienteControle.atualizarDadosPaciente(paciente);
                    if (atualizado) {
                        System.out.println("Dados atualizados com sucesso!");
                        // Recarregar os dados do paciente do banco de dados
                        PacienteModel pacienteAtualizado = PacienteDAO.buscarPaciente(pacienteLogado.getCpf());
                        if (pacienteAtualizado != null) {
                            // Atualizar o objeto em memória com os dados do banco
                            pacienteLogado.setNomeUsuario(pacienteAtualizado.getNomeUsuario());
                            pacienteLogado.setEmail(pacienteAtualizado.getEmail());
                            pacienteLogado.setSenha(pacienteAtualizado.getSenha());
                            pacienteLogado.setIdade(pacienteAtualizado.getIdade());
                            pacienteLogado.setTelefoneContato(pacienteAtualizado.getTelefoneContato());
                            pacienteLogado.setEndereco(pacienteAtualizado.getEndereco());
                            pacienteLogado.setTipoPlanoSaude(pacienteAtualizado.getTipoPlanoSaude());
                            pacienteLogado.setAlergias(pacienteAtualizado.getAlergias());
                            pacienteLogado.setNumGestacoesAnteriores(pacienteAtualizado.getNumGestacoesAnteriores());
                            pacienteLogado.setVacinas(pacienteAtualizado.getVacinas());
                            pacienteLogado.setPeso(pacienteAtualizado.getPeso());
                            pacienteLogado.setCondicoesPreEx(pacienteAtualizado.getCondicoesPreEx());
                            System.out.println("Dados recarregados com sucesso!");
                        }
                    } else {
                        System.out.println("Erro ao atualizar os dados.");
                    }
                    break;
                }
                case 3: {
                    System.out.println("HISTÓRICO DE CONSULTAS:");
                    List<ConsultaModel> consultas = ConsultaDAO.buscarConsultasPorPaciente(pacienteLogado.getCpf());
                    if (consultas.isEmpty()) {
                        System.out.println("Nenhuma consulta encontrada.");
                    } else {
                        for (ConsultaModel consulta : consultas) {
                            System.out.println(consulta);
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("IMPRIMIR ÚLTIMA CONSULTA:");
                    System.out.println("ÚLTIMA CONSULTA:");
                    List<ConsultaModel> ultimaConsulta = ConsultaDAO.buscarUltimaConsulta(pacienteLogado.getCpf());
                    if (!ultimaConsulta.isEmpty()) {
                        ConsultaModel consulta = ultimaConsulta.get(0);
                        System.out.println("Consulta: " + consulta.getCodigoConsulta() +
                                ", Data: " + consulta.getDataConsulta() +
                                ", Médico: " + consulta.getMedico().getCrm());
                    } else {
                        System.out.println("Nenhuma consulta encontrada.");
                    }
                    break;
                }
                default: {
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
                }
            }
        } while (opcao != 5);
        System.out.println("Obrigado por usar o sistema!");
    }

    public void cadastrarPaciente() {
        PacienteModel paciente = new PacienteModel();
        PacienteControle pacienteControle = new PacienteControle();

        System.out.println("=== CADASTRO DE PACIENTE ===");
        
        // Dados pessoais básicos
        System.out.print("Informe o nome completo: ");
        String nome = scanner.nextLine().trim();
        paciente.setNomeUsuario(nome);
        
        System.out.print("Informe o email: ");
        String email = scanner.nextLine().trim();
        paciente.setEmail(email);
        
        System.out.print("Informe a senha: ");
        String senha = scanner.nextLine().trim();
        paciente.setSenha(senha);
        
        System.out.print("Informe o CPF: ");
        String cpf = scanner.nextLine().trim();
        paciente.setCpf(cpf);
        
        // Validação básica
        if (nome.isEmpty()) {
            System.out.println("Nome é obrigatório!");
            return;
        }
        if (email.isEmpty()) {
            System.out.println("Email é obrigatório!");
            return;
        }
        if (cpf.isEmpty()) {
            System.out.println("CPF é obrigatório!");
            return;
        }
        
        System.out.print("Informe a idade: ");
        String idadeStr = scanner.nextLine().trim();
        try {
            int idade = Integer.parseInt(idadeStr);
            paciente.setIdade(idade);
        } catch (NumberFormatException e) {
            System.out.println("Idade inválida!");
            return;
        }
        
        System.out.print("Informe o telefone de contato: ");
        paciente.setTelefoneContato(scanner.nextLine().trim());
        
        System.out.print("Informe o endereço: ");
        paciente.setEndereco(scanner.nextLine().trim());
        
        System.out.print("Informe o tipo de plano de saúde: ");
        paciente.setTipoPlanoSaude(scanner.nextLine().trim());
        
        System.out.print("Informe o tipo sanguíneo: ");
        paciente.setTipoSanguineo(scanner.nextLine().trim());
        
        System.out.print("Informe as alergias: ");
        paciente.setAlergias(scanner.nextLine().trim());
        
        System.out.print("Informe o número de gestações anteriores: ");
        String gestacoesStr = scanner.nextLine().trim();
        try {
            int gestacoes = Integer.parseInt(gestacoesStr);
            paciente.setNumGestacoesAnteriores(gestacoes);
        } catch (NumberFormatException e) {
            System.out.println("Número de gestações inválido!");
            return;
        }
        
        System.out.print("Informe as vacinas: ");
        paciente.setVacinas(scanner.nextLine().trim());
        
        System.out.print("Informe o peso (kg): ");
        String pesoStr = scanner.nextLine().trim();
        try {
            if (!pesoStr.isEmpty()) {
                float peso = Float.parseFloat(pesoStr);
                paciente.setPeso(peso);
            }
        } catch (NumberFormatException e) {
            System.out.println("Peso inválido!");
            return;
        }
        
        System.out.print("Informe condições pré-existentes: ");
        paciente.setCondicoesPreEx(scanner.nextLine().trim());

        boolean cadastrado = pacienteControle.cadastrarPaciente(paciente);
        if (cadastrado) {
            System.out.println("Paciente cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar paciente.");
        }
    }
}

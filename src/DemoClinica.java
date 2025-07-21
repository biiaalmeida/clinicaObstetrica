import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import util.ConexaoPostgres;
import view.MedicoView;
import view.PacienteViewNova;
import controle.UsuarioControle;
import model.UsuarioModel;

public class DemoClinica {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== BEM-VINDO AO SISTEMA DA CLINICA OBSTETRICA ===");
        System.out.println("Inicializando sistema...");
        
        // Testar conexao basica
        try (Connection connection = ConexaoPostgres.getConexao()) {
            System.out.println("Conexao estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            return;
        }

        // Iniciar menu principal
        exibirMenuPrincipal();
    }
    
    private static void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1. Fazer Login");
            System.out.println("2. Cadastrar Usuario");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opcao: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer
            
            switch (opcao) {
                case 1:
                    realizarLogin();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        } while (opcao != 3);
        
        System.out.println("Sistema encerrado. Obrigado!");
    }
    
    private static void realizarLogin() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        UsuarioControle usuarioControle = new UsuarioControle();
        UsuarioModel usuario = usuarioControle.realizarLogin(email, senha);
        
        if (usuario != null) {
            System.out.println("Login realizado com sucesso!");
            System.out.println("Bem-vindo(a), " + usuario.getNomeUsuario() + "!");
            
            String tipoUsuario = usuarioControle.verificarTipoUsuario(email);
            System.out.println("Tipo de usuario: " + tipoUsuario);
            
            redirecionarPorTipo(tipoUsuario, usuario);
        } else {
            System.out.println("Email ou senha incorretos!");
        }
    }
    
    private static void redirecionarPorTipo(String tipoUsuario, UsuarioModel usuario) {
        switch (tipoUsuario.toUpperCase()) {
            case "PACIENTE":
                System.out.println("\n=== REDIRECIONANDO PARA AREA DO PACIENTE ===");
                System.out.println("Bem-vindo(a), " + usuario.getNomeUsuario() + "!");
                PacienteViewNova pacienteView = new PacienteViewNova();
                // Ir direto para o menu do paciente logado, passando os dados do usuário
                pacienteView.exibirMenuPacienteLogadoPorEmail(usuario.getEmail());
                break;
                
            case "MEDICO":
                System.out.println("\n=== REDIRECIONANDO PARA AREA DO MEDICO ===");
                System.out.println("Bem-vindo(a), Dr(a). " + usuario.getNomeUsuario() + "!");
                MedicoView medicoView = new MedicoView();
                // Ir direto para o menu do médico logado, passando o email do usuário
                medicoView.exibirMenuMedicoLogadoPorEmail(usuario.getEmail());
                break;
                
            default:
                System.out.println("Tipo de usuario nao reconhecido: " + tipoUsuario);
        }
    }
    
    private static void cadastrarUsuario() {
        System.out.println("\n=== CADASTRO DE USUARIO ===");
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine().trim();
        
        if (nome.isEmpty()) {
            System.out.println("Nome nao pode ser vazio!");
            return;
        }
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        if (email.isEmpty()) {
            System.out.println("Email nao pode ser vazio!");
            return;
        }
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();
        
        if (senha.isEmpty()) {
            System.out.println("Senha nao pode ser vazia!");
            return;
        }
        
        UsuarioControle usuarioControle = new UsuarioControle();
        
        // Identificar automaticamente o tipo pelo email
        String tipoUsuario = usuarioControle.verificarTipoUsuario(email);
        
        if (tipoUsuario.equals("MEDICO")) {
            // Cadastro completo de médico
            System.out.print("CRM: ");
            String crm = scanner.nextLine().trim();
            
            System.out.print("Especialidade: ");
            String especialidade = scanner.nextLine().trim();
            
            boolean sucessoMedico = usuarioControle.cadastrarMedicoCompleto(nome, email, senha, crm, especialidade);
            if (sucessoMedico) {
                System.out.println("Medico cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar medico!");
            }
        } else {
            // Cadastro completo de paciente
            System.out.print("CPF (apenas numeros): ");
            String cpf = scanner.nextLine().trim();
            
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); // limpar buffer
            
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine().trim();
            
            boolean sucessoPaciente = usuarioControle.cadastrarPacienteCompleto(nome, email, senha, cpf, idade, telefone);
            if (sucessoPaciente) {
                System.out.println("Paciente cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar paciente!");
            }
        }
    }
}
import java.util.Scanner;
import java.util.ArrayList;
import java.Medico;
import java.Paciente;
import java.Usuario;

public class MenuSistema {
    private Scanner scanner;

    public MenuSistema() {
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== SISTEMA CLINICA OBSTETRICA ===");

        boolean continuar = true;
        while (continuar) {
            continuar = exibirMenuPrincipal();
        }

        scanner.close();
    }

    private boolean exibirMenuPrincipal() {
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Login");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // consumir quebra de linha

        switch (opcao) {
            case 1:
                menuCadastrarUsuario(); 
                break;
            case 2:
                menuLogin();
                break;
            case 3:
                System.out.println("Encerrando sistema...");
                return false;
            default:
                System.out.println("Opção inválida!");
        }
        System.out.println();
        return true;
    }
    public void menuCadastrarUsuario() {
                    System.out.println("\n=== CADASTRO DE USUÁRIO ===");
                    System.out.print("Digite seu email para identificar o tipo de usuário: ");
                    System.out.println();

                    try {
                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        if (email.contains("@medico")){
                            System.out.println("Cadastro de médico iniciado.");
                            // Implementar lógica de cadastro de médico
                        } else if (email.contains("@paciente")) {
                            System.out.println("Cadastro de paciente iniciado.");
                            // Implementar lógica de cadastro de paciente
                        } else {
                            System.out.println("Tipo de usuário não reconhecido. Por favor, use '@medico' ou '@paciente' no email.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
                        }
                }
     
     private void menuLogin(){
                    System.out.println("\n=== LOGIN ===");
                    System.out.print("Digite seu email: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite sua senha: ");
                    String senha = scanner.nextLine();

                   boolean autenticado = false;
                   for (Usuario usuario : usuarios){
                        if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                            autenticado = true;
                            System.out.println("Login realizado com sucesso!");
                            // Implementar lógica de acesso ao sistema
                            break;
                        }
                   }
                }

    private void cadastrarMedico(String email){
        System.out.println(x:"=== CADASTRO DE MÉDICO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CRM: ");
        String crm = scanner.nextLine();   
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine(); 

        Medico medico = new Medico(nome, email, crm, especialidade);
        usuarios.add(medico);
        System.out.println("Médico cadastrado com sucesso!");
        
    }
    private void CadastrarPaciente(String email){
        System.out.println("Cadastro de paciente iniciado.");
        // Implementar lógica de cadastro de paciente
    }

    private void loginMedico(String email, String senha){
        System.out.println(x:"=== LOGIN MÉDICO ===");
        boolean autenticado = false;
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Medico && usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                autenticado = true;
                System.out.println("Login de médico realizado com sucesso!");
    
            }       
        }
    }
}
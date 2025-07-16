package view;

import controle.UsuarioControle;
import java.util.Scanner;
import model.Usuario;

public class UsuarioView {
    private final Scanner scanner;
    private final UsuarioControle usuarioControle;
    
    public UsuarioView() {
        this.scanner = new Scanner(System.in);
        this.usuarioControle = new UsuarioControle();
    }

    private boolean menuLogin() {
        System.out.println("=== ACESSO AO SISTEMA ===");
        System.out.println("1. Fazer Login");
        System.out.println("2. Cadastrar Usuário");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                fazerLogin();
                break;
            case 2:
                cadastrarUsuario();
                break;
            case 3:
                return false;
            default:
                System.out.println("Opção inválida!\n");
        }
        return true;
    }

    private void fazerLogin() {
        System.out.println("\n=== LOGIN ===");
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        Usuario usuario = usuarioControle.autenticar(email, senha);
        
        if (usuario != null) {
            System.out.println("Login realizado com sucesso!");
            System.out.println("Bem-vindo(a), " + usuario.getNomeUsuario() + "!\n");
        } else {
            System.out.println("Email ou senha incorretos!\n");
        }
    }

    private void cadastrarUsuario() {
        System.out.println("\n=== CADASTRO DE USUÁRIO ===");
        
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine().trim();
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        boolean sucesso = usuarioControle.cadastrar(nome, email, senha);
        
        if (sucesso) {
            System.out.println("Usuário cadastrado com sucesso!\n");
        } else {
            System.out.println("Erro ao cadastrar usuário!\n");
        }
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            continuar = menuLogin();
        }
        System.out.println("Sistema encerrado.");
    }

    public static void main(String[] args) {
        UsuarioView sistema = new UsuarioView();
        sistema.iniciar();
    }
}

package view;

import java.util.Scanner;
import controle.UsuarioControle;
import DAO.UsuarioDAO;
import model.UsuarioModel;

public class UsuarioView {
    private final Scanner scanner;
    private final UsuarioControle usuarioControle;
    private final UsuarioDAO usuarioDAO;

    public UsuarioView() {
        this.scanner = new Scanner(System.in);
        this.usuarioControle = new UsuarioControle();
        this.usuarioDAO = new UsuarioDAO();
    }

    // ===== MÉTODO PRINCIPAL =====
    public void iniciar() {
        System.out.println("=== SISTEMA DE USUÁRIOS ===\n");
        
        boolean continuar = true;
        while (continuar) {
            continuar = menuUsuario();
        }
        
        System.out.println("Sistema encerrado!");
        scanner.close();
    }

    // ===== MENU PRINCIPAL =====
    private boolean menuUsuario() {
        System.out.println("=== MENU USUÁRIO ===");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Autenticar Usuário");
        System.out.println("3. Atualizar Credenciais");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                cadastrarUsuario();
                break;
            case 2:
                autenticarUsuario();
                break;
            case 3:
                atualizarCredenciais();
                break;
            case 4:
                return false;
            default:
                System.out.println("Opção inválida!\n");
        }
        return true;
    }

    // ===== CADASTRAR USUÁRIO =====
    private void cadastrarUsuario() {
        System.out.println("\n=== CADASTRAR USUÁRIO ===");
        
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine().trim();
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        // Chamando método do UsuarioControle
        boolean sucesso = usuarioControle.cadastrar(nome, email, senha);
        
        if (sucesso) {
            System.out.println("Usuário cadastrado com sucesso!\n");
        } else {
            System.out.println("Erro ao cadastrar usuário!\n");
        }
    }

    // ===== AUTENTICAR USUÁRIO =====
    private void autenticarUsuario() {
        System.out.println("\n=== AUTENTICAR USUÁRIO ===");
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        // Chamando método do UsuarioControle
        UsuarioModel usuario = usuarioControle.autenticar(email, senha);
        
        if (usuario != null) {
            System.out.println("Autenticação realizada com sucesso!");
            System.out.println("Bem-vindo(a), " + usuario.getNomeUsuario() + "!\n");
        } else {
            System.out.println("Email ou senha incorretos!\n");
        }
    }

    // ===== ATUALIZAR CREDENCIAIS =====
    private void atualizarCredenciais() {
        System.out.println("\n=== ATUALIZAR CREDENCIAIS ===");
        
        System.out.print("Email atual: ");
        String email = scanner.nextLine().trim();
        
        // Verificar se usuário existe usando UsuarioDAO
        UsuarioModel usuario = usuarioDAO.buscarPorEmail(email);
        
        if (usuario == null) {
            System.out.println("Usuário não encontrado!\n");
            return;
        }
        
        System.out.println("Usuário encontrado: " + usuario.getNomeUsuario());
        
        System.out.print("Senha atual: ");
        String senhaAtual = scanner.nextLine();
        
        // Verificar senha atual usando UsuarioControle
        UsuarioModel autenticado = usuarioControle.autenticar(email, senhaAtual);
        
        if (autenticado == null) {
            System.out.println("Senha atual incorreta!\n");
            return;
        }
        
        System.out.print("Novo nome (ENTER para manter): ");
        String novoNome = scanner.nextLine().trim();
        if (novoNome.isEmpty()) {
            novoNome = usuario.getNomeUsuario();
        }
        
        System.out.print("Nova senha: ");
        String novaSenha = scanner.nextLine();
        
        // Atualizar usando UsuarioControle
        boolean sucessoNome = usuarioControle.atualizarUsuario(email, novoNome);
        boolean sucessoSenha = usuarioControle.alterarSenha(email, senhaAtual, novaSenha);
        
        if (sucessoNome && sucessoSenha) {
            System.out.println("Credenciais atualizadas com sucesso!\n");
        } else {
            System.out.println("Erro ao atualizar credenciais!\n");
        }
    }

    // ===== UTILITÁRIOS =====
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        UsuarioView sistema = new UsuarioView();
        sistema.iniciar();
    }
}
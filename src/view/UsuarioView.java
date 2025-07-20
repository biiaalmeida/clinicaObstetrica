package view;

import controle.UsuarioControle;
import java.util.Scanner;
import model.UsuarioModel;

public class UsuarioView {
    
    public UsuarioModel exibirMenuUsuario() {
        try (Scanner scanner = new Scanner(System.in)) {
            UsuarioControle usuarioControle = new UsuarioControle();
            int opcao;
            
            do {
                System.out.println("=== SISTEMA DE ACESSO ===");
                System.out.println("1. Fazer Login");
                System.out.println("2. Cadastrar Usuário");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcao) {
                    case 1:
                        System.out.println("\n=== LOGIN ===");
                        System.out.print("Email: ");
                        String email = scanner.nextLine().trim();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        
                        UsuarioModel usuario = usuarioControle.autenticar(email, senha);
                        
                        if (usuario != null) {
                            System.out.println("Login realizado com sucesso!");
                            System.out.println("Bem-vindo(a), " + usuario.getNomeUsuario() + "!");
                            String tipoUsuario = usuarioControle.obterTipoUsuario(usuario);
                            System.out.println("Tipo de usuário: " + tipoUsuario);
                            scanner.close();
                            return usuario;
                        } else {
                            System.out.println("Email ou senha incorretos!");
                        }
                        break;

                    case 2:
                        System.out.println("\n=== CADASTRO DE USUÁRIO ===");
                        System.out.print("Nome completo: ");
                        String nome = scanner.nextLine().trim();
                        System.out.print("Email: ");
                        String emailCadastro = scanner.nextLine().trim();
                        System.out.print("Senha: ");
                        String senhaCadastro = scanner.nextLine();
                        
                        boolean sucesso = usuarioControle.cadastrar(nome, emailCadastro, senhaCadastro);
                        
                        if (sucesso) {
                            System.out.println("Usuário cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar usuário!");
                        }
                        break;

                    default:
                        if (opcao != 3) {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                        break;
                }
                
            } while (opcao != 3);
            
            System.out.println("Sistema encerrado!");
        }
        return null; 
    }
}
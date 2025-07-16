import java.util.Scanner;

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
                menuCadastrarUsuario(); // Implementar método de cadastro
                break;
            case 2:
                menuLogin();// Implementar método de login
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
}
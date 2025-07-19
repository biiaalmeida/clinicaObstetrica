package view;
import java.util.Scanner;
import model.MedicoModel;

public class MedicoView {
    public MedicoModel cadastrarMedico() {
        System.out.println("=== CADASTRO DE MÉDICO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CRM: ");
        String crm = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        MedicoModel medico = new MedicoModel(nome, email, crm, especialidade);
        usuarios.add(medico);
        System.out.println("Cadastro de paciente iniciado.");
    }

    private void loginMedico(String email, String senha){
        System.out.println("=== LOGIN MEDICO ===");
        boolean autenticado = false;
        for (Usuario usuario : usuarios) {
            if (usuario instanceof MedicoModel && usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                autenticado = true;
                System.out.println("Login realizado com sucesso!");
            }
        }
    }

    public void exibirMedico(String crm) {
        System.out.println("=== EXIBIR MÉDICO ===");
        for (Usuario usuario : usuarios) {
            if (usuario instanceof MedicoModel && usuario.getCrm().equals(crm)) {
                MedicoModel medico = (MedicoModel) usuario;
                System.out.println("Nome: " + medico.getNome());
                System.out.println("Email: " + medico.getEmail());
                System.out.println("CRM: " + medico.getCrm());
                System.out.println("Especialidade: " + medico.getEspecialidade());
                return;
            }
        }
        System.out.println("Médico não encontrado.");
    }
}
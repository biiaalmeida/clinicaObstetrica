import java.sql.Connection;
import java.sql.SQLException;
import util.ConexaoPostgres;
import view.ConsultaView;
import view.MedicoView;
import view.PacienteView;
import view.UsuarioView;

public class DemoClinica {
    
    public static void main(String[] args) {
        System.out.println("=== BEM-VINDO AO SISTEMA DA CLINICA OBSTETRICA ===");
        System.out.println("Inicializando sistema...\n");
        
        // Testar conexao basica
        try (Connection connection = ConexaoPostgres.getConexao()) {
            System.out.println("Conexao estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }

        // Chamando todas as Views do sistema
        System.out.println("\n Executando todos os modulos do sistema...\n");
        
        // 1. Chamar UsuarioView
        System.out.println("INICIANDO MODULO DE USUARIOS");
        UsuarioView usuarioView = new UsuarioView();
        usuarioView.exibirMenuUsuario();
        
        // 2. Chamar MedicoView
        System.out.println("\n INICIANDO MODULO DE MEDICOS");
        MedicoView medicoView = new MedicoView();
        medicoView.exibirMenuMedico();

        // 3. Chamar Pacienteview
        System.out.println("\n INICIANDO MODULO DE PACIENTES");
        PacienteView pacienteView = new PacienteView();
        pacienteView.exibirMenuPaciente();
        
        // 4. Chamar ConsultaView
        System.out.println("\n INICIANDO MODULO DE CONSULTAS");
        ConsultaView consultaView = new ConsultaView();
        consultaView.menuConsulta();
        
        // Finalizacao
        System.out.println("\n=== SISTEMA ENCERRADO ===");
        System.out.println("Obrigado por usar o Sistema da Clinica Obstetrica!");
    }
    
}
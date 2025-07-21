package controle;
import DAO.MedicoDAO;
import model.MedicoModel;

public class MedicoControle {
    public MedicoModel buscarMedico(String crm) {
        return MedicoDAO.buscarMedico(crm);
    }

    public void imprimirMedico(String crm){
        MedicoModel medico = buscarMedico(crm);
        if (medico != null) {
            System.out.println(medico.toString());
        } else {
            System.out.println("Médico não encontrado.");
        }
    }

    public void imprimirMedicoPorEmail(String email){
        MedicoModel medico = MedicoDAO.buscarMedPorEmail(email);
        if (medico != null) {
            System.out.println(medico.toString());
        } else {
            System.out.println("Médico não encontrado.");
        }
    }

    public boolean atualizarDadosMedico(MedicoModel medico) {
        MedicoDAO dao = new MedicoDAO();
        return dao.editarMedico(medico);
    }

    public boolean cadastrarMedico(MedicoModel medico) {
        MedicoDAO dao = new MedicoDAO();
        return dao.cadastrarMedico(medico);
    }
}
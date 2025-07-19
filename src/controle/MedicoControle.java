package controle;
import DAO.MedicoDAO;
import model.MedicoModel;

public class MedicoControle {
    public MedicoModel buscarMedico(String crm) {
        return MedicoDAO.buscarPorCrm(crm);
    }

    public void imprimirMedico(String crm){
        MedicoModel medico = buscarMedico(crm);
        if (medico != null) {
            System.out.println(medico.toString());
        } else {
            System.out.println("Médico não encontrado.");
        }
    }
}
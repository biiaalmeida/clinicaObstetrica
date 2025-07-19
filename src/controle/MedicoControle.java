package controle;

public class MedicoControle {
    public void imprimirMedico(String crm){
        MedicoControle medico = buscarMedico(crm);
        if (medico != null) {
            System.out.println(medico.toString());
        } else {
            System.out.println("Médico não encontrado.");
        }
    }

}
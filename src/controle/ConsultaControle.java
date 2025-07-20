package controle;

import DAO.ConsultaDAO;
import java.util.List;
import model.ConsultaModel;


public class ConsultaControle {
    public ConsultaModel buscarConsulta(int codigoConsulta) {
        return ConsultaDAO.buscarConsulta(codigoConsulta);
    }

    public void imprimirConsulta(int codigoConsulta) {
        ConsultaModel consulta = buscarConsulta(codigoConsulta);
        if (consulta != null) {
            System.out.println(consulta.toString());
        } else {
            System.out.println("Consulta não encontrada.");
        }
    }

    public void imprimirConsultaMedico(String crm) {
    List<ConsultaModel> consultas = ConsultaDAO.buscarConsultasPorMedico(crm);
    for (ConsultaModel consulta : consultas) {
        System.out.println("Consulta: " + consulta.getCodigoConsulta() +
                ", Data: " + consulta.getDataConsulta() +
                ", Paciente: " + consulta.getPaciente());
    }
}

    public void imprimirConsultaMedPac(String cpf, String crm) {
        List<ConsultaModel> consultas = ConsultaDAO.buscarConsultaMedPac(cpf, crm);
        for (ConsultaModel consulta : consultas) {
            System.out.println("Consulta: " + consulta.getCodigoConsulta() +
                    ", Data: " + consulta.getDataConsulta() +
                    ", Médico: " + consulta.getMedico().getCrm());
        }
    }

    public void imprimirUltimaConsulta(String cpf) {
        List<ConsultaModel> consultas = ConsultaDAO.buscarUltimaConsulta(cpf);
        if (!consultas.isEmpty()) {
            ConsultaModel ultimaConsulta = consultas.get(0);
            System.out.println("Última consulta: " + ultimaConsulta.getCodigoConsulta() +
                    ", Data: " + ultimaConsulta.getDataConsulta() +
                    ", Médico: " + ultimaConsulta.getMedico().getCrm());
        } else {
            System.out.println("Nenhuma consulta encontrada para o paciente com CPF: " + cpf);
        }
    }

    public void listarConsulta(String cpf) {
        List<ConsultaModel> consultas = ConsultaDAO.buscarConsultasPorPaciente(cpf);
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada para o paciente com CPF: " + cpf);
        } else {
            for (ConsultaModel consulta : consultas) {
                System.out.println("Consulta: " + consulta.getCodigoConsulta() +
                        ", Data: " + consulta.getDataConsulta() +
                        ", Médico: " + consulta.getMedico().getCrm());
            }
        }
    }
    public boolean cadastrarConsulta(ConsultaModel consulta) {
        ConsultaDAO dao = new ConsultaDAO();
        return dao.cadastrarConsulta(consulta);
    }
}

package model;


public class Paciente extendes Usuario{
    private String cpf;
    private int idade;
    private String telefoneContato;
    private String tipoPlanoSaude;
    private String tipoSanguineo;
    private String alergias;
    private int numGestacoesAnteriores;
    private String vacinas;
    private Float peso;
    private String condicoesPreEx;

    public Paciente(String nome, String email, String senha, String cpf, int idade, String telefoneContato,String tipoPlanoSaude, String tipoSanguineo, 
    String alergias, int numGestacoesAnteriores, String vacinas, Float peso, String condicoesPreEx) {

        super(nome, email, senha);
        this.cpf = cpf;
        this.idade = idade;
        this.telefoneContato = telefoneContato;
        this.tipoPlanoSaude = tipoPlanoSaude;
        this.tipoSanguineo = tipoSanguineo;
        this.alergias = alergias;
        this.numGestacoesAnteriores = numGestacoesAnteriores;
        this.vacinas = vacinas;
        this.peso = peso;
        this.condicoesPreEx = condicoesPreEx;
    }
 
}

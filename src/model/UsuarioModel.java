package model;

public class UsuarioModel {
    protected String nomeUsuario;
    protected String senha;
    protected String email;

    public UsuarioModel(String nomeUsuario, String senha, String email) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuario() {
        if (email.endsWith("@medico")) {
            return "Medico";
        } else if (email.endsWith("@paciente")) {
            return "Paciente";
        } else {
            throw new IllegalArgumentException("Email deve terminar com @medico ou @paciente");
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nomeUsuario='" + nomeUsuario + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

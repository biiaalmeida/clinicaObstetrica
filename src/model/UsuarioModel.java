package model;

public class Usuario {
    protected String nomeUsuario;
    protected String senha;
    protected String email;

    public Usuario(String nomeUsuario, String senha, String email) {
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

    public boolean autenticar(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

    public Usuario atualizarCredenciais(String novoNomeUsuario, String novaSenha) {
        this.nomeUsuario = novoNomeUsuario;
        this.senha = novaSenha;
        return this;
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

}

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
}

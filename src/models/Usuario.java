package models;

import java.io.Serializable; // Adicione esta importação

public abstract class Usuario implements Serializable { // Adicione implements Serializable
    private static final long serialVersionUID = 1L; // Adicione esta linha (pode ser 1L para começar)
    private int idUsuario;
    private String nome;
    private String email;
    private String username;
    private String senha;

    public Usuario(int idUsuario, String nome, String email, String username, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.senha = senha;
    }

    public abstract void cadastrar();

    public void login(String usuario, String senha){

    };

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
package models;

import java.io.Serializable;

public class Aluno extends Usuario implements Serializable { // Adicione implements Serializable
    private static final long serialVersionUID = 1L; // Adicione esta linha
    private long cpf;
    private String nomeFaculdade;

    public Aluno(int idUsuario, String nome, String email, String username, String senha, long cpf, String nomeFaculdade){
        super(idUsuario,nome, email, username, senha);
        this.cpf = cpf;
        this.nomeFaculdade = nomeFaculdade;
    }

    @Override
    public void cadastrar() {
        System.out.println("Cadastrando aluno...");
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNomeFaculdade() {
        return nomeFaculdade;
    }

    public void setNomeFaculdade(String nomeFaculdade) {
        this.nomeFaculdade = nomeFaculdade;
    }
}
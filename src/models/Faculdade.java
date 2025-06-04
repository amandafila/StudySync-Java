package models;

import java.io.Serializable;

public class Faculdade extends Usuario implements Serializable { // Adicione implements Serializable
    private static final long serialVersionUID = 1L; // Adicione esta linha
    private long cnpj;
    private long cep;
    private long telefone;

    public Faculdade(int idUsuario, String nome, String email, String username, String senha, long cnpj, long cep, long telefone){
        super(idUsuario,nome, email, username, senha);
        this.cnpj = cnpj;
        this.cep = cep;
        this.telefone = telefone;
    }

    @Override
    public void cadastrar() {
        System.out.println("Cadastrando aluno...");
    }

    public long getCnpj() {
        return cnpj;
    }

    public long getCep() {
        return cep;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public void setCep(long cep) {
        this.cep = cep;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
}
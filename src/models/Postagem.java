package models;

import java.io.Serializable;

public abstract class Postagem implements Serializable { // Adicione implements Serializable
    private static final long serialVersionUID = 1L; // Adicione esta linha
    private String titulo;
    private String conteudo;
    private String faculdadeNome;

    public Postagem(String titulo, String conteudo, String faculdadeNome) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.faculdadeNome = faculdadeNome;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getFaculdadeNome() {
        return faculdadeNome;
    }

    // Setters (novos)
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public abstract String getTipo();
}
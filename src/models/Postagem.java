package models;

public abstract class Postagem {
    private String titulo;
    private String conteudo;
    private String faculdadeNome;

    public Postagem(String titulo, String conteudo, String faculdadeNome) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.faculdadeNome = faculdadeNome;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getFaculdadeNome() {
        return faculdadeNome;
    }

    public abstract String getTipo(); // "Publicação" ou "Vaga"
}

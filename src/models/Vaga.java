package models;

public class Vaga extends Postagem {
    private String link;

    public Vaga(String titulo, String conteudo, String faculdadeNome, String link) {
        super(titulo, conteudo, faculdadeNome);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String getTipo() {
        return "Vaga";
    }
}

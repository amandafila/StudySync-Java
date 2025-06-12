package models;

import java.io.Serializable;

public class Vaga extends Postagem implements Serializable {
    private static final long serialVersionUID = 1L;
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
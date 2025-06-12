package models;

import java.io.Serializable;

public class Publicacao extends Postagem implements Serializable {
    private static final long serialVersionUID = 1L;

    public Publicacao(String titulo, String conteudo, String faculdadeNome) {
        super(titulo, conteudo, faculdadeNome);
    }

    @Override
    public String getTipo() {
        return "Publicação";
    }
}
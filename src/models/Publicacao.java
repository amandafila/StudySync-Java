package models;

import java.io.Serializable;

public class Publicacao extends Postagem implements Serializable { // Adicione implements Serializable
    private static final long serialVersionUID = 1L; // Adicione esta linha

    public Publicacao(String titulo, String conteudo, String faculdadeNome) {
        super(titulo, conteudo, faculdadeNome);
    }

    @Override
    public String getTipo() {
        return "Publicação";
    }
}
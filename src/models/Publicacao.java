package models;

public class Publicacao extends Postagem {
    public Publicacao(String titulo, String conteudo, String faculdadeNome) {
        super(titulo, conteudo, faculdadeNome);
    }

    @Override
    public String getTipo() {
        return "Publicação";
    }
}

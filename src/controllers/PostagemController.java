package controllers;

import models.Postagem;
import models.Publicacao;
import models.Vaga;

import java.io.*; // Importe tudo de java.io
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostagemController {

    private static final String POSTAGENS_FILE = "postagens.dat";

    public static List<Postagem> carregarPostagens() {
        List<Postagem> postagens = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(POSTAGENS_FILE))) {
            postagens = (List<Postagem>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de postagens.dat não encontrado. Será criado ao salvar.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar postagens: " + e.getMessage());
        }
        return postagens;
    }

    private static void salvarTodasPostagens(List<Postagem> postagens) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(POSTAGENS_FILE))) {
            oos.writeObject(postagens);
        } catch (IOException e) {
            System.out.println("Erro ao salvar postagens: " + e.getMessage());
        }
    }

    public static void salvarPostagem(Postagem postagem) {
        List<Postagem> postagens = carregarPostagens();

        postagens.removeIf(p ->
                p.getTitulo().trim().equalsIgnoreCase(postagem.getTitulo().trim()) &&
                        p.getFaculdadeNome().trim().equalsIgnoreCase(postagem.getFaculdadeNome().trim())
        );

        postagens.add(postagem);

        salvarTodasPostagens(postagens);
    }

    public static void removerPostagem(Postagem postagem) {
        List<Postagem> postagens = carregarPostagens();
        postagens.removeIf(p -> p.getTitulo().equals(postagem.getTitulo()) && p.getFaculdadeNome().equals(postagem.getFaculdadeNome()));
        salvarTodasPostagens(postagens);
    }

    public static void atualizarPostagem(Postagem postagemAtualizada) {
        salvarPostagem(postagemAtualizada); // Isso vai sobrescrever a postagem existente ou adicionar se for nova.
    }
}
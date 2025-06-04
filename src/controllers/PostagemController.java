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

    // Método auxiliar para carregar lista de postagens
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

    // Método auxiliar para salvar a lista completa de postagens
    private static void salvarTodasPostagens(List<Postagem> postagens) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(POSTAGENS_FILE))) {
            oos.writeObject(postagens);
        } catch (IOException e) {
            System.out.println("Erro ao salvar postagens: " + e.getMessage());
        }
    }

    public static void salvarPostagem(Postagem postagem) {
        List<Postagem> postagens = carregarPostagens();
        // Verifica se a postagem já existe (baseado em título e faculdade nome)
        // Isso é importante para o caso de "atualização" via salvar (como em PostagemController)
        boolean encontrado = false;
        for (int i = 0; i < postagens.size(); i++) {
            Postagem p = postagens.get(i);
            // Sua lógica de identificação de postagem para atualização é pelo título e nome da faculdade.
            // Para IDs únicos, você precisaria de um ID para Postagem.
            if (p.getTitulo().equals(postagem.getTitulo()) && p.getFaculdadeNome().equals(postagem.getFaculdadeNome())) {
                postagens.set(i, postagem);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            postagens.add(postagem);
        }
        salvarTodasPostagens(postagens);
    }

    public static void removerPostagem(Postagem postagem) {
        List<Postagem> postagens = carregarPostagens();
        // Remove a postagem da lista
        postagens.removeIf(p -> p.getTitulo().equals(postagem.getTitulo()) && p.getFaculdadeNome().equals(postagem.getFaculdadeNome()));
        salvarTodasPostagens(postagens);
    }

    // O método atualizarPostagem agora pode ser mais simples, pois salvarPostagem já lida com a atualização
    // Se a postagem atualizada tiver o mesmo título e faculdade, ela será substituída.
    public static void atualizarPostagem(Postagem postagemAtualizada) {
        salvarPostagem(postagemAtualizada); // Isso vai sobrescrever a postagem existente ou adicionar se for nova.
    }
}
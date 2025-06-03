package controllers;

import models.Postagem;
import models.Publicacao;
import models.Vaga;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostagemController {
    public static void salvarPostagem(Postagem postagem) {
        try (FileWriter writer = new FileWriter("postagens.txt", true)) {
            String linha = postagem.getTipo() + ";" +
                    postagem.getTitulo() + ";" +
                    postagem.getConteudo() + ";" +
                    postagem.getFaculdadeNome();

            if (postagem.getTipo().equals("Vaga")) {
                linha += ";" + ((models.Vaga) postagem).getLink();
            }

            writer.write(linha + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar postagem: " + e.getMessage());
        }
    }

    // No PostagemController:
    public static void removerPostagem(Postagem postagem) {
        try {
            // Lê todas as linhas do arquivo
            List<String> linhas = Files.readAllLines(Paths.get("postagens.txt"));

            // Filtra a linha correspondente à postagem a ser removida
            List<String> novasLinhas = linhas.stream()
                    .filter(linha -> {
                        String[] partes = linha.split(";");
                        String tituloArquivo = partes[1];
                        String faculdadeArquivo = partes[3];
                        return !(tituloArquivo.equals(postagem.getTitulo())
                                && faculdadeArquivo.equals(postagem.getFaculdadeNome()));
                    })
                    .collect(Collectors.toList());

            // Reescreve o arquivo sem a linha removida
            Files.write(Paths.get("postagens.txt"), novasLinhas);

        } catch (IOException e) {
            System.out.println("Erro ao excluir postagem: " + e.getMessage());
        }
    }

    // Método para carregar todas as postagens do arquivo
    public static List<Postagem> carregarPostagens() {
        List<Postagem> postagens = new ArrayList<>();
        try {
            List<String> linhas = Files.readAllLines(Paths.get("postagens.txt"));
            for (String linha : linhas) {
                String[] partes = linha.split(";");
                String tipo = partes[0];
                String titulo = partes[1];
                String conteudo = partes[2];
                String faculdadeNome = partes[3];

                if (tipo.equals("Vaga")) {
                    String link = partes[4];
                    postagens.add(new Vaga(titulo, conteudo, faculdadeNome, link));
                } else {
                    postagens.add(new Publicacao(titulo, conteudo, faculdadeNome));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar postagens: " + e.getMessage());
        }
        return postagens;
    }
}
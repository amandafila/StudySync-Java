package controllers;

import models.Aluno;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlunoController {

    public static List<Aluno> carregarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        try {
            List<String> linhas = Files.readAllLines(Paths.get("alunos.txt"));
            for (String linha : linhas) {
                String[] partes = linha.split(";");
                Aluno aluno = new Aluno(
                        Integer.parseInt(partes[0]), // id
                        partes[1], // nome
                        partes[2], // email
                        partes[3], // username
                        partes[4], // senha
                        Long.parseLong(partes[5]), // cpf
                        partes[6]  // faculdade
                );
                alunos.add(aluno);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
        }
        return alunos;
    }

    public static void atualizarAluno(Aluno aluno) {
        try {
            List<String> linhas = Files.readAllLines(Paths.get("alunos.txt"));
            List<String> novasLinhas = new ArrayList<>();

            for (String linha : linhas) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                if (id == aluno.getIdUsuario()) {
                    // Atualiza a linha do aluno editado
                    String novaLinha = String.join(";",
                            String.valueOf(aluno.getIdUsuario()),
                            aluno.getNome(),
                            aluno.getEmail(),
                            aluno.getUsername(),
                            aluno.getSenha(),
                            String.valueOf(aluno.getCpf()),
                            aluno.getNomeFaculdade()
                    );
                    novasLinhas.add(novaLinha);
                } else {
                    novasLinhas.add(linha); // Mantém as outras linhas
                }
            }

            Files.write(Paths.get("alunos.txt"), novasLinhas);

        } catch (IOException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public static void removerAluno(Aluno aluno) {
        try {
            List<String> linhas = Files.readAllLines(Paths.get("alunos.txt"));
            List<String> novasLinhas = linhas.stream()
                    .filter(linha -> {
                        String[] partes = linha.split(";");
                        int id = Integer.parseInt(partes[0]);
                        return id != aluno.getIdUsuario(); // Remove a linha do aluno
                    })
                    .collect(Collectors.toList());

            Files.write(Paths.get("alunos.txt"), novasLinhas);

        } catch (IOException e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }
}

package controllers;

import models.Aluno;

import java.io.*; // Importe tudo de java.io
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlunoController {

    private static final String ALUNOS_FILE = "alunos.dat"; // Novo nome do arquivo

    public static List<Aluno> carregarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ALUNOS_FILE))) {
            // Tenta ler a lista de alunos do arquivo
            alunos = (List<Aluno>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de alunos.dat não encontrado. Criando um novo.");
            // Se o arquivo não existir, retorna uma lista vazia, e ele será criado ao salvar
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
            // Em caso de erro na leitura ou classe não encontrada
        }
        return alunos;
    }

    // Método auxiliar para salvar a lista completa de alunos
    private static void salvarTodosAlunos(List<Aluno> alunos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ALUNOS_FILE))) {
            oos.writeObject(alunos); // Escreve a lista completa de alunos
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    public static void atualizarAluno(Aluno aluno) {
        List<Aluno> alunos = carregarAlunos();
        boolean encontrado = false;
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getIdUsuario() == aluno.getIdUsuario()) {
                alunos.set(i, aluno); // Substitui o aluno atualizado
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            // Se o aluno não foi encontrado (ex: novo cadastro), adicione-o.
            // Para um método de "atualizar", isso pode ser uma lógica separada de "salvar".
            // Mas para o seu fluxo atual, se o ID já existe ele atualiza, senão, não faz nada.
            // Se precisar adicionar, adicione aqui: alunos.add(aluno);
        }
        salvarTodosAlunos(alunos); // Salva a lista atualizada
    }

    public static void removerAluno(Aluno aluno) {
        List<Aluno> alunos = carregarAlunos();
        // Remove o aluno da lista
        alunos.removeIf(a -> a.getIdUsuario() == aluno.getIdUsuario());
        salvarTodosAlunos(alunos); // Salva a lista atualizada
    }
    // No AlunoController.java
    public static void removerAlunosDaFaculdade(String nomeFaculdade) {
        List<Aluno> alunos = carregarAlunos();
        // Remove todos os alunos da faculdade especificada
        alunos.removeIf(a -> a.getNomeFaculdade().equals(nomeFaculdade));
        salvarTodosAlunos(alunos);
    }
}
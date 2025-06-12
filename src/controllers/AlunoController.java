package controllers;

import models.Aluno;

import java.io.*; // Importe tudo de java.io
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlunoController {

    private static final String ALUNOS_FILE = "alunos.dat";

    public static List<Aluno> carregarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ALUNOS_FILE))) {
            alunos = (List<Aluno>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de alunos.dat não encontrado. Criando um novo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
        }
        return alunos;
    }

    // Método para salvar a lista completa de alunos
    private static void salvarTodosAlunos(List<Aluno> alunos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ALUNOS_FILE))) {
            oos.writeObject(alunos);
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
        salvarTodosAlunos(alunos);
    }

    public static void removerAluno(Aluno aluno) {
        List<Aluno> alunos = carregarAlunos();
        alunos.removeIf(a -> a.getIdUsuario() == aluno.getIdUsuario());
        salvarTodosAlunos(alunos);
    }
    public static void removerAlunosDaFaculdade(String nomeFaculdade) {
        List<Aluno> alunos = carregarAlunos();
        alunos.removeIf(a -> a.getNomeFaculdade().equals(nomeFaculdade));
        salvarTodosAlunos(alunos);
    }
}
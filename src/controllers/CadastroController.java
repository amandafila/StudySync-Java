package controllers;

import models.Aluno;
import models.Faculdade;

import java.io.*; // Importe tudo de java.io
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files; // Ainda precisamos para verificar se o arquivo existe e criar se necessário.
import java.nio.file.Paths;

public class CadastroController {

    private static final String ALUNOS_FILE = "alunos.dat";
    private static final String FACULDADES_FILE = "faculdades.dat";

    // Método auxiliar para carregar lista de objetos
    private static <T> List<T> carregarObjetos(String filename) {
        List<T> objetos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            objetos = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + filename + " não encontrado. Será criado ao salvar.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar " + filename + ": " + e.getMessage());
        }
        return objetos;
    }

    // Método auxiliar para salvar lista de objetos
    private static <T> void salvarObjetos(String filename, List<T> objetos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(objetos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar " + filename + ": " + e.getMessage());
        }
    }

    public static void salvarAluno(Aluno aluno) {
        List<Aluno> alunos = carregarObjetos(ALUNOS_FILE);
        boolean encontrado = false;
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getIdUsuario() == aluno.getIdUsuario()) {
                alunos.set(i, aluno); // Atualiza se já existe
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            alunos.add(aluno); // Adiciona se for novo
        }
        salvarObjetos(ALUNOS_FILE, alunos);
    }

    public static void salvarFaculdade(Faculdade faculdade) {
        List<Faculdade> faculdades = carregarObjetos(FACULDADES_FILE);
        boolean encontrado = false;
        for (int i = 0; i < faculdades.size(); i++) {
            if (faculdades.get(i).getIdUsuario() == faculdade.getIdUsuario()) {
                faculdades.set(i, faculdade); // Atualiza se já existe
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            faculdades.add(faculdade); // Adiciona se for nova
        }
        salvarObjetos(FACULDADES_FILE, faculdades);
    }

    public static int gerarProximoIdAluno() {
        List<Aluno> alunos = carregarObjetos(ALUNOS_FILE);
        int maiorId = 0;
        for (Aluno aluno : alunos) {
            if (aluno.getIdUsuario() > maiorId) {
                maiorId = aluno.getIdUsuario();
            }
        }
        return maiorId + 1;
    }

    public static int gerarProximoIdFaculdade() {
        List<Faculdade> faculdades = carregarObjetos(FACULDADES_FILE);
        int maiorId = 0;
        for (Faculdade faculdade : faculdades) {
            if (faculdade.getIdUsuario() > maiorId) {
                maiorId = faculdade.getIdUsuario();
            }
        }
        return maiorId + 1;
    }
    public static List<Faculdade> carregarTodasFaculdades() {
        return carregarObjetos(FACULDADES_FILE);
    }
}
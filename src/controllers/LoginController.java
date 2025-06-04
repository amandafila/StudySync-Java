package controllers;

import models.Aluno;
import models.Faculdade;
import models.Usuario; // Importe Usuario para o cast

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    private static final String ALUNOS_FILE = "alunos.dat";
    private static final String FACULDADES_FILE = "faculdades.dat";

    // Método auxiliar para carregar lista de objetos
    private static <T> List<T> carregarObjetos(String filename) {
        List<T> objetos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            objetos = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + filename + " não encontrado. Retornando lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar " + filename + ": " + e.getMessage());
        }
        return objetos;
    }

    public static Object autenticar(String tipo, String username, String senha) {
        if (tipo.equalsIgnoreCase("Aluno")) {
            List<Aluno> alunos = carregarObjetos(ALUNOS_FILE);
            for (Aluno aluno : alunos) {
                if (aluno.getUsername().equals(username) && aluno.getSenha().equals(senha)) {
                    return aluno;
                }
            }
        } else if (tipo.equalsIgnoreCase("Faculdade")) {
            List<Faculdade> faculdades = carregarObjetos(FACULDADES_FILE);
            for (Faculdade faculdade : faculdades) {
                if (faculdade.getUsername().equals(username) && faculdade.getSenha().equals(senha)) {
                    return faculdade;
                }
            }
        }
        return null;
    }
}
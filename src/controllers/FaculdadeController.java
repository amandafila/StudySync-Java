package controllers;

import models.Faculdade;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class FaculdadeController {
    private static final String ARQUIVO_FACULDADES = "faculdades.dat";
    private static List<Faculdade> faculdades = carregarFaculdades();

    private static List<Faculdade> carregarFaculdades() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_FACULDADES))) {
            return (List<Faculdade>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void salvarFaculdades() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_FACULDADES))) {
            oos.writeObject(faculdades);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void adicionarFaculdade(Faculdade faculdade) {
        if (faculdade == null) {
            throw new IllegalArgumentException("Faculdade não pode ser nula");
        }
        faculdades.add(faculdade);
        salvarFaculdades();
    }

    public static void removerFaculdade(Faculdade faculdade) {
        if (faculdade == null) {
            throw new IllegalArgumentException("Faculdade não pode ser nula");
        }
        faculdades.removeIf(f -> f.getCnpj() == faculdade.getCnpj());
        salvarFaculdades(); // Salva as alterações no arquivo
    }

    public static void atualizarFaculdade(Faculdade faculdadeAtualizada) {
        if (faculdadeAtualizada == null) {
            throw new IllegalArgumentException("Faculdade não pode ser nula");
        }

        for (Faculdade f : faculdades) {
            if (f.getCnpj() == faculdadeAtualizada.getCnpj()) {
                f.setNome(faculdadeAtualizada.getNome());
                f.setEmail(faculdadeAtualizada.getEmail());
                f.setUsername(faculdadeAtualizada.getUsername());
                f.setSenha(faculdadeAtualizada.getSenha());
                // Atualize outros campos conforme necessário
                salvarFaculdades();
                return;
            }
        }

        throw new IllegalArgumentException("Faculdade não encontrada para atualização");
    }

    // Restante dos métodos permanece o mesmo
}
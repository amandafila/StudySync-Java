package controllers;

import models.Aluno;
import models.Faculdade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    public static Object autenticar(String tipo, String username, String senha) {
        String arquivo = tipo.equalsIgnoreCase("Aluno") ? "alunos.txt" : "faculdades.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");

                String user = partes[3];
                String pass = partes[4];

                if (user.equals(username) && pass.equals(senha)) {
                    if (tipo.equalsIgnoreCase("Aluno")) {
                        return new Aluno(
                                Integer.parseInt(partes[0]),
                                partes[1], partes[2], user, pass,
                                Long.parseLong(partes[5]),
                                partes[6] // nome da faculdade
                        );
                    } else {
                        return new Faculdade(
                                Integer.parseInt(partes[0]),    // id
                                partes[1],                      // nome
                                partes[2],                      // email
                                user,                           // username
                                pass,                           // senha
                                Long.parseLong(partes[5]),      // cnpj
                                Long.parseLong(partes[6]),      // cep
                                Long.parseLong(partes[7])       // telefone
                        );
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao autenticar: " + e.getMessage());
        }

        return null;
    }
}

//os comentários aqui são meus (Amanda), não do chatgpt. De preferência só apagar no final para td mundo saber oq está acontecendo
package controllers;

import models.Aluno;
import models.Faculdade;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CadastroController {
//classe com métodos static pq pode ser chamado sem criar um objeto da classe

    public static void salvarAluno(Aluno aluno) {
        try {
            FileWriter writer = new FileWriter("alunos.txt", true);
            //está criando um objeto de filewriter chamado writer que escreve no arquivo alunos.txt
            //o true é para adicionar contúdo novo sem apagar o que já está salvo lá

            writer.write(aluno.getIdUsuario() + ";" +
                    aluno.getNome() + ";" +
                    aluno.getEmail() + ";" +
                    aluno.getUsername() + ";" +
                    aluno.getSenha() + ";" +
                    aluno.getCpf() + ";" +
                    aluno.getNomeFaculdade() + "\n");
            //escreve no arquivo as informações passadas e as separa com ponto e vírgula
            writer.close();
            //fecha o arquivo depois de escrever para salvar os dados direito
        } catch (IOException e) {
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
        }
    }

    public static void salvarFaculdade(Faculdade faculdade) {
        //mesma coisa, só que com faculdade
        try {
            FileWriter writer = new FileWriter("faculdades.txt", true);
            writer.write(faculdade.getIdUsuario() + ";" +
                    faculdade.getNome() + ";" +
                    faculdade.getEmail() + ";" +
                    faculdade.getUsername() + ";" +
                    faculdade.getSenha() + ";" +
                    faculdade.getCnpj() + ";" +
                    faculdade.getCep() + ";" +
                    faculdade.getTelefone() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar faculdade: " + e.getMessage());
        }
    }
    public static int gerarProximoIdAluno() {
        int maiorId = 0;
        try {
            List<String> linhas = Files.readAllLines(Paths.get("alunos.txt"));
            for (String linha : linhas) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                if (id > maiorId) {
                    maiorId = id;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de alunos: " + e.getMessage());
        }
        return maiorId + 1;
    }

    public static int gerarProximoIdFaculdade() {
        int maiorId = 0;
        try {
            List<String> linhas = Files.readAllLines(Paths.get("faculdades.txt"));
            for (String linha : linhas) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                if (id > maiorId) {
                    maiorId = id;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de alunos: " + e.getMessage());
        }
        return maiorId + 1;
    }

}
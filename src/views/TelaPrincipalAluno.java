package views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Aluno;

public class TelaPrincipalAluno {
    private Aluno aluno;

    public TelaPrincipalAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void start(Stage stage) {
        stage.setTitle("Bem-vindo, " + aluno.getNome() + "!");

        Label lblInfo = new Label(
                "Nome: " + aluno.getNome() + "\n" +
                        "Email: " + aluno.getEmail() + "\n" +
                        "Faculdade: " + aluno.getNomeFaculdade()
        );

        Button btnVerVagas = new Button("Ver Vagas Disponíveis");
        Button btnVerPublicacoes = new Button("Ver Publicações");
        Button btnSair = new Button("Sair");

        btnVerVagas.setOnAction(e -> {
            stage.close();
           // new TelaVisualizarVagasAluno(aluno).start(new Stage()); // (Precisa implementar essa tela)
        });

        btnVerPublicacoes.setOnAction(e -> {
            stage.close();
            new TelaVisualizarPublicacoesAluno(aluno).start(new Stage()); // (Precisa implementar essa tela)
        });

        btnSair.setOnAction(e -> {
            stage.close();
            new TelaLogin("Aluno").start(new Stage());
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(lblInfo, btnVerVagas, btnVerPublicacoes, btnSair);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}
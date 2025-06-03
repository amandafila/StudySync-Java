package views;

import controllers.PostagemController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Aluno;
import models.Postagem;

import java.util.List;

public class TelaVisualizarPublicacoesAluno {
    private Aluno aluno;

    public TelaVisualizarPublicacoesAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void start(Stage stage) {
        stage.setTitle("Publicações - " + aluno.getNomeFaculdade());

        // Cabeçalho
        Label lblHeader = new Label("📚 Publicações da faculdade: " + aluno.getNomeFaculdade());
        lblHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 18; -fx-padding: 0 0 10 0;");

        // Área das publicações
        VBox postagensBox = new VBox(15); // Espaço entre publicações
        postagensBox.setPadding(new Insets(10));

        List<Postagem> publicacoes = PostagemController.carregarPostagens()
                .stream()
                .filter(p -> p.getFaculdadeNome().equals(aluno.getNomeFaculdade()))
                .toList();

        for (Postagem postagem : publicacoes) {
            VBox card = new VBox(5);
            card.setPadding(new Insets(10));
            card.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #cccccc;");

            Label lblTipo = new Label(postagem.getTipo().toUpperCase());
            lblTipo.setStyle("-fx-text-fill: white; -fx-background-color: #007bff; -fx-padding: 2 6 2 6; -fx-background-radius: 4;");

            Label lblTitulo = new Label(postagem.getTitulo());
            lblTitulo.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

            Label lblConteudo = new Label(postagem.getConteudo());
            lblConteudo.setWrapText(true);

            card.getChildren().addAll(lblTipo, lblTitulo, lblConteudo);
            postagensBox.getChildren().add(card);
        }

        // Scroll
        ScrollPane scrollPane = new ScrollPane(postagensBox);
        scrollPane.setFitToWidth(true);

        // Botão Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            stage.close();
            new TelaPrincipalAluno(aluno).start(new Stage());
        });

        VBox layout = new VBox(20, lblHeader, scrollPane, btnVoltar);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.show();
    }
}

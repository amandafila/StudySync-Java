package views;

import controllers.PostagemController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Aluno;
import models.Postagem;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;

public class TelaVisualizarVagasAluno {
    private Aluno aluno;

    public TelaVisualizarVagasAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void start(Stage stage) {
        stage.setTitle("Vagas - " + aluno.getNomeFaculdade());

        Label lblHeader = new Label("🔍 Vagas disponíveis na sua faculdade: " + aluno.getNomeFaculdade());
        lblHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 18; -fx-padding: 0 0 10 0;");

        VBox vagasBox = new VBox(15);
        vagasBox.setPadding(new Insets(10));

        List<Postagem> vagas = PostagemController.carregarPostagens()
                .stream()
                .filter(p -> p.getFaculdadeNome().equals(aluno.getNomeFaculdade()))
                .filter(p -> p.getTipo().equalsIgnoreCase("vaga"))
                .toList();

        for (Postagem vaga : vagas) {
            VBox card = new VBox(5);
            card.setPadding(new Insets(10));
            card.setStyle("-fx-background-color: #e6ffe6; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #99cc99;");

            Label lblTipo = new Label("VAGA");
            lblTipo.setStyle("-fx-text-fill: white; -fx-background-color: #28a745; -fx-padding: 2 6 2 6; -fx-background-radius: 4;");

            Label lblTitulo = new Label(vaga.getTitulo());
            lblTitulo.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

            final String url = (vaga instanceof models.Vaga) ? ((models.Vaga) vaga).getLink() : null;

            Hyperlink link = new Hyperlink();
            if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                link.setText(url);
                link.setOnAction(e -> abrirNavegador(url));
            } else {
                link.setText("(Link indisponível)");
                link.setDisable(true);
            }

            card.getChildren().addAll(lblTipo, lblTitulo, link);
            vagasBox.getChildren().add(card);
        }

        ScrollPane scrollPane = new ScrollPane(vagasBox);
        scrollPane.setFitToWidth(true);

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

    private void abrirNavegador(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possível abrir o link");
            alert.setContentText("URL inválida ou navegador não suportado.");
            alert.showAndWait();
        }
    }
}

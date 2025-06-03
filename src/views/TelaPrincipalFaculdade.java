package views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Faculdade;

public class TelaPrincipalFaculdade {
    private Faculdade faculdade;

    public TelaPrincipalFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
    }

    public void start(Stage stage) {
        stage.setTitle("Bem-vinda, " + faculdade.getNome() + "!");

        Label lblInfo = new Label(
                "Nome: " + faculdade.getNome() + "\n" +
                        "Email: " + faculdade.getEmail() + "\n" +
                        "CNPJ: " + faculdade.getCnpj()
        );

        Button btnPostar = new Button("Fazer Postagem");
        Button btnVerAlunos = new Button("Visualizar Alunos");
        Button btnVerPublicacoes = new Button("Visualizar Publicações");
        Button btnSair = new Button("Sair");

        btnPostar.setOnAction(e -> {
            stage.close();
            new TelaPostagem(faculdade).start(new Stage());
        });

        btnVerAlunos.setOnAction(e -> {
            stage.close();
            new TelaVisualizarAlunos(faculdade).start(new Stage());
        });

        btnVerPublicacoes.setOnAction(e -> {
            stage.close();
            new TelaVisualizarPublicacoes(faculdade).start(new Stage());
        });

        btnSair.setOnAction(e -> {
            stage.close();
            new TelaEscolhaTipo().start(new Stage());
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(lblInfo, btnPostar, btnVerAlunos, btnVerPublicacoes, btnSair);

        Scene scene = new Scene(layout, 450, 350);
        stage.setScene(scene);
        stage.show();
    }
}
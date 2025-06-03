package views;

import controllers.PostagemController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Faculdade;
import models.Publicacao;
import models.Vaga;

public class TelaPostagem {
    private Faculdade faculdade;

    public TelaPostagem(Faculdade faculdade) {
        this.faculdade = faculdade;
    }

    public void start(Stage stage) {
        stage.setTitle("Nova Postagem - " + faculdade.getNome());

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        // Seleção do tipo de postagem
        Label lblTipo = new Label("Tipo:");
        ComboBox<String> comboTipo = new ComboBox<>();
        comboTipo.getItems().addAll("Publicação", "Vaga");
        comboTipo.setValue("Publicação");

        // Campos comuns
        Label lblTitulo = new Label("Título:");
        TextField txtTitulo = new TextField();

        Label lblConteudo = new Label("Conteúdo:");
        TextArea txtConteudo = new TextArea();
        txtConteudo.setPrefRowCount(3);

        // Campo específico para vagas
        Label lblLink = new Label("Link da Vaga:");
        TextField txtLink = new TextField();
        lblLink.setVisible(false);
        txtLink.setVisible(false);

        // Mostra/oculta o campo de link conforme o tipo selecionado
        comboTipo.setOnAction(e -> {
            boolean isVaga = comboTipo.getValue().equals("Vaga");
            lblLink.setVisible(isVaga);
            txtLink.setVisible(isVaga);
        });

        Button btnPostar = new Button("Postar");
        Button btnVoltar = new Button("Voltar");

        // Posicionamento no grid
        grid.add(lblTipo, 0, 0);
        grid.add(comboTipo, 1, 0);
        grid.add(lblTitulo, 0, 1);
        grid.add(txtTitulo, 1, 1);
        grid.add(lblConteudo, 0, 2);
        grid.add(txtConteudo, 1, 2);
        grid.add(lblLink, 0, 3);
        grid.add(txtLink, 1, 3);
        grid.add(btnPostar, 1, 4);
        grid.add(btnVoltar, 1, 5);

        // Ação do botão "Postar"
        btnPostar.setOnAction(e -> {
            String tipo = comboTipo.getValue();
            String titulo = txtTitulo.getText();
            String conteudo = txtConteudo.getText();

            if (titulo.isEmpty() || conteudo.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Preencha todos os campos!");
                alert.showAndWait();
                return;
            }

            if (tipo.equals("Vaga")) {
                String link = txtLink.getText();
                if (link.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("O link da vaga é obrigatório!");
                    alert.showAndWait();
                    return;
                }
                Vaga vaga = new Vaga(titulo, conteudo, faculdade.getNome(), link);
                PostagemController.salvarPostagem(vaga);
            } else {
                Publicacao pub = new Publicacao(titulo, conteudo, faculdade.getNome());
                PostagemController.salvarPostagem(pub);
            }

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Sucesso");
            success.setContentText("Postagem criada com sucesso!");
            success.showAndWait();

            stage.close();
            new TelaPrincipalFaculdade(faculdade).start(new Stage());
        });

        // Ação do botão "Voltar"
        btnVoltar.setOnAction(e -> {
            stage.close();
            new TelaPrincipalFaculdade(faculdade).start(new Stage());
        });

        Scene scene = new Scene(grid, 650, 400);
        stage.setScene(scene);
        stage.show();
    }
}
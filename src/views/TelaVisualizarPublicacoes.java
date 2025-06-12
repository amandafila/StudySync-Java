package views;

import controllers.PostagemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Faculdade;
import models.Postagem;

import java.util.List;
import java.util.stream.Collectors;

public class TelaVisualizarPublicacoes {
    private Faculdade faculdade;
    private TableView<Postagem> tabela;

    public TelaVisualizarPublicacoes(Faculdade faculdade) {
        this.faculdade = faculdade;
    }

    public void start(Stage stage) {
        stage.setTitle("Publicações da " + faculdade.getNome());

        tabela = new TableView<>();
        tabela.setEditable(true);

        ObservableList<Postagem> postagens = FXCollections.observableArrayList(
                PostagemController.carregarPostagens()
                        .stream()
                        .filter(p -> p.getFaculdadeNome().equals(faculdade.getNome()))
                        .collect(Collectors.toList())
        );

        TableColumn<Postagem, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<Postagem, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colTitulo.setCellFactory(TextFieldTableCell.forTableColumn());
        colTitulo.setOnEditCommit(e -> {
            Postagem postagem = e.getRowValue();
            postagem.setTitulo(e.getNewValue());
            PostagemController.atualizarPostagem(postagem);
        });

        TableColumn<Postagem, String> colConteudo = new TableColumn<>("Conteúdo");
        colConteudo.setCellValueFactory(new PropertyValueFactory<>("conteudo"));
        colConteudo.setCellFactory(TextFieldTableCell.forTableColumn());
        colConteudo.setOnEditCommit(e -> {
            Postagem postagem = e.getRowValue();
            postagem.setConteudo(e.getNewValue());
            PostagemController.atualizarPostagem(postagem);
        });

        tabela.getColumns().addAll(colTipo, colTitulo, colConteudo);
        tabela.setItems(postagens);

        Button btnExcluir = new Button("Excluir Postagem");
        btnExcluir.setOnAction(e -> {
            Postagem selecionada = tabela.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                postagens.remove(selecionada);
                PostagemController.removerPostagem(selecionada);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Selecione uma postagem para excluir!");
                alert.showAndWait();
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            stage.close();
            new TelaPrincipalFaculdade(faculdade).start(new Stage());
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(tabela, btnExcluir, btnVoltar);

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
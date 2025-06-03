package views;

import controllers.AlunoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Aluno;
import models.Faculdade;

import java.util.List;
import java.util.stream.Collectors;

public class TelaVisualizarAlunos {
    private Faculdade faculdade;

    public TelaVisualizarAlunos(Faculdade faculdade) {
        this.faculdade = faculdade;
    }

    public void start(Stage stage) {
        stage.setTitle("Alunos da " + faculdade.getNome());

        // Tabela de alunos (editável)
        TableView<Aluno> tabela = new TableView<>();
        tabela.setEditable(true); // Permite edição

        // Carrega alunos filtrados por faculdade
        ObservableList<Aluno> alunos = FXCollections.observableArrayList(
                AlunoController.carregarAlunos()
                        .stream()
                        .filter(a -> a.getNomeFaculdade().equals(faculdade.getNome()))
                        .collect(Collectors.toList())
        );

        // Colunas da tabela
        TableColumn<Aluno, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNome.setCellFactory(TextFieldTableCell.forTableColumn()); // Torna editável
        colNome.setOnEditCommit(e -> {
            Aluno aluno = e.getRowValue();
            aluno.setNome(e.getNewValue()); // Atualiza o objeto
            AlunoController.atualizarAluno(aluno); // Salva no arquivo
        });

        TableColumn<Aluno, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(e -> {
            Aluno aluno = e.getRowValue();
            aluno.setEmail(e.getNewValue());
            AlunoController.atualizarAluno(aluno);
        });

        TableColumn<Aluno, Long> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        TableColumn<Aluno, String> colUser = new TableColumn<>("Usuário");
        colUser.setCellValueFactory(new PropertyValueFactory<>("username"));
        colUser.setCellFactory(TextFieldTableCell.forTableColumn()); // Torna editável
        colUser.setOnEditCommit(e -> {
            Aluno aluno = e.getRowValue();
            aluno.setUsername(e.getNewValue()); // Atualiza o objeto
            AlunoController.atualizarAluno(aluno); // Salva no arquivo
        });


        tabela.getColumns().addAll(colNome, colEmail, colCpf, colUser);
        tabela.setItems(alunos);

        // Botões
        Button btnExcluir = new Button("Excluir Aluno");
        btnExcluir.setOnAction(e -> {
            Aluno selecionado = tabela.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                alunos.remove(selecionado);
                AlunoController.removerAluno(selecionado);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Selecione um aluno para excluir!");
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
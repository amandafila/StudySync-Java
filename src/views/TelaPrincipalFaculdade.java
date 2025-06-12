package views;

import controllers.FaculdadeController;
import controllers.AlunoController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Faculdade;
import models.Aluno;
import java.util.List;

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
                        "CNPJ: " + faculdade.getCnpj() + "\n" +
                        "CEP: " + faculdade.getCep()
        );
        lblInfo.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");

        Button btnPostar = new Button("Fazer Postagem");
        btnPostar.setStyle(
                "-fx-background-color: #006494;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        btnPostar.setPrefWidth(200);

        Button btnVerAlunos = new Button("Visualizar Alunos");
        btnVerAlunos.setStyle(
                "-fx-background-color: #006494;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        btnVerAlunos.setPrefWidth(200);

        Button btnVerPublicacoes = new Button("Visualizar Postagem");
        btnVerPublicacoes.setStyle(
                "-fx-background-color: #006494;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        btnVerPublicacoes.setPrefWidth(200);

        Button btnEditar = new Button("Editar Informações");
        btnEditar.setStyle(
                "-fx-background-color: #006494;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        btnEditar.setPrefWidth(200);

        Button btnMudarSenha = new Button("Mudar Senha");
        btnMudarSenha.setStyle(
                "-fx-background-color: #006494;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        btnMudarSenha.setPrefWidth(200);

        Button btnDeletarFaculdade = new Button("Deletar Faculdade");
        btnDeletarFaculdade.setPrefWidth(200);

        Button btnSair = new Button("Sair");
        btnSair.setStyle(
                "-fx-background-color: #006494;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        btnSair.setPrefWidth(200);

        btnDeletarFaculdade.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white;");
        btnDeletarFaculdade.setOnAction(e -> {
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacao.setTitle("Confirmação de Exclusão");
            confirmacao.setHeaderText("Tem certeza que deseja deletar esta faculdade?");
            confirmacao.setContentText("Esta ação é irreversível e todos os dados da faculdade e seus alunos serão perdidos.");

            confirmacao.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    AlunoController.removerAlunosDaFaculdade(faculdade.getNome());
                    FaculdadeController.removerFaculdade(faculdade);
                    stage.close();
                    new TelaEscolhaTipo().start(new Stage());

                    Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                    sucesso.setTitle("Faculdade Deletada");
                    sucesso.setHeaderText(null);
                    sucesso.setContentText("A faculdade e todos os alunos associados foram deletados com sucesso.");
                    sucesso.showAndWait();
                }
            });
        });

        btnEditar.setOnAction(e -> {
            Dialog<Faculdade> dialog = new Dialog<>();
            dialog.setTitle("Editar Informações");
            dialog.setHeaderText("Edite suas informações:");

            ButtonType salvarButtonType = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(salvarButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nomeField = new TextField(faculdade.getNome());
            TextField emailField = new TextField(faculdade.getEmail());
            TextField usuarioField = new TextField(faculdade.getUsername());


            grid.add(new Label("Nome:"), 0, 0);
            grid.add(nomeField, 1, 0);
            grid.add(new Label("Email:"), 0, 1);
            grid.add(emailField, 1, 1);
            grid.add(new Label("Usuário:"), 0, 2);
            grid.add(usuarioField, 1, 2);


            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == salvarButtonType) {
                    faculdade.setNome(nomeField.getText());
                    faculdade.setEmail(emailField.getText());
                    faculdade.setUsername(usuarioField.getText());
                    FaculdadeController.atualizarFaculdade(faculdade);
                    return faculdade;
                }
                return null;
            });

            dialog.showAndWait().ifPresent(result -> {
                lblInfo.setText(
                        "Nome: " + faculdade.getNome() + "\n" +
                                "Email: " + faculdade.getEmail() + "\n" +
                                "CNPJ: " + faculdade.getCnpj() + "\n" +
                                "CEP: " + faculdade.getCep()
                );
                stage.setTitle("Bem-vinda, " + faculdade.getNome() + "!");
            });
        });

        btnMudarSenha.setOnAction(e -> {
            Dialog<Boolean> dialog = new Dialog<>();
            dialog.setTitle("Mudar Senha");
            dialog.setHeaderText("Digite sua senha atual e a nova senha:");

            ButtonType confirmarButtonType = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(confirmarButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            PasswordField senhaAtualField = new PasswordField();
            PasswordField novaSenhaField = new PasswordField();
            PasswordField confirmarSenhaField = new PasswordField();

            grid.add(new Label("Senha Atual:"), 0, 0);
            grid.add(senhaAtualField, 1, 0);
            grid.add(new Label("Nova Senha:"), 0, 1);
            grid.add(novaSenhaField, 1, 1);
            grid.add(new Label("Confirmar Nova Senha:"), 0, 2);
            grid.add(confirmarSenhaField, 1, 2);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == confirmarButtonType) {
                    if (!senhaAtualField.getText().equals(faculdade.getSenha())) {
                        mostrarAlerta("Senha atual incorreta!");
                        return false;
                    }

                    if (!novaSenhaField.getText().equals(confirmarSenhaField.getText())) {
                        mostrarAlerta("As novas senhas não coincidem!");
                        return false;
                    }

                    if (novaSenhaField.getText().equals(senhaAtualField.getText())) {
                        mostrarAlerta("A nova senha deve ser diferente da atual!");
                        return false;
                    }

                    faculdade.setSenha(novaSenhaField.getText());
                    FaculdadeController.atualizarFaculdade(faculdade);
                    mostrarAlerta("Senha alterada com sucesso!", Alert.AlertType.INFORMATION);
                    return true;
                }
                return false;
            });

            dialog.showAndWait();
        });

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
        layout.getChildren().addAll(lblInfo, btnPostar, btnVerAlunos, btnVerPublicacoes,
                btnEditar, btnMudarSenha, btnDeletarFaculdade, btnSair);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #13293D;");

        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(String mensagem) {
        mostrarAlerta(mensagem, Alert.AlertType.ERROR);
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
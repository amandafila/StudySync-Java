package views;

import controllers.AlunoController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
        lblInfo.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");

        Button btnVerVagas = new Button("Ver Vagas Disponíveis");
        btnVerVagas.setStyle(
                "-fx-background-color: #006494;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        btnVerVagas.setPrefWidth(200);
        Button btnVerPublicacoes = new Button("Ver Publicações");
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

        Button btnDeletar = new Button("Deletar-me");
        btnDeletar.setPrefWidth(200);// Novo botão
        Button btnSair = new Button("Sair");
        btnSair.setStyle(
                "-fx-background-color: #006494;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        btnSair.setPrefWidth(200);

        // Configuração do botão Deletar-me
        btnDeletar.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white;");
        btnDeletar.setOnAction(e -> {
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacao.setTitle("Confirmação de Exclusão");
            confirmacao.setHeaderText("Tem certeza que deseja deletar sua conta?");
            confirmacao.setContentText("Esta ação é irreversível e todos seus dados serão perdidos.");

            confirmacao.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    AlunoController.removerAluno(aluno);

                    stage.close();

                    new TelaLogin("Aluno").start(new Stage());

                    Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                    sucesso.setTitle("Conta Deletada");
                    sucesso.setHeaderText(null);
                    sucesso.setContentText("Sua conta foi deletada com sucesso.");
                    sucesso.showAndWait();
                }
            });
        });

        btnVerVagas.setOnAction(e -> {
            stage.close();
            new TelaVisualizarVagasAluno(aluno).start(new Stage());
        });

        btnVerPublicacoes.setOnAction(e -> {
            stage.close();
            new TelaVisualizarPublicacoesAluno(aluno).start(new Stage());
        });

        btnEditar.setOnAction(e -> {
            Dialog<Aluno> dialog = new Dialog<>();
            dialog.setTitle("Editar Informações");
            dialog.setHeaderText("Edite suas informações:");

            ButtonType salvarButtonType = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(salvarButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nomeField = new TextField(aluno.getNome());
            TextField emailField = new TextField(aluno.getEmail());
            TextField usernameField = new TextField(aluno.getUsername());

            grid.add(new Label("Nome:"), 0, 0);
            grid.add(nomeField, 1, 0);

            grid.add(new Label("Email:"), 0, 1);
            grid.add(emailField, 1, 1);

            grid.add(new Label("Usuário:"), 0, 2);
            grid.add(usernameField, 1, 2);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == salvarButtonType) {
                    aluno.setNome(nomeField.getText());
                    aluno.setEmail(emailField.getText());
                    aluno.setUsername(usernameField.getText());
                    AlunoController.atualizarAluno(aluno);
                    return aluno;
                }
                return null;
            });

            dialog.showAndWait().ifPresent(result -> {
                lblInfo.setText(
                        "Nome: " + aluno.getNome() + "\n" +
                                "Email: " + aluno.getEmail() + "\n" +
                                "Faculdade: " + aluno.getNomeFaculdade()
                );
                stage.setTitle("Bem-vindo, " + aluno.getNome() + "!");
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
                    if (!senhaAtualField.getText().equals(aluno.getSenha())) {
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

                    aluno.setSenha(novaSenhaField.getText());
                    AlunoController.atualizarAluno(aluno);
                    mostrarAlerta("Senha alterada com sucesso!", Alert.AlertType.INFORMATION);
                    return true;
                }
                return false;
            });

            dialog.showAndWait();
        });

        btnSair.setOnAction(e -> {
            stage.close();
            new TelaEscolhaTipo().start(new Stage());
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(lblInfo, btnVerVagas, btnVerPublicacoes, btnEditar, btnMudarSenha, btnDeletar, btnSair);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #13293D;");
        Scene scene = new Scene(layout, 400, 400);
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
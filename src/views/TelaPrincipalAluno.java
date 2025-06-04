package views;

import controllers.AlunoController;
import javafx.geometry.Insets;
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

        Button btnVerVagas = new Button("Ver Vagas Disponíveis");
        Button btnVerPublicacoes = new Button("Ver Publicações");
        Button btnEditar = new Button("Editar Informações");
        Button btnMudarSenha = new Button("Mudar Senha"); // Novo botão
        Button btnSair = new Button("Sair");

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

        // Novo método para mudança de senha
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
                    // Verifica se a senha atual está correta
                    if (!senhaAtualField.getText().equals(aluno.getSenha())) {
                        mostrarAlerta("Senha atual incorreta!");
                        return false;
                    }

                    // Verifica se as novas senhas coincidem
                    if (!novaSenhaField.getText().equals(confirmarSenhaField.getText())) {
                        mostrarAlerta("As novas senhas não coincidem!");
                        return false;
                    }

                    // Verifica se a nova senha é diferente da atual
                    if (novaSenhaField.getText().equals(senhaAtualField.getText())) {
                        mostrarAlerta("A nova senha deve ser diferente da atual!");
                        return false;
                    }

                    // Atualiza a senha
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
            new TelaLogin("Aluno").start(new Stage());
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(lblInfo, btnVerVagas, btnVerPublicacoes, btnEditar, btnMudarSenha, btnSair);

        Scene scene = new Scene(layout, 400, 350); // Aumentei a altura para acomodar o novo botão
        stage.setScene(scene);
        stage.show();
    }

    // Método auxiliar para mostrar alertas
    private void mostrarAlerta(String mensagem) {
        mostrarAlerta(mensagem, Alert.AlertType.ERROR);
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
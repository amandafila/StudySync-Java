package views;

import controllers.LoginController;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Aluno;
import models.Faculdade;

public class TelaLogin {

    private final String tipoUsuario;

    public TelaLogin(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void start(Stage stage) {
        stage.setTitle("Login - " + tipoUsuario);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #13293D;");
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new javafx.geometry.Insets(20));

        Label lblUser = new Label("Usuário:");
        lblUser.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");
        TextField txtUser = new TextField();
        txtUser.setStyle(
                "-fx-text-fill: white;" +               // cor da letra
                        "-fx-background-color: #006494;"        // cor de fundo
        );

        Label lblSenha = new Label("Senha:");
        lblSenha.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");
        PasswordField txtSenha = new PasswordField();
        txtSenha.setStyle(
                "-fx-text-fill: white;" +               // cor da letra
                        "-fx-background-color: #006494;"        // cor de fundo
        );

        Button btnLogin = new Button("Entrar");
        btnLogin.setStyle("-fx-background-color: #006494;" +
                "-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10;"

        );

        grid.add(lblUser, 0, 0);
        grid.add(txtUser, 1, 0);

        grid.add(lblSenha, 0, 1);
        grid.add(txtSenha, 1, 1);

        grid.add(btnLogin, 1, 2);

        btnLogin.setOnAction(e -> {
            String username = txtUser.getText();
            String senha = txtSenha.getText();

            Object usuario = LoginController.autenticar(tipoUsuario, username, senha);

            if (usuario != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Login realizado com sucesso!");
                alert.showAndWait();

                stage.close();
                if (usuario instanceof Aluno) {
                    new TelaPrincipalAluno((Aluno) usuario).start(new Stage());
                } else if (usuario instanceof Faculdade) {
                    new TelaPrincipalFaculdade((Faculdade) usuario).start(new Stage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Usuário ou senha inválidos.");
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(grid, 350, 200);
        stage.setScene(scene);
        stage.show();
    }
}

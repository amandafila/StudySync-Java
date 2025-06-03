package views;

import controllers.LoginController;
import javafx.scene.Scene;
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
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new javafx.geometry.Insets(20));

        Label lblUser = new Label("Usuário:");
        TextField txtUser = new TextField();

        Label lblSenha = new Label("Senha:");
        PasswordField txtSenha = new PasswordField();

        Button btnLogin = new Button("Entrar");

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

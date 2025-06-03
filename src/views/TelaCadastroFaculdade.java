package views;

//mesma coisa de aluno

import controllers.CadastroController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Faculdade;

public class TelaCadastroFaculdade {

    public void start(Stage stage) {
        stage.setTitle("Cadastro de Faculdade");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();

        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();

        Label lblUser = new Label("Username:");
        TextField txtUser = new TextField();

        Label lblSenha = new Label("Senha:");
        PasswordField txtSenha = new PasswordField();

        Label lblCnpj = new Label("CNPJ:");
        TextField txtCnpj = new TextField();

        Label lblCep = new Label("CEP:");
        TextField txtCep = new TextField();

        Label lblTelefone = new Label("Telefone:");
        TextField txtTelefone = new TextField();

        Button btnCadastrar = new Button("Cadastrar");
        Button btnLogin = new Button("Login");


        grid.add(lblNome, 0, 0);
        grid.add(txtNome, 1, 0);

        grid.add(lblEmail, 0, 1);
        grid.add(txtEmail, 1, 1);

        grid.add(lblUser, 0, 2);
        grid.add(txtUser, 1, 2);

        grid.add(lblSenha, 0, 3);
        grid.add(txtSenha, 1, 3);

        grid.add(lblCnpj, 0, 4);
        grid.add(txtCnpj, 1, 4);

        grid.add(lblCep, 0, 5);
        grid.add(txtCep, 1, 5);

        grid.add(lblTelefone, 0, 6);
        grid.add(txtTelefone, 1, 6);

        grid.add(btnCadastrar, 1, 7);
        grid.add(btnLogin, 1, 8);

        btnLogin.setOnAction(e -> {
            stage.close();
            new TelaLogin("Faculdade").start(new Stage());
        });


        btnCadastrar.setOnAction(e -> {
            try {
                int novoId = CadastroController.gerarProximoIdFaculdade();
                Faculdade faculdade = new Faculdade(
                        novoId, // id temporário
                        txtNome.getText(),
                        txtEmail.getText(),
                        txtUser.getText(),
                        txtSenha.getText(),
                        Long.parseLong(txtCnpj.getText()),
                        Long.parseLong(txtCep.getText()),
                        Long.parseLong(txtTelefone.getText())
                );

                CadastroController.salvarFaculdade(faculdade);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Faculdade cadastrada com sucesso!");
                alert.showAndWait();

                stage.close();
                new TelaEscolhaTipo().start(new Stage());

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Erro: Preencha os campos numéricos corretamente!");
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(grid, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}

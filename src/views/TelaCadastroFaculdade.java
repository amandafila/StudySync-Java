package views;

//mesma coisa de aluno

import controllers.CadastroController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Faculdade;

public class TelaCadastroFaculdade {


    public void start(Stage stage) {
        stage.setTitle("Cadastro de Faculdade");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #13293D;");
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblNome = new Label("Nome:");
        lblNome.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");
        TextField txtNome = new TextField();
        txtNome.setStyle(
                "-fx-text-fill: white;" +               // cor da letra
                        "-fx-background-color: #006494;"        // cor de fundo
        );

        Label lblEmail = new Label("Email:");
        lblEmail.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");
        TextField txtEmail = new TextField();
        txtEmail.setStyle(
                "-fx-text-fill: white;" +               // cor da letra
                        "-fx-background-color: #006494;"        // cor de fundo
        );

        Label lblUser = new Label("Username:");
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

        Label lblCnpj = new Label("CNPJ:");
        lblCnpj.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");
        TextField txtCnpj = new TextField();
        txtCnpj.setStyle(
                "-fx-text-fill: white;" +               // cor da letra
                        "-fx-background-color: #006494;"        // cor de fundo
        );

        Label lblCep = new Label("CEP:");
        lblCep.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");
        TextField txtCep = new TextField();
        txtCep.setStyle(
                "-fx-text-fill: white;" +               // cor da letra
                        "-fx-background-color: #006494;"        // cor de fundo
        );

        Label lblTelefone = new Label("Telefone:");
        lblTelefone.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");
        TextField txtTelefone = new TextField();
        txtTelefone.setStyle(
                "-fx-text-fill: white;" +               // cor da letra
                        "-fx-background-color: #006494;"        // cor de fundo
        );

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setStyle("-fx-background-color: #006494;" +
                "-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10;"

        );
        btnCadastrar.setPrefWidth(200);

        Button btnLogin = new Button("Login");
        btnLogin.setStyle("-fx-background-color: #006494;" +
                "-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10;"

        );
        btnLogin.setPrefWidth(200);


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

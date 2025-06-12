package views;

import controllers.CadastroController;
import controllers.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Aluno;
import models.Faculdade;

import java.util.List;

public class TelaCadastroAluno {

    public void start(Stage stage) {
        stage.setTitle("Cadastro de Aluno");

        ComboBox<String> comboFaculdades = new ComboBox<>();
        comboFaculdades.setPromptText("Selecione a faculdade");
        comboFaculdades.setStyle(
                "-fx-background-color: #006494;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        comboFaculdades.setPrefWidth(200);

        try {
            List<Faculdade> todasFaculdades = CadastroController.carregarTodasFaculdades();
            todasFaculdades.stream()
                    .map(Faculdade::getNome)
                    .forEach(comboFaculdades.getItems()::add);
        } catch (Exception e) {
            System.out.println("Erro ao carregar faculdades: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Não foi possível carregar as faculdades. Tente novamente mais tarde.");
            alert.showAndWait();
        }

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #13293D;");
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblNome = new Label("Nome:");
        lblNome.setStyle("-fx-text-fill: #E8F1F2;" + "-fx-font-size: 14px;");
        TextField txtNome = new TextField();
        txtNome.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: #006494;"
        );

        Label lblEmail = new Label("Email:");
        lblEmail.setStyle("-fx-text-fill: #E8F1F2;" + "-fx-font-size: 14px;");
        TextField txtEmail = new TextField();
        txtEmail.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: #006494;"
        );

        Label lblUser = new Label("Usuário:");
        lblUser.setStyle("-fx-text-fill: #E8F1F2;" + "-fx-font-size: 14px;");
        TextField txtUser = new TextField();
        txtUser.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: #006494;"
        );

        Label lblSenha = new Label("Senha:");
        lblSenha.setStyle("-fx-text-fill: #E8F1F2;" + "-fx-font-size: 14px;");
        PasswordField txtSenha = new PasswordField();
        txtSenha.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: #006494;"
        );

        Label lblCpf = new Label("CPF:");
        lblCpf.setStyle("-fx-text-fill: #E8F1F2;" + "-fx-font-size: 14px;");
        TextField txtCpf = new TextField();
        txtCpf.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: #006494;"
        );

        Label lblFaculdade = new Label("Faculdade:");
        lblFaculdade.setStyle("-fx-text-fill: #E8F1F2;" + "-fx-font-size: 14px;");

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setStyle(
                "-fx-background-color: #006494;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        btnCadastrar.setPrefWidth(200);

        Button btnLogin = new Button("Login");
        btnLogin.setStyle(
                "-fx-background-color: #041024;" +
                        "-fx-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        btnLogin.setPrefWidth(200);

        grid.add(lblNome, 0, 1);
        grid.add(txtNome, 1, 1);
        grid.add(lblEmail, 0, 2);
        grid.add(txtEmail, 1, 2);
        grid.add(lblUser, 0, 3);
        grid.add(txtUser, 1, 3);
        grid.add(lblSenha, 0, 4);
        grid.add(txtSenha, 1, 4);
        grid.add(lblCpf, 0, 5);
        grid.add(txtCpf, 1, 5);
        grid.add(lblFaculdade, 0, 6);
        grid.add(comboFaculdades, 1, 6);
        grid.add(btnCadastrar, 1, 7);
        grid.add(btnLogin, 1, 0);

        btnLogin.setOnAction(e -> {
            stage.close();
            new TelaLogin("Aluno").start(new Stage());
        });

        btnCadastrar.setOnAction(e -> {
            try {
                if (txtNome.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                        txtUser.getText().isEmpty() || txtSenha.getText().isEmpty() ||
                        txtCpf.getText().isEmpty() || comboFaculdades.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.setContentText("Preencha todos os campos!");
                    alert.showAndWait();
                    return;
                }

                int novoId = CadastroController.gerarProximoIdAluno();
                Aluno aluno = new Aluno(
                        novoId,
                        txtNome.getText(),
                        txtEmail.getText(),
                        txtUser.getText(),
                        txtSenha.getText(),
                        Long.parseLong(txtCpf.getText()),
                        comboFaculdades.getValue()
                );
                CadastroController.salvarAluno(aluno);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Aluno cadastrado com sucesso!");
                alert.showAndWait();

                stage.close();
                new TelaEscolhaTipo().start(new Stage());

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("CPF inválido! Digite apenas números.");
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(grid, 400, 350);
        stage.setScene(scene);
        stage.show();
    }
}
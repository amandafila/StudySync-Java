package views;

import controllers.CadastroController;
import controllers.LoginController; // Para carregar faculdades agora via LoginController
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Aluno;
import models.Faculdade; // Importe Faculdade

import java.io.IOException; // Já não é tão direto para o .txt, mas pode manter para outras exceções.
import java.util.List; // Importe List

public class TelaCadastroAluno {

    public void start(Stage stage) {
        stage.setTitle("Cadastro de Aluno");

        ComboBox<String> comboFaculdades = new ComboBox<>();
        comboFaculdades.setPromptText("Selecione a faculdade");

        // Carrega os nomes das faculdades do arquivo .dat
        try {
            // Usando LoginController para carregar as faculdades (reuso do método existente)
            // Ou criar um método específico em CadastroController para carregar apenas as faculdades
            List<Faculdade> faculdades = (List<Faculdade>) LoginController.autenticar("Faculdade", "", ""); // Truque para carregar todas as faculdades, não ideal.
            // MELHOR: Adicionar um método `carregarTodasFaculdades()` em `CadastroController` ou `FaculdadeController`
            // Por simplicidade para a refatoração, vamos adicionar um novo método em `CadastroController`
            // ou usar o `carregarObjetos` privado, mas para ser publicamente acessível...

            // ALTERNATIVA MAIS LIMPA (adicionar um método em CadastroController):
            List<Faculdade> todasFaculdades = CadastroController.carregarTodasFaculdades(); // Precisamos criar este método
            todasFaculdades.stream()
                    .map(Faculdade::getNome)
                    .forEach(comboFaculdades.getItems()::add);

        } catch (Exception e) { // Captura qualquer exceção durante o carregamento
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
                "-fx-text-fill: white;" +               // cor da letra
                        "-fx-background-color: #006494;"        // cor de fundo
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

        GridPane.setHalignment(btnCadastrar, HPos.RIGHT);

        grid.add(lblNome, 0, 0);
        grid.add(txtNome, 1, 0);
        grid.add(lblEmail, 0, 1);
        grid.add(txtEmail, 1, 1);
        grid.add(lblUser, 0, 2);
        grid.add(txtUser, 1, 2);
        grid.add(lblSenha, 0, 3);
        grid.add(txtSenha, 1, 3);
        grid.add(lblCpf, 0, 4);
        grid.add(txtCpf, 1, 4);
        grid.add(lblFaculdade, 0, 5);
        grid.add(comboFaculdades, 1, 5);
        grid.add(btnCadastrar, 1, 6);

        btnCadastrar.setOnAction(e -> {
            try {
                // Validação para campos vazios
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

        Scene scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}
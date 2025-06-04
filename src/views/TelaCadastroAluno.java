package views;

import controllers.CadastroController;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Aluno;

import java.io.IOException;

public class TelaCadastroAluno {

    public void start(Stage stage) {
        //metodo chamado quando essa tela é aberta
        //stage é a janela
        stage.setTitle("Cadastro de Aluno");

        ComboBox<String> comboFaculdades = new ComboBox<>();
        comboFaculdades.setPromptText("Selecione a faculdade");

        // Carrega os nomes das faculdades do arquivo
        try {
            java.nio.file.Files.lines(java.nio.file.Paths.get("faculdades.txt"))
                    .map(linha -> linha.split(";")[1]) // pega o nome (segunda posição)
                    .forEach(comboFaculdades.getItems()::add);
        } catch (IOException e) {
            System.out.println("Erro ao carregar faculdades: " + e.getMessage());
        }

        GridPane grid = new GridPane();
        //layout em forma de grade
        grid.setAlignment(Pos.CENTER);
        //alinha tudo no meio
        grid.setStyle("-fx-background-color: #13293D;");
        grid.setPadding(new Insets(20));
        //padding de 20px entre os componentes
        grid.setHgap(10);
        //espaço de 10px entre as colunas
        grid.setVgap(10);
        //espaço de 10px entre as linhas

        Label lblNome = new Label("Nome:");
        lblNome.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");
        TextField txtNome = new TextField();
        txtNome.setStyle(
                "-fx-text-fill: white;" +               // cor da letra
                "-fx-background-color: #006494;"        // cor de fundo
        );

        //label e espaço pro usuário colocar o nome

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


        Label lblCpf = new Label("CPF:");
        lblCpf.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");
        TextField txtCpf = new TextField();
        txtCpf.setStyle(
                "-fx-text-fill: white;" +               // cor da letra
                        "-fx-background-color: #006494;"        // cor de fundo
        );


        Button btnCadastrar = new Button("Cadastrar");
        Button btnLogin = new Button("Login");

        // Posicionando no grid (coluna, linha)
        grid.add(lblNome, 0, 0);
        //coloca o label nome na coluna 0 linha 0
        grid.add(txtNome, 1, 0);
        //coloca o input do nome na linha 0 coluna 1

        grid.add(lblEmail, 0, 1);
        grid.add(txtEmail, 1, 1);

        grid.add(lblUser, 0, 2);
        grid.add(txtUser, 1, 2);

        grid.add(lblSenha, 0, 3);
        grid.add(txtSenha, 1, 3);

        grid.add(lblCpf, 0, 4);
        grid.add(txtCpf, 1, 4);
        Label lblFaculdade = new Label("Faculdade:");
        lblFaculdade.setStyle("-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;");
        grid.add(lblFaculdade, 0, 5);
        grid.add(comboFaculdades, 1, 5);
        comboFaculdades.setStyle(
                "-fx-background-color: #1B98E0;" +     // fundo do campo principal
                        "-fx-prompt-text-fill: #E8F1F2;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );

        comboFaculdades.setPrefWidth(200);

        grid.add(btnCadastrar, 1, 6);
        //cria o botão e insere ele na grade
        grid.add(btnLogin, 1, 7);
        btnLogin.setStyle("-fx-background-color: #006494;" +
                "-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10;"

        );
        btnLogin.setPrefWidth(200);
        btnCadastrar.setStyle("-fx-background-color: #006494;" +
                "-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10;"
        );
        btnCadastrar.setPrefWidth(200);

        btnLogin.setOnAction(e -> {
            stage.close();
            new TelaLogin("Aluno").start(new Stage());
        });

        btnCadastrar.setOnAction(e -> {
            //código de quando o botãao for clicado
            try {
                int novoId = CadastroController.gerarProximoIdAluno();
                Aluno aluno = new Aluno(
                        //cria um novo aluno
                        novoId,// id temporário, pode gerar dinamicamente depois
                        txtNome.getText(),
                        //pega o que ta escrito no input
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
                //reclama se o cpf estiver inválido
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

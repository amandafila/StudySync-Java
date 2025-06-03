package views;

import controllers.CadastroController;
import javafx.geometry.Insets;
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
        grid.setPadding(new Insets(20));
        //padding de 20px entre os componentes
        grid.setHgap(10);
        //espaço de 10px entre as colunas
        grid.setVgap(10);
        //espaço de 10px entre as linhas

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();
        //label e espaço pro usuário colocar o nome

        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();

        Label lblUser = new Label("Username:");
        TextField txtUser = new TextField();

        Label lblSenha = new Label("Senha:");
        PasswordField txtSenha = new PasswordField();

        Label lblCpf = new Label("CPF:");
        TextField txtCpf = new TextField();

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
        grid.add(lblFaculdade, 0, 5);
        grid.add(comboFaculdades, 1, 5);

        grid.add(btnCadastrar, 1, 6);
        //cria o botão e insere ele na grade
        grid.add(btnLogin, 1, 7);

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

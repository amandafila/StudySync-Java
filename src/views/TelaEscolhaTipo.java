package views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEscolhaTipo extends Application {
    //quer dizer que é um programa JavaFx

    @Override
    public void start(Stage primaryStage) {
        //o JavaFX chama automaticamente esse metodo quando vc da run
        //primaryStage é a janela principal que o usuário deveria ver

        primaryStage.setTitle("Escolha o Tipo de Cadastro");
        //título da janela


        Button btnAluno = new Button("Aluno");
        btnAluno.setStyle("-fx-background-color: #006494;" +
                "-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10;"
        );
        Button btnFaculdade = new Button("Faculdade");
        btnFaculdade.setStyle("-fx-background-color: #006494;" +
                "-fx-text-fill: #E8F1F2;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10;"
        );
        //cria botões com seus respectivos textos

        btnAluno.setMaxWidth(Double.MAX_VALUE);
        btnFaculdade.setMaxWidth(Double.MAX_VALUE);
        //manda o botão esticar a largura o máximo que pode

        btnAluno.setOnAction(e -> {
            //define o que acontece quando você aperta no botão
            primaryStage.close();
            //fecha a janela
            new TelaCadastroAluno().start(new Stage());
            //cria uma nova janela e abre TelaCadastroAluno
        });

        btnFaculdade.setOnAction(e -> {
            primaryStage.close();
            new TelaCadastroFaculdade().start(new Stage());
            //mesma coisa
        });



        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #13293D;");
        //organiza os botões na vertical
        // 10 pixels de espaçamento entre cada botão

        layout.setPadding(new Insets(20));
        //20px de padding na janela

        layout.getChildren().addAll(btnAluno, btnFaculdade);
        //coloca "filhos" dentro do layout(caixa vertical criada), no caso os 2 botões

        Scene scene = new Scene(layout, 300, 150);
        //cria a scene (o que fica dentro da janela) e insere seu tamanho
        primaryStage.setScene(scene);
        //manda a janela principal colocar a cena q vc criou dentro dela
        primaryStage.show();
        //mostra a janela pro usuário
    }


    public static void main(String[] args) {
        launch(args);
        //inicia o JavaFX e chama o metodo start
    }
}

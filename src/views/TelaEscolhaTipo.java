package views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEscolhaTipo extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Escolha o Tipo de Cadastro");


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

        btnAluno.setMaxWidth(Double.MAX_VALUE);
        btnFaculdade.setMaxWidth(Double.MAX_VALUE);

        btnAluno.setOnAction(e -> {
            primaryStage.close();
            new TelaCadastroAluno().start(new Stage());
        });

        btnFaculdade.setOnAction(e -> {
            primaryStage.close();
            new TelaCadastroFaculdade().start(new Stage());
        });



        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #13293D;");


        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(btnAluno, btnFaculdade);

        Scene scene = new Scene(layout, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        //inicia o JavaFX e chama o metodo start
    }
}

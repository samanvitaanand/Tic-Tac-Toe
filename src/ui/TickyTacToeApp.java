package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TickyTacToeApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TickyTacToeMenu menu = new TickyTacToeMenu(primaryStage);
        Scene scene = new Scene(menu.getRoot(), 500, 400);
        primaryStage.setTitle("Ticky Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
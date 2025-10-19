package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import model.Player;
import ai.AIPlayer;

public class TickyTacToeMenu {
    private final VBox root;

    public TickyTacToeMenu(Stage stage) {
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea 0%, #764ba2 100%); -fx-padding: 60 40;");
        root.setPrefHeight(500);
        root.setPrefWidth(500);

        ImageView tickyImage = new ImageView(new Image(getClass().getResourceAsStream("/ticky.png")));
        tickyImage.setFitHeight(180);
        tickyImage.setPreserveRatio(true);

        // Add shadow to image
        DropShadow imageShadow = new DropShadow();
        imageShadow.setColor(Color.rgb(0, 0, 0, 0.3));
        imageShadow.setRadius(15);
        tickyImage.setEffect(imageShadow);

        Text title = new Text("🤖 Welcome to Ticky Tac Toe!");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
        title.setFill(Color.WHITE);

        // Add shadow to title
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.rgb(0, 0, 0, 0.3));
        titleShadow.setRadius(5);
        title.setEffect(titleShadow);

        Button onePlayer = createButton("Play vs Ticky (AI)");
        Button twoPlayer = createButton("Play vs Friend");

        onePlayer.setOnAction(e -> {
            Player p1 = new Player("You", "X");
            Player p2 = new AIPlayer("Ticky 🤖", "O");
            TickyTacToeUIFX ui = new TickyTacToeUIFX(stage, p1, p2);
            stage.setScene(new Scene(ui.getRoot(), 650, 750));
        });

        twoPlayer.setOnAction(e -> {
            Player p1 = new Player("Player 1", "X");
            Player p2 = new Player("Player 2", "O");
            TickyTacToeUIFX ui = new TickyTacToeUIFX(stage, p1, p2);
            stage.setScene(new Scene(ui.getRoot(), 650, 750));
        });

        root.getChildren().addAll(tickyImage, title, onePlayer, twoPlayer);
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 18));
        btn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.25); " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 25; " +
                "-fx-padding: 15 40; " +
                "-fx-cursor: hand; " +
                "-fx-border-color: rgba(255, 255, 255, 0.3); " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 25;");
        btn.setPrefWidth(280);

        // Add shadow to button
        DropShadow btnShadow = new DropShadow();
        btnShadow.setColor(Color.rgb(0, 0, 0, 0.2));
        btnShadow.setRadius(8);
        btn.setEffect(btnShadow);

        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.4); " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 25; " +
                        "-fx-padding: 15 40; " +
                        "-fx-cursor: hand; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.6); " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 25;"
        ));

        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.25); " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 25; " +
                        "-fx-padding: 15 40; " +
                        "-fx-cursor: hand; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.3); " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 25;"
        ));

        return btn;
    }

    public VBox getRoot() {
        return root;
    }
}
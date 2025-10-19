package ui;

import ai.AIPlayer;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import model.Player;
import model.TicTacToe;
import model.GameStats;

public class TickyTacToeUIFX {
    private final BorderPane root;
    private final Button[][] buttons = new Button[3][3];
    private final TicTacToe game;
    private final GameStats stats = new GameStats();
    private Player currentPlayer;
    private final Label statusLabel = new Label();
    private final Label scoreLabel = new Label();
    private final ImageView tickyImage;
    private final Stage stage;
    private boolean waitingForAI = false; // Track if AI is thinking

    public TickyTacToeUIFX(Stage stage, Player p1, Player p2) {
        this.root = new BorderPane();
        this.game = new TicTacToe(p1, p2);
        this.currentPlayer = p1;
        this.stage = stage;

        // Modern gradient background
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea 0%, #764ba2 100%);");

        VBox topBox = new VBox(15);
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-padding: 20 20 40 20;");

        // Modern status label with shadow
        statusLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        statusLabel.setTextFill(Color.WHITE);
        DropShadow statusShadow = new DropShadow();
        statusShadow.setColor(Color.rgb(0, 0, 0, 0.3));
        statusShadow.setRadius(5);
        statusLabel.setEffect(statusShadow);

        // Score label styling
        scoreLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
        scoreLabel.setTextFill(Color.rgb(255, 255, 255, 0.95));
        scoreLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15); " +
                "-fx-padding: 8 20; -fx-background-radius: 20;");

        tickyImage = new ImageView(new Image(getClass().getResourceAsStream("/peeringTicky.png")));
        tickyImage.setFitWidth(200);
        tickyImage.setPreserveRatio(true);

        topBox.getChildren().addAll(statusLabel, scoreLabel);
        root.setTop(topBox);

        GridPane grid = createBoard();

        // Create a VBox to stack Ticky and board vertically
        VBox gameArea = new VBox(-30); // Negative spacing to overlap Ticky with board
        gameArea.setAlignment(Pos.CENTER);

        // Ticky container - centered
        HBox tickyContainer = new HBox();
        tickyContainer.setAlignment(Pos.CENTER);
        tickyContainer.getChildren().add(tickyImage);

        // Grid container - centered
        HBox gridContainer = new HBox();
        gridContainer.setAlignment(Pos.CENTER);
        gridContainer.getChildren().add(grid);

        gameArea.getChildren().addAll(tickyContainer, gridContainer);
        root.setCenter(gameArea);

        // Homepage button only
        Button homeBtn = new Button("🏠 Homepage");
        homeBtn.setOnAction(e -> {
            TickyTacToeMenu menu = new TickyTacToeMenu(stage);
            stage.setScene(new Scene(menu.getRoot(), 500, 500));
        });
        homeBtn.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 15));
        homeBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.25); " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 25; " +
                "-fx-padding: 12 30; " +
                "-fx-cursor: hand; " +
                "-fx-border-color: rgba(255, 255, 255, 0.3); " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 25;");

        homeBtn.setOnMouseEntered(e -> homeBtn.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.35); " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 25; " +
                        "-fx-padding: 12 30; " +
                        "-fx-cursor: hand; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.5); " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 25;"
        ));

        homeBtn.setOnMouseExited(e -> homeBtn.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.25); " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 25; " +
                        "-fx-padding: 12 30; " +
                        "-fx-cursor: hand; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.3); " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 25;"
        ));

        HBox bottomBox = new HBox(homeBtn);
        bottomBox.setAlignment(Pos.CENTER);
        root.setBottom(bottomBox);
        BorderPane.setMargin(bottomBox, new javafx.geometry.Insets(15));

        updateStatus();
    }

    private GridPane createBoard() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(12);
        grid.setVgap(12);
        grid.setStyle("-fx-padding: 20;");

        // Add shadow effect to grid
        DropShadow gridShadow = new DropShadow();
        gridShadow.setColor(Color.rgb(0, 0, 0, 0.3));
        gridShadow.setRadius(10);
        grid.setEffect(gridShadow);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                Button btn = new Button();
                btn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 48));
                btn.setPrefSize(110, 110);

                // Modern glassmorphism style
                btn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); " +
                        "-fx-background-radius: 15; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.5); " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 15; " +
                        "-fx-cursor: hand;");

                final int row = r, col = c;

                // Hover effect
                btn.setOnMouseEntered(e -> {
                    if (btn.getText().isEmpty()) {
                        btn.setStyle("-fx-background-color: rgba(255, 255, 255, 1); " +
                                "-fx-background-radius: 15; " +
                                "-fx-border-color: #667eea; " +
                                "-fx-border-width: 3; " +
                                "-fx-border-radius: 15; " +
                                "-fx-cursor: hand;");
                    }
                });

                btn.setOnMouseExited(e -> {
                    if (btn.getText().isEmpty()) {
                        btn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); " +
                                "-fx-background-radius: 15; " +
                                "-fx-border-color: rgba(255, 255, 255, 0.5); " +
                                "-fx-border-width: 2; " +
                                "-fx-border-radius: 15; " +
                                "-fx-cursor: hand;");
                    }
                });

                btn.setOnAction(e -> handleMove(btn, row, col));
                buttons[r][c] = btn;
                grid.add(btn, c, r);
            }
        }
        return grid;
    }

    private void handleMove(Button btn, int row, int col) {
        // Prevent moves when waiting for AI or if not human player's turn
        if (waitingForAI) return;
        if (currentPlayer instanceof AIPlayer) return;
        if (!btn.getText().isEmpty()) return;

        boolean moved = game.makeMove(row, col, currentPlayer);
        if (!moved) return;

        btn.setText(currentPlayer.getSymbol());

        // Determine color based on player
        String buttonColor;
        if (currentPlayer == game.getPlayer1()) {
            buttonColor = "linear-gradient(to bottom right, #667eea, #764ba2)"; // Purple for Player 1 (X)
        } else {
            buttonColor = "linear-gradient(to bottom right, #f093fb, #f5576c)"; // Pink for Player 2 (O)
        }

        btn.setStyle("-fx-background-color: " + buttonColor + "; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 15; " +
                "-fx-border-color: rgba(255, 255, 255, 0.5); " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 15;");

        if (checkGameOver()) return;
        switchPlayer();

        if (currentPlayer instanceof AIPlayer) {
            waitingForAI = true; // Block user input
            PauseTransition pause = new PauseTransition(Duration.millis(800));
            pause.setOnFinished(e -> {
                AIPlayer ai = (AIPlayer) currentPlayer;

                int[] move = ai.findBestMove(game.getBoard());
                game.makeMove(move[0], move[1], ai);
                Button aiBtn = buttons[move[0]][move[1]];
                aiBtn.setText(ai.getSymbol());

                // AI move styling - pink gradient
                aiBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #f093fb, #f5576c); " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 15; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.5); " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 15;");

                checkGameOver();
                switchPlayer();
                waitingForAI = false; // Re-enable user input
            });
            pause.play();
        }
    }

    private boolean checkGameOver() {
        Player winner = game.checkWinner();
        if (winner != null) {
            // Show excited face if AI wins
            if (winner instanceof AIPlayer) {
                tickyImage.setImage(new Image(getClass().getResourceAsStream("/excitedTicky.png")));
                tickyImage.setFitWidth(150);
                tickyImage.setTranslateY(5);
            }

            showResult(winner.getName() + " wins!");
            winner.incrementScore();
            stats.updateStats(winner, game.getPlayer1(), game.getPlayer2());
            resetBoard();
            return true;
        } else if (game.isDraw()) {
            showResult("It's a draw!");
            stats.incrementDraw();
            resetBoard();
            return true;
        }
        updateStatus();
        return false;
    }

    private void showResult(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        // Style the alert
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea, #764ba2);");
        dialogPane.lookup(".content.label").setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

        alert.showAndWait();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();
        updateStatus();
    }

    private void resetBoard() {
        for (Button[] row : buttons)
            for (Button b : row) {
                b.setText("");
                b.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); " +
                        "-fx-background-radius: 15; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.5); " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 15; " +
                        "-fx-cursor: hand;");
            }
        game.resetBoard();
        currentPlayer = game.getPlayer1();
        waitingForAI = false; // Reset AI waiting flag
        updateStatus();
        tickyImage.setImage(new Image(getClass().getResourceAsStream("/peeringTicky.png")));
    }

    private void updateStatus() {
        statusLabel.setText(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
        scoreLabel.setText(game.getPlayer1().getName() + ": " + game.getPlayer1().getScore() +
                " | " + game.getPlayer2().getName() + ": " + game.getPlayer2().getScore() +
                " | Draws: " + stats.getDraws());
    }

    public BorderPane getRoot() {
        return root;
    }
}
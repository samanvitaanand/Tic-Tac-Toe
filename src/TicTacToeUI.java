import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TicTacToeUI extends Application {

    private Button[][] buttons = new Button[3][3];
    private Label statusLabel = new Label("Welcome to Tic Tac Toe!");
    private Label scoreLabel = new Label();
    private TicTacToeGame game;
    private Player player1;
    private Player player2;
    private GameStats gameStats;
    private int currPlayerIndex = 0; // 0 for player1, 1 for player2

    @Override
    public void start(Stage primaryStage) {
        // Initialize game components
        gameStats = new GameStats();
        player1 = new Player("Player 1", "X");
        player2 = new Player("Player 2", "O");
        game = new TicTacToeGame(player1, player2, gameStats);
        game.board.create_board();

        BorderPane root = new BorderPane();
        root.setTop(createTopPanel());
        root.setCenter(createBoardGrid());
        root.setBottom(createBottomPanel());

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Tic Tac Toe (JavaFX)");
        primaryStage.setScene(scene);
        primaryStage.show();

        updateScoreLabel();
        statusLabel.setText(player1.getName() + "'s turn (" + player1.getSymbol() + ")");
    }

    private VBox createTopPanel() {
        VBox top = new VBox(10);
        top.setAlignment(Pos.CENTER);
        top.getChildren().addAll(statusLabel, scoreLabel);
        return top;
    }

    private GridPane createBoardGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = new Button("");
                btn.setPrefSize(100, 100);
                btn.setStyle("-fx-font-size: 24;");
                int r = row;
                int c = col;
                btn.setOnAction(e -> handleMove(r, c));
                buttons[row][col] = btn;
                grid.add(btn, col, row);
            }
        }
        return grid;
    }

    private HBox createBottomPanel() {
        HBox bottom = new HBox(10);
        bottom.setAlignment(Pos.CENTER);
        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction(e -> resetGame());
        bottom.getChildren().add(newGameBtn);
        return bottom;
    }

    private void handleMove(int row, int col) {
        Player currPlayer = (currPlayerIndex == 0) ? player1 : player2;
        char symbol = currPlayer.getSymbol().charAt(0);

        if (game.board.valid_play(new Move(row, col, symbol))) {
            game.board.place_symbol(new Move(row, col, symbol));
            buttons[row][col].setText(String.valueOf(symbol));
            buttons[row][col].setDisable(true);

            if (game.board.check_win(symbol)) {
                statusLabel.setText(currPlayer.getName() + " wins!");
                gameStats.updateStats(currPlayer, player1, player2);
                currPlayer.incrementScore();
                disableBoard();
                updateScoreLabel();
            } else if (game.board.isDraw()) {
                statusLabel.setText("It's a draw!");
                gameStats.updateStats(null, player1, player2);
                updateScoreLabel();
            } else {
                currPlayerIndex = 1 - currPlayerIndex; // Switch turns
                Player next = (currPlayerIndex == 0) ? player1 : player2;
                statusLabel.setText(next.getName() + "'s turn (" + next.getSymbol() + ")");
            }
        } else {
            statusLabel.setText("Invalid move! Try again.");
        }
    }

    private void disableBoard() {
        for (Button[] row : buttons) {
            for (Button btn : row) {
                btn.setDisable(true);
            }
        }
    }

    private void resetGame() {
        game.board.create_board();
        for (Button[] row : buttons) {
            for (Button btn : row) {
                btn.setText("");
                btn.setDisable(false);
            }
        }
        currPlayerIndex = 0;
        statusLabel.setText(player1.getName() + "'s turn (" + player1.getSymbol() + ")");
    }

    private void updateScoreLabel() {
        scoreLabel.setText(
                player1.getName() + " (X): " + player1.getScore() + " | " +
                        player2.getName() + " (O): " + player2.getScore()
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}

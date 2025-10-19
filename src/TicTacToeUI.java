import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeUI extends JFrame {
    private final JButton[][] buttons = new JButton[3][3];
    private final TicTacToe game;
    private Player currentPlayer;
    private final GameStats stats;
    private final List<Move> movesList = new ArrayList<>();

    // Labels for UI feedback
    private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel scoreLabel = new JLabel("", SwingConstants.CENTER);

    public TicTacToeUI(Player player1, Player player2) {
        super("Tic Tac Toe");

        this.stats = new GameStats();
        this.game = new TicTacToe(player1, player2);
        this.currentPlayer = player1;

        setLayout(new BorderLayout());
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initBoard();
        updateScoreLabel();

        // Status panel
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusPanel.add(statusLabel);
        statusPanel.add(scoreLabel);
        add(statusPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);

        statusLabel.setText(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
    }

    private void initBoard() {
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        Font buttonFont = new Font("Arial", Font.BOLD, 48);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton button = new JButton("");
                button.setFont(buttonFont);
                int row = r, col = c;

                button.addActionListener(e -> handleMove(row, col, button));
                buttons[r][c] = button;
                boardPanel.add(button);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    private void handleMove(int row, int col, JButton button) {
        if (!button.getText().equals("")) return; // Already filled

        // Human move
        boolean moved = game.makeMove(row, col, currentPlayer);
        if (!moved) {
            JOptionPane.showMessageDialog(this, "Invalid move! Try again.");
            return;
        }
        button.setText(currentPlayer.getSymbol());
        movesList.add(new Move(row, col, currentPlayer.getSymbol()));

        if (checkGameOver()) return;

        switchPlayer();

        // AI move
        if (currentPlayer instanceof AIPlayer) {
            AIPlayer ai = (AIPlayer) currentPlayer;

            // small delay so UI updates
            Timer t = new Timer(300, e -> {
                int[] move = ai.findBestMove(game.getBoard());
                if (move == null) return;
                if (move[0] < 0) return;

                JButton aiButton = buttons[move[0]][move[1]];
                boolean aiMoved = game.makeMove(move[0], move[1], currentPlayer);
                if (aiMoved) {
                    aiButton.setText(currentPlayer.getSymbol());
                    movesList.add(new Move(move[0], move[1], currentPlayer.getSymbol()));
                }

                if (!checkGameOver()) switchPlayer();
            });
            t.setRepeats(false);
            t.start();
        }
    }

    private boolean checkGameOver() {
        Player winner = game.checkWinner();
        if (winner != null) {
            String message = winner.getName() + " wins!";
            winner.incrementScore();
            stats.updateStats(winner, game.getPlayer1(), game.getPlayer2());
            updateScoreLabel();
            JOptionPane.showMessageDialog(this, message);
            resetBoard();
            return true;
        }

        if (game.isDraw()) {
            stats.incrementDraw();
            updateScoreLabel();
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
            return true;
        }

        statusLabel.setText(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
        return false;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == game.getPlayer1() ? game.getPlayer2() : game.getPlayer1();
        statusLabel.setText(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
    }

    private void resetBoard() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                buttons[r][c].setText("");

        movesList.clear();
        game.resetBoard();
        currentPlayer = game.getPlayer1();
        statusLabel.setText(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
    }

    private void updateScoreLabel() {
        scoreLabel.setText(
                game.getPlayer1().getName() + ": " + game.getPlayer1().getScore() + " | " +
                        game.getPlayer2().getName() + ": " + game.getPlayer2().getScore() + " | " +
                        "Draws: " + stats.getDraws()
        );
    }
}



//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TicTacToeUI extends JFrame {
//    private final JButton[][] buttons = new JButton[3][3];
//    private final TicTacToe game;
//    private Player currentPlayer;
//    private final GameStats stats;
//    private final List<Move> movesList = new ArrayList<>();
//
//    // Labels for UI feedback
//    private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
//    private final JLabel scoreLabel = new JLabel("", SwingConstants.CENTER);
//
//    public TicTacToeUI(Player player1, Player player2) {
//        super("Tic Tac Toe");
//
//        this.stats = new GameStats();
//        this.game = new TicTacToe(player1, player2);
//        this.currentPlayer = player1;
//
//        setLayout(new BorderLayout());
//        setSize(400, 450);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//        initBoard();
//        updateScoreLabel();
//
//        // Status panel
//        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
//        statusPanel.add(statusLabel);
//        statusPanel.add(scoreLabel);
//        add(statusPanel, BorderLayout.SOUTH);
//
//        setVisible(true);
//    }
//
//    private void initBoard() {
//        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
//        Font buttonFont = new Font("Arial", Font.BOLD, 48);
//
//        for (int r = 0; r < 3; r++) {
//            for (int c = 0; c < 3; c++) {
//                JButton button = new JButton("");
//                button.setFont(buttonFont);
//                int row = r, col = c;
//
//                button.addActionListener(e -> handleMove(row, col, button));
//                buttons[r][c] = button;
//                boardPanel.add(button);
//            }
//        }
//
//        add(boardPanel, BorderLayout.CENTER);
//    }
//
//    private void handleMove(int row, int col, JButton button) {
//        if (!button.getText().equals("")) return; // Already filled
//
//        // Human move
//        button.setText(currentPlayer.getSymbol());
//        game.makeMove(row, col, currentPlayer);
//        movesList.add(new Move(row, col, currentPlayer.getSymbol()));
//
//        if (checkGameOver()) return;
//
//        switchPlayer();
//
//        // AI move
//        if (currentPlayer instanceof AIPlayer) {
//            AIPlayer ai = (AIPlayer) currentPlayer;
//            int[] move = ai.findBestMove(game.getBoard());
//
//            buttons[move[0]][move[1]].setText(currentPlayer.getSymbol());
//            game.makeMove(move[0], move[1], currentPlayer);
//            movesList.add(new Move(move[0], move[1], currentPlayer.getSymbol()));
//
//            if (checkGameOver()) return;
//            switchPlayer();
//        }
//    }
//
//    private boolean checkGameOver() {
//        Player winner = game.checkWinner();
//        if (winner != null || game.isDraw()) {
//            String message;
//            if (winner != null) {
//                message = winner.getName() + " wins!";
//                winner.incrementScore();
//                stats.updateStats(winner, game.getPlayer1(), game.getPlayer2());
//            } else {
//                message = "It's a draw!";
//                stats.incrementDraw();
//            }
//
//            updateScoreLabel();
//            JOptionPane.showMessageDialog(this, message);
//            resetBoard();
//            return true;
//        }
//        statusLabel.setText(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
//        return false;
//    }
//
//    private void switchPlayer() {
//        currentPlayer = currentPlayer == game.getPlayer1() ? game.getPlayer2() : game.getPlayer1();
//        statusLabel.setText(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
//    }
//
//    private void resetBoard() {
//        for (int r = 0; r < 3; r++)
//            for (int c = 0; c < 3; c++)
//                buttons[r][c].setText("");
//
//        movesList.clear();
//        currentPlayer = game.getPlayer1();
//        statusLabel.setText(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
//    }
//
//    private void updateScoreLabel() {
//        scoreLabel.setText(
//                game.getPlayer1().getName() + ": " + game.getPlayer1().getScore() + " | " +
//                        game.getPlayer2().getName() + ": " + game.getPlayer2().getScore() + " | " +
//                        "Draws: " + stats.getDraws()
//        );
//    }
//}
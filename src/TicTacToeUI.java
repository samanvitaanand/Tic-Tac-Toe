public class TicTacToeUI {
    private final char[][] board;
    private final Player player1;
    private final Player player2;

    public TicTacToeUI(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new char[3][3];
        resetBoard();
    }

    public void resetBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = '-';
            }
        }
    }

    public boolean makeMove(int row, int col, Player player) {
        if (board[row][col] == '-') {
            board[row][col] = player.getSymbol().charAt(0);
            return true;
        }
        return false;
    }

    public Player checkWinner() {
        char s1 = player1.getSymbol().charAt(0);
        char s2 = player2.getSymbol().charAt(0);

        if (hasWon(s1)) return player1;
        if (hasWon(s2)) return player2;
        return null;
    }

    private boolean hasWon(char symbol) {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }

        // Diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;

        return false;
    }

    public boolean isDraw() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == '-') return false;
            }
        }
        return true;
    }

    public char[][] getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
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
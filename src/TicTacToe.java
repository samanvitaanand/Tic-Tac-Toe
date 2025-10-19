//import javax.swing.*;
//
//public class TicTacToe {
//    private final char[][] board;
//    private final Player player1;
//    private final Player player2;
//
//    public TicTacToe(Player player1, Player player2) {
//        this.player1 = player1;
//        this.player2 = player2;
//        this.board = new char[3][3];
//        resetBoard();
//    }
//
//    public void resetBoard() {
//        for (int r = 0; r < 3; r++) {
//            for (int c = 0; c < 3; c++) {
//                board[r][c] = '-';
//            }
//        }
//    }
//
//    public boolean makeMove(int row, int col, Player player) {
//        if (board[row][col] == '-') {
//            board[row][col] = player.getSymbol().charAt(0);
//            return true;
//        }
//        return false;
//    }
//
//    public Player checkWinner() {
//        char s1 = player1.getSymbol().charAt(0);
//        char s2 = player2.getSymbol().charAt(0);
//
//        if (hasWon(s1)) return player1;
//        if (hasWon(s2)) return player2;
//        return null;
//    }
//
//    private boolean hasWon(char symbol) {
//        // Rows & Columns
//        for (int i = 0; i < 3; i++) {
//            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
//            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
//        }
//
//        // Diagonals
//        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
//        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
//
//        return false;
//    }
//
//    public boolean isDraw() {
//        for (int r = 0; r < 3; r++) {
//            for (int c = 0; c < 3; c++) {
//                if (board[r][c] == '-') return false;
//            }
//        }
//        return true;
//    }
//
//    public char[][] getBoard() {
//        return board;
//    }
//
//    public Player getPlayer1() {
//        return player1;
//    }
//
//    public Player getPlayer2() {
//        return player2;
//    }
//}

//public class TicTacToe {
//    private final char[][] board;
//    private final Player player1;
//    private final Player player2;
//
//    public TicTacToe(Player player1, Player player2) {
//        this.player1 = player1;
//        this.player2 = player2;
//        this.board = new char[3][3];
//        resetBoard();
//    }
//
//    public void resetBoard() {
//        for (int r = 0; r < 3; r++)
//            for (int c = 0; c < 3; c++)
//                board[r][c] = '-';
//    }
//
//    public boolean makeMove(int row, int col, Player player) {
//        if (row < 0 || row >= 3 || col < 0 || col >= 3) return false;
//        if (board[row][col] == '-') {
//            board[row][col] = player.getSymbol().charAt(0);
//            return true;
//        }
//        return false;
//    }
//
//    public Player checkWinner() {
//        char s1 = player1.getSymbol().charAt(0);
//        char s2 = player2.getSymbol().charAt(0);
//        if (hasWon(s1)) return player1;
//        if (hasWon(s2)) return player2;
//        return null;
//    }
//
//    private boolean hasWon(char symbol) {
//        for (int i = 0; i < 3; i++) {
//            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
//            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
//        }
//        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
//        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
//        return false;
//    }
//
//    public boolean isDraw() {
//        for (int r = 0; r < 3; r++)
//            for (int c = 0; c < 3; c++)
//                if (board[r][c] == '-') return false;
//        // no empty squares and no winner
//        return checkWinner() == null;
//    }
//
//    public char[][] getBoard() { return board; }
//
//    public Player getPlayer1() { return player1; }
//    public Player getPlayer2() { return player2; }
//}

public class TicTacToe {
    private final char[][] board;
    private final Player player1;
    private final Player player2;

    public TicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new char[3][3];
        resetBoard();
    }

    public void resetBoard() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board[r][c] = '-';
    }

    public boolean makeMove(int row, int col, Player player) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) return false;
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
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
        return false;
    }

    public boolean isDraw() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == '-') return false;
        // no empty squares and no winner
        return checkWinner() == null;
    }

    public char[][] getBoard() { return board; }

    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
}

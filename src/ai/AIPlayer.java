package ai;

import model.Player; //  Needed for Player inheritance

public class AIPlayer extends Player {

    private final char aiMark;
    private final char humanMark;

    public AIPlayer(String name, String symbol) {
        super(name, symbol);
        this.aiMark = symbol.charAt(0);
        this.humanMark = (aiMark == 'X') ? 'O' : 'X';
    }

    // Expects a 3x3 board with '-' for empty
    public int[] findBestMove(char[][] board) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == '-') {
                    board[r][c] = aiMark;
                    int score = minimax(board, 0, false);
                    board[r][c] = '-';
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{r, c};
                    }
                }
            }
        }

        // Fallback if no move found
        if (bestMove[0] == -1) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board[r][c] == '-') return new int[]{r, c};
                }
            }
        }
        return bestMove;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizing) {
        Character result = checkWinner(board);
        if (result != null) {
            if (result == aiMark) return 10 - depth;
            if (result == humanMark) return depth - 10;
            if (result == 'D') return 0;
        }

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board[r][c] == '-') {
                        board[r][c] = aiMark;
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[r][c] = '-';
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board[r][c] == '-') {
                        board[r][c] = humanMark;
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[r][c] = '-';
                    }
                }
            }
            return best;
        }
    }

    private Character checkWinner(char[][] b) {
        // Rows
        for (int r = 0; r < 3; r++)
            if (b[r][0] != '-' && b[r][0] == b[r][1] && b[r][1] == b[r][2])
                return b[r][0];

        // Cols
        for (int c = 0; c < 3; c++)
            if (b[0][c] != '-' && b[0][c] == b[1][c] && b[1][c] == b[2][c])
                return b[0][c];

        // Diagonals
        if (b[0][0] != '-' && b[0][0] == b[1][1] && b[1][1] == b[2][2]) return b[0][0];
        if (b[0][2] != '-' && b[0][2] == b[1][1] && b[1][1] == b[2][0]) return b[0][2];

        // Draw
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (b[r][c] == '-') return null;
        return 'D';
    }
}
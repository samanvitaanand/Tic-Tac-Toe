public class AIPlayer extends Player {

    private final char aiMark;
    private final char humanMark;

    public AIPlayer(String name, String symbol) {
        super(name, symbol);               // symbol is String
        this.aiMark = symbol.charAt(0);    // char for minimax
        this.humanMark = (aiMark == 'X') ? 'O' : 'X';
    }

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

        return bestMove;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizing) {
        Character result = checkWinner(board);
        if (result != null) {
            if (result == aiMark) return 10 - depth;
            if (result == humanMark) return depth - 10;
            if (result == 'D') return 0; // Draw
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board[r][c] == '-') {
                        board[r][c] = aiMark;
                        bestScore = Math.max(bestScore, minimax(board, depth + 1, false));
                        board[r][c] = '-';
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board[r][c] == '-') {
                        board[r][c] = humanMark;
                        bestScore = Math.min(bestScore, minimax(board, depth + 1, true));
                        board[r][c] = '-';
                    }
                }
            }
            return bestScore;
        }
    }

    private Character checkWinner(char[][] b) {
        // Rows
        for (int r = 0; r < 3; r++)
            if (b[r][0] != '-' && b[r][0] == b[r][1] && b[r][1] == b[r][2])
                return b[r][0];

        // Columns
        for (int c = 0; c < 3; c++)
            if (b[0][c] != '-' && b[0][c] == b[1][c] && b[1][c] == b[2][c])
                return b[0][c];

        // Diagonals
        if (b[0][0] != '-' && b[0][0] == b[1][1] && b[1][1] == b[2][2])
            return b[0][0];
        if (b[0][2] != '-' && b[0][2] == b[1][1] && b[1][1] == b[2][0])
            return b[0][2];

        // Draw
        boolean full = true;
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (b[r][c] == '-') full = false;

        if (full) return 'D';
        return null;
    }
}
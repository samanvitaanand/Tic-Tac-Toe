// Represents the tic tac toe board. Stores information about each cell on the board
class Board {
    // Two dimensional
    private char[][] board;

    public Board(){
        board = new char [3][3];
    }

    public void create_board() {
        // Loop through each row
        for(int i = 0; i < 3; i++) {
            // Loop through each column
            for(int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void display_board() {
        System.out.println(this);
    }

    public String toString() {
        String result = "";
        for (int i =0; i<3; i++) {
            for(int j = 0; j < 3; j++) {
                result += board[i][j] + " ";

            }
            result += "\n";
        }
        return result;
    }

    public boolean valid_play(Move move){
        int row = move.getRow();
        int col = move.getCol();

        // Check if it is in bounds of the 3x3 board
        if(row < 0 || row > 3 || col < 0 || col > 3) {
            return false;
        }
        // empty
        return board[row][col] == '-';
        //return true;
    }

    public void place_symbol(Move move){
        int row = move.getRow();
        int col = move.getCol();
        char symbol = move.getSymbol();
        //String symbol = move.getSymbol();

        board[row][col] = symbol;
    }

    public boolean check_win(char symbol) {
        return check_rows(symbol) || check_cols(symbol) || check_diag(symbol);
    }

    private boolean check_rows(char symbol){
        for(int row = 0; row < 3; row++) {
            if(board[row][0] == symbol && board[row][1] == symbol && board[row][2] == symbol) {
                return true;
            }
        }
        return false;
    }


    private boolean check_cols(char symbol) {
        for(int col = 0; col < 3; col++) {
            if(board[0][col] == symbol && board[1][col] == symbol && board[2][col] == symbol) {
                return true;
            }
        }
        return false;
    }


    private boolean check_diag(char symbol) {
        if(board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        if(board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }
        return false;
    }


    // rename to: isDraw()
    public boolean isDraw() {
        for(int r = 0; r < 3; r++) {
            for(int c =0; c < 3; c++) {
                if(board[r][c] == '-') {
                    return false; // if empty cell
                }
            }
        }
        return true;
    }

    // // rename to isEmpty()
    // public boolean isEmpty(int row, int col) {
    //     for(int r = 0; r < 3; r++) {
    //         for(int c =0; c < 3; c++) {
    //             if(board[r][c] == '-'){

    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    // }
}
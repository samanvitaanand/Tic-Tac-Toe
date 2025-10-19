// represents a move made by a player, storing th row and col where player placed their symbol.

class Move {
    private int row;
    private int col;
    private char symbol;
    //private String symbol;

    public Move(int row, int col, char symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getSymbol() {
        return symbol;
    }

}

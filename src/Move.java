//public class Move {
//    private final int row;
//    private final int col;
//    private final String symbol;
//
//    public Move(int row, int col, String symbol) {
//        this.row = row;
//        this.col = col;
//        this.symbol = symbol;
//    }
//
//    public int getRow() { return row; }
//    public int getCol() { return col; }
//    public String getSymbol() { return symbol; }
//
//    @Override
//    public String toString() {
//        return symbol + " at (" + row + ", " + col + ")";
//    }
//}

public class Move {
    private final int row;
    private final int col;
    private final char symbol;

    // Construct with char
    public Move(int row, int col, char symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
    }

    // Construct with String symbol (e.g. "X" or "O")
    public Move(int row, int col, String symbol) {
        this(row, col, symbol.charAt(0));
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public char getSymbol() { return symbol; }
    public String getSymbolString() { return String.valueOf(symbol); }
}

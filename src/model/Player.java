package model;

public class Player {
    private final String name;
    private final String symbol; // "X" or "O"
    private int score = 0;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() { return name; }
    public String getSymbol() { return symbol; }
    public char getSymbolChar() { return symbol.charAt(0); }
    public int getScore() { return score; }
    public void incrementScore() { score++; }
}
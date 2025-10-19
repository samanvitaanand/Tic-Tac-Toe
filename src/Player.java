// reresents the players in the game, storing their name, their symbol (X/O) and their score
class Player {

    String name;
    String symbol;
    int score;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}

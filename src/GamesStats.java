//Keeps tracks of number of wins and losses and draws
class GameStats {
    // had wins and losses here
    private int player1Wins;
    private int player2Wins;
    private int draws;


    public GameStats() {
        player1Wins = 0;
        player2Wins = 0;
        draws = 0;
        // this.player1Wins = player1Wins;
        // this.player2Wins = player2Wins;
        // this.draws = draws;
    }

    public int incrementPlayer1Score() {
        player1Wins+=1;
        return player1Wins;
    }

    public int incrementPlayer2Score(){
        player2Wins+=1;
        return player2Wins;
    }

    public int incrementDraw(){
        draws+=1;
        return draws;
    }

    public void updateStats(Player winner, Player player1, Player player2) {
        if (winner == null) {
            incrementDraw();
        } else if (winner == player1) {
            incrementPlayer1Score();
        } else if (winner == player2) {
            incrementPlayer2Score();
        }
    }


    public void displayStats(Player player1, Player player2) {
        System.out.println("Game stats: ");
        // System.out.println(player1.getName() + " has " + player1.getScore() + " wins.");
        // System.out.println(player2.getName() + " has " + player2.getScore() + " wins.");
        System.out.println(player1.getName() + " has " + player1Wins + " wins.");
        System.out.println(player2.getName() + " has " + player2Wins + " wins.");
        System.out.println("--------------");

    }
}

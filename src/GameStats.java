//public class GameStats {
//    private int player1Wins;
//    private int player2Wins;
//    private int draws;
//
//    public GameStats() {
//        player1Wins = 0;
//        player2Wins = 0;
//        draws = 0;
//    }
//
//    public void incrementPlayer1Score() { player1Wins++; }
//    public void incrementPlayer2Score() { player2Wins++; }
//    public void incrementDraw() { draws++; }
//
//    public int getPlayer1Wins() { return player1Wins; }
//    public int getPlayer2Wins() { return player2Wins; }
//    public int getDraws() { return draws; }
//
//    public void updateStats(Player winner, Player player1, Player player2) {
//        if (winner == null) incrementDraw();
//        else if (winner == player1) incrementPlayer1Score();
//        else if (winner == player2) incrementPlayer2Score();
//    }
//}

public class GameStats {
    private int draws = 0;
    private int p1Wins = 0;
    private int p2Wins = 0;

    public void updateStats(Player winner, Player p1, Player p2) {
        if (winner == null) {
            draws++;
        } else if (winner == p1) {
            p1Wins++;
        } else if (winner == p2) {
            p2Wins++;
        }
    }

    public void incrementDraw() { draws++; }

    public int getDraws() { return draws; }
    public int getP1Wins() { return p1Wins; }
    public int getP2Wins() { return p2Wins; }
}


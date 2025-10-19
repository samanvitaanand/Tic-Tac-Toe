package model;

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
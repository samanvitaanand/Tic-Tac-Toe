/**
 * @author <Samanvita>
 *
 */

import java.util.Scanner;

class Main {
    public static Scanner console = new Scanner(System.in);
    public static void main(String[] args) {
        Main main = new Main();
        main.play();
    }

    /**
     * Plays a series of TicTacToe games. This will get the players'
     * names and continue to play games until they don't want
     * to play anymore.
     */

    public void play() {
        System.out.println("Let's play TicTacToe!");
        System.out.println("---------------------");
        boolean playAgain = true;

        GameStats stats = new GameStats();

        while(playAgain) {

            System.out.println("Enter Player 1's name: ");
            String player1_name = console.nextLine();
            System.out.println("Enter Player 2's name: ");
            String player2_name = console.nextLine();

            System.out.println();
            System.out.println(player1_name + " will be X, " + player2_name + " will be O. ");
            System.out.println();

            Player player1 = new Player(player1_name, "X");
            Player player2 = new Player(player2_name, "O");

            TicTacToeGame game = new TicTacToeGame(player1, player2, stats);

            boolean game_over = game.startGame(console);

            if (game_over) {
                System.out.println();
                System.out.print("Do you want to play again? (y/n): ");
                String response = console.next();
                console.nextLine();
                playAgain = response.equals("y");

            } else {
                playAgain = false;
                System.out.println("Thanks for playing!");
            }
        }
    }
}
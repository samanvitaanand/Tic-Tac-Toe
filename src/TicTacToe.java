// manages overall game, including starting and ending of a game, player turns, and displaying results
import java.util.*;
class TicTacToeGame {
    Player player1;
    Player player2;
    Board board;
    GameStats gameStats;


    // Passing 2 player objects to constructor
    public TicTacToeGame(Player player1, Player player2, GameStats gameStats) {
        // assigning player objects to variables
        this.player1 = player1;
        this.player2 = player2;
        // creates new Board object and asssigns to board field
        this.board = new Board();
        // creates new GameStats object and assigns to gameStats field
        // probably not a new game stats per game
        this.gameStats = gameStats;
    }


    private Move getPlayerMove(Player curr_player, Scanner scanner) {
        System.out.println();
        System.out.println("==========");
        System.out.println(curr_player.getName() + "'s turn");
        System.out.println("==========");

        System.out.print("Enter row: ");
        int row = scanner.nextInt();
        System.out.print("Enter col: ");
        int col = scanner.nextInt();

        char symbol = curr_player.getSymbol().charAt(0);
        // System.out.println(symbol);

        //Move move = new Move(row, col, symbol);
        return new Move(row, col, symbol);

    }


    public boolean startGame(Scanner scanner) {
        board.create_board();
        // set game over is false when game starts
        boolean game_over = false;
        int currPlayerIndex = 0;
        boolean playAgain = true;

        while(!game_over) {
            Player curr_player;
            if(currPlayerIndex == 0) {
                curr_player = player1;
            } else {
                curr_player = player2;
            }

            Move move = getPlayerMove(curr_player, scanner);

            if(board.valid_play(move)) {
                board.place_symbol(move);

                System.out.println();
                board.display_board();
                char symbol = curr_player.getSymbol().charAt(0);
                if(board.check_win(symbol)) {
                    //if(board.check_win(curr_player.getSymbol())) {
                    System.out.println();
                    System.out.println(curr_player.getName() + " wins!");
                    gameStats.updateStats(curr_player, player1, player2);

                    System.out.println("----------END OF GAME----------");
                    game_over = true;
                    curr_player.incrementScore();

                } else if (board.isDraw()) {
                    System.out.println("Its a draw! ");
                    gameStats.updateStats(null, player1, player2);
                    System.out.println("----------END OF GAME----------");
                    game_over = true;
                }


                if(currPlayerIndex == 0) {
                    currPlayerIndex = 1; // Switch to player 2
                } else {
                    currPlayerIndex = 0; // Switch to player 1
                }

            } else {
                System.out.println("Invalid move: Please choose an empty cell");
            }
        }

        System.out.println();
        gameStats.displayStats(player1, player2);
        return game_over;
    }
}
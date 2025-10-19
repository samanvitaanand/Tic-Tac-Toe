import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::showMenu);
    }

    private static void showMenu() {
        JFrame frame = new JFrame("Tic Tac Toe - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Let's play Tic Tac Toe!", SwingConstants.CENTER);
        title.setBounds(50, 30, 300, 30);
        frame.add(title);

        JButton pvpButton = new JButton("Player vs Player");
        pvpButton.setBounds(100, 100, 200, 40);
        frame.add(pvpButton);

        JButton aiButton = new JButton("Player vs Ticky (AI 🤖)");
        aiButton.setBounds(100, 160, 200, 40);
        frame.add(aiButton);

        pvpButton.addActionListener(e -> { frame.dispose(); startGame(false); });
        aiButton.addActionListener(e -> { frame.dispose(); startGame(true); });

        frame.setVisible(true);
    }

    private static void startGame(boolean vsAI) {
        String player1Name;
        do {
            player1Name = JOptionPane.showInputDialog(null, "Enter Player 1's name:");
        } while (player1Name == null || player1Name.trim().isEmpty());
        Player player1 = new Player(player1Name.trim(), "X");

        Player player2;

        if (vsAI) {
            player2 = new AIPlayer("Ticky 🤖", "O"); // ✅ String, not char
            JOptionPane.showMessageDialog(null,
                    "Meet Ticky the TicTacToe genius! (•‿•)\n" +
                            player1.getName() + " will be X, Ticky will be O.");
        } else {
            String player2Name;
            do {
                player2Name = JOptionPane.showInputDialog(null, "Enter Player 2's name:");
            } while (player2Name == null || player2Name.trim().isEmpty());
            player2 = new Player(player2Name.trim(), "O");
            JOptionPane.showMessageDialog(null,
                    player1.getName() + " will be X, " + player2.getName() + " will be O.");
        }

        new TicTacToeUI(player1, player2);
    }
}
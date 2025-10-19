//import javax.swing.*;
//import java.awt.*;
//
//public class TicTacToeLauncher {
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Tic Tac Toe - Setup");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(350, 220);
//            frame.setLayout(new BorderLayout(10, 10));
//
//            JLabel label = new JLabel("Choose mode and your symbol", SwingConstants.CENTER);
//            label.setFont(new Font("Arial", Font.BOLD, 16));
//            frame.add(label, BorderLayout.NORTH);
//
//            JPanel center = new JPanel(new GridLayout(2, 1, 10, 10));
//
//            JPanel symbolPanel = new JPanel();
//            symbolPanel.add(new JLabel("Pick your symbol: "));
//            JButton xButton = new JButton("X");
//            JButton oButton = new JButton("O");
//            symbolPanel.add(xButton);
//            symbolPanel.add(oButton);
//
//            JPanel modePanel = new JPanel();
//            modePanel.add(new JLabel("Mode: "));
//            JButton pvpBtn = new JButton("Player vs Player");
//            JButton pvaiBtn = new JButton("Player vs AI");
//            modePanel.add(pvpBtn);
//            modePanel.add(pvaiBtn);
//
//            center.add(symbolPanel);
//            center.add(modePanel);
//            frame.add(center, BorderLayout.CENTER);
//
//            // Defaults
//            final char[] chosenSymbol = new char[]{'X'};
//            final boolean[] vsAI = new boolean[]{true};
//
//            xButton.addActionListener(e -> chosenSymbol[0] = 'X');
//            oButton.addActionListener(e -> chosenSymbol[0] = 'O');
//
//            pvaiBtn.addActionListener(e -> {
//                vsAI[0] = true;
//                startGame(chosenSymbol[0], true);
//                frame.dispose();
//            });
//
//            pvpBtn.addActionListener(e -> {
//                vsAI[0] = false;
//                startGame(chosenSymbol[0], false);
//                frame.dispose();
//            });
//
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });
//    }
//
//    private static void startGame(char playerSymbol, boolean vsAI) {
//        Player p1 = new Player("Player 1", String.valueOf(playerSymbol));
//        Player p2;
//        char other = playerSymbol == 'X' ? 'O' : 'X';
//        if (vsAI) p2 = new AIPlayer("Computer", String.valueOf(other));
//        else p2 = new Player("Player 2", String.valueOf(other));
//
//        new TicTacToeUI(p1, p2);
//    }
//}
//

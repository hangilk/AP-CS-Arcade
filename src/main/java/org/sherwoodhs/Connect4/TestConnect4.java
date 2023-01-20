package org.sherwoodhs.Connect4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.*;

/*

Class is no longer a JFrame itself, but has a JFrame component

Class no longer contains main method

Program is run through separate main class by calling (initializing?) TestConnect4 class method

Added reset & exit buttons with methods

Button claiming is now disabled after a player wins, until the game is reset

Added boolean isWin for if player has won

Separated ActionListener code from Button Initializer (iterator) code.
ActionListener code can be found at bottom of class.

Divided up program into distinct methods

----------------------------------------------------------

NOTE: Buttons visually clear, but remain claimed even after pressing reset.
      Need to figure out button claiming logic.

      Also buttons move down *kind of*
      Need to do more testing to figure out how/why

 */

public class TestConnect4 implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JPanel title;
    private JButton resetButton;
    private JButton exitButton;
    private JButton[] button;
    private boolean isWin;
    private int[][] board; // 2D array
    private int currentPlayer;

    private void ConfigureGame() {

        frame = new JFrame();
        panel = new JPanel();
        title = new JPanel();
        resetButton = new JButton();
        exitButton = new JButton();
        button = new JButton[42];
        board = new int [6][7];
        currentPlayer = 1;
        isWin = false;

        resetButton.setBackground(Color.ORANGE);
        resetButton.setText("Reset");
        resetButton.addActionListener(this::actionPerformed);

        exitButton.setBackground(Color.RED);
        exitButton.setText("Exit");
        exitButton.addActionListener(this::actionPerformed);

        title.add(resetButton);
        title.add(exitButton);

        frame.setTitle("Connect 4"); // set window properties
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        panel.setLayout(new GridLayout(6,7));

        for (int i = 0; i < 42; i++) {
            button[i] = new JButton();
            button[i].addActionListener(this::actionPerformed);
            // separated the ActionListener code (at bottom) from the button iterator
            panel.add(button[i]);
        }

        frame.add(title, BorderLayout.NORTH);
        frame.add(panel);
    }
    
    private void ResetGame() {
        for (int i = 0; i < 42; i++) {
            button[i].setBackground(new JButton().getBackground());
            button[i].setEnabled(true);
        }
        for (int x = 5; x >= 0; x--){
            for (int y = 6; y >= 0; y--){
                board[x][y] = 0;
            }
        }
        isWin = false;
    }

    private void ExitGame() {
        System.exit(0);
    }
    
    private boolean CheckWin(int row, int column) {
// check horizontal
        int count = 1;
        for (int i = column - 1; i >= 0; i--) {
            if (board[row][i] == currentPlayer) {
                count++;
            } else {
                break; // iterations done until [     ??     ]
            }
        }
        for (int i = column + 1; i < 7; i++) {
            if (board[row][i] == currentPlayer) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 4) {
            return true;
        }
        // check vertical
        count = 1;
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][column] == currentPlayer) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 4) {
            return true;
        }

        // check diagonal (top-left to bottom-right)
        count = 1;
        for (int i = 1; i < 7; i++) {
            int r = row + i;
            int c = column + i;
            if (r < 6 && c < 7 && board[r][c] == currentPlayer) {
                count++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 7; i++) {
            int r = row - i;
            int c = column - i;
            if (r >= 0 && c >= 0 && board[r][c] == currentPlayer) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 4) {
            return true;
        }
        return false; // checkWin false if no win conditions met
    }
    
    private void ClaimSpot() {

    }
    
    public TestConnect4() {
        ConfigureGame();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetButton) {
            ResetGame();

        } else if (e.getSource() == exitButton) {
            ExitGame();

        } else if (isWin == false) {

            JButton b = (JButton) e.getSource();
            int column = -1;

            for (int j = 0; j < 42; j++) { // column break
                if (b == button[j]) {
                    column = j % 7;
                    break;
                }

            }
            if (column != -1) {
                for (int row = 5; row >= 0; row--) {
                    if (board[row][column] == 0) {
                        board[row][column] = currentPlayer;
                        b.setBackground(currentPlayer == 1 ? Color.RED : Color.YELLOW);
                        b.setEnabled(false);
                        if (CheckWin(row, column)) {
                            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                            isWin = true;
                        } else {
                            currentPlayer = currentPlayer == 1 ? 2 : 1;
                        }
                        break;
                    }
                }
            }
        }

        System.out.println("\n\n\n");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }
}

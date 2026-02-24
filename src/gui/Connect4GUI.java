package src.gui;
import javax.swing.*;

import src.Board;
import src.Player;

import java.awt.*;

/**
 * A simple Connect 4 GUI using Java Swing. It allows two players to play against each other on a customizable board size.
 * The game checks for wins and draws, and displays appropriate messages.
 * Note: This GUI does not currently support playing against a CPU opponent.
 */
public class Connect4GUI extends JFrame {

    private Board board;
    private JLabel[][] cells;
    private JButton[] columnButtons;

    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private int size;

    public Connect4GUI(int size, Player p1, Player p2) {
        this.size = size;
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = p1;

        board = new Board(size);

        setTitle("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createTopButtons();
        createBoardGrid();

        pack();
        setMinimumSize(getPreferredSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates the top row of buttons that players click to drop their pieces into the columns. Each button corresponds to a column on the board.
     * When a button is clicked, it triggers the handleMove method to process the player's move and update the game state accordingly.
     * Note: The buttons are disabled once the game ends (win or draw) to prevent further moves.
     * 
     */
    private void createTopButtons() {
        JPanel topPanel = new JPanel(new GridLayout(1, size));
        columnButtons = new JButton[size];

        for (int col = 0; col < size; col++) {
            final int column = col;

            JButton btn = new JButton("▼");
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setPreferredSize(new Dimension(60, 60));

            btn.addActionListener(e -> handleMove(column));

            columnButtons[col] = btn;
            topPanel.add(btn);
        }

        add(topPanel, BorderLayout.NORTH);
    }

    /**
     * Creates the main grid of the board using JLabels to represent each cell. The grid is initialized with "-" to indicate empty cells.
     * The method also sets up the visual appearance of the cells, including font and borders. After creating the grid, it calls refreshBoard to display the initial state of the board.
     * Note: The cells are updated dynamically as players make their moves, and the refreshBoard method is responsible for reflecting the current state of the board on the GUI.
     */
    private void createBoardGrid() {
        JPanel boardPanel = new JPanel(new GridLayout(size, size));
        cells = new JLabel[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {

                JLabel label = new JLabel("-", SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 28));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setPreferredSize(new Dimension(60, 60));

                cells[r][c] = label;
                boardPanel.add(label);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
        refreshBoard();
    }

    /**
     * Handles a player's move when they click on a column button. It attempts to drop the player's piece into the selected column using the Board's dropPiece method.
     * @param column The index of the column where the player wants to drop their piece (0-based index).
     * 
     */
    private void handleMove(int column) {
        if (!board.dropPiece(column, currentPlayer.getSymbol())) {
            JOptionPane.showMessageDialog(this, "Column full!");
            return;
        }

        refreshBoard();

        if (board.checkWin(currentPlayer.getSymbol())) {
            JOptionPane.showMessageDialog(this,
                    currentPlayer.getName() + " wins!");
            disableButtons();
            return;
        }

        if (board.isFull()) {
            JOptionPane.showMessageDialog(this, "Draw!");
            disableButtons();
            return;
        }

        switchPlayer();
    }


    /**
     * Refreshes the board display by iterating through the grid and updating each cell's text to match the current state of the board.
     *
     */
    private void refreshBoard() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                cells[r][c].setText(String.valueOf(board.getCell(r, c)));
            }
        }
    }

    /**
     * Switches the current player after a move is made. 
     * It updates the currentPlayer variable to the other player and also updates the window title to indicate whose turn it is.
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        setTitle("Connect 4 - " + currentPlayer.getName() + "'s turn");
    }

    /**
     * Disables all the column buttons to prevent further moves after the game has ended (either by a win or a draw). 
     * This method is called when the game is over to ensure that players cannot continue to interact with the board.
     */
    private void disableButtons() {
        for (JButton b : columnButtons) {
            b.setEnabled(false);
        }
    }

    public static void main(String[] args) {

        String name1 = JOptionPane.showInputDialog("Player 1 name:");
        if (name1 == null || name1.isEmpty()) name1 = "Player 1";

        String name2 = JOptionPane.showInputDialog("Player 2 name:");
        if (name2 == null || name2.isEmpty()) name2 = "Player 2";

        int tempSize;

        try {
            String input = JOptionPane.showInputDialog("Board size (3-10):");
            tempSize = Integer.parseInt(input);
            if (tempSize < 3 || tempSize > 10) {
                tempSize = 7;
            }
        } catch (Exception e) {
            tempSize = 7;
        }

        final int size = tempSize;

        Player p1 = new Player(name1, 'X');
        Player p2 = new Player(name2, 'O');

        SwingUtilities.invokeLater(() ->
                new Connect4GUI(size, p1, p2)
        );

    }
}

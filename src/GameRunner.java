package src;

import java.util.Scanner;

/**
 * The GameRunner class serves as the entry point for the Connect 4 game. 
 */
public class GameRunner {
    public static void main(String[] args) {
        Player player1, player2;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name for Player 1 (X): ");
        String input = scanner.nextLine();
        if (input.isEmpty() || !input.matches("^[a-zA-Z0-9_]+$")) {
            player1 = new Player("Player 1", 'X');
        } else {
            player1 = new Player(input, 'X');
        }

        System.out.print("Enter name for Player 2 OR Enter CPU for the computer (O): ");
        input = scanner.nextLine();
        if (input.isEmpty() || !input.matches("^[a-zA-Z0-9_]+$")) {
            player2 = new Player("Player 2", 'O');
        } 
        else if (input.equals("CPU")) {
            player2 = new Player("CPU", 'O');
        } else {
            player2 = new Player(input, 'O');
        }

        System.out.println("Welcome " + player1 + " and " + player2 + "!");

        System.out.println("Please enter desired board size (3-10): ");

        int size = -1;

        while (size < 3 || size > 10) {
            try {
                size = scanner.nextInt();
                if (size < 3 || size > 10) {
                    System.out.println("Invalid board size. Please enter a number between 3 and 10.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        Board board = new Board(size + 1);

        boolean gameRunning = true;
        int currentPlayer = 1;

        if (!player2.isCPU) {
            while (gameRunning) {
                System.out.println("Current player: " + (currentPlayer == 1 ? player1 : player2));
                System.out.print("Enter column (1 - " + (size) + "): ");
                @SuppressWarnings("resource")
                Scanner inputScanner = new Scanner(System.in);
                int col = inputScanner.nextInt();
                char symbol = currentPlayer == 1 ? 'X' : 'O';
                board.dropPiece(col - 1, symbol);
                if(board.checkWin(symbol)) {
                    System.out.println("Congratulations " + (currentPlayer == 1 ? player1 : player2) + "! You have won the game!");
                    gameRunning = false;
                } else if (board.isFull()) {
                    System.out.println("The game is a draw!");
                    gameRunning = false;
                }
                currentPlayer = currentPlayer == 1 ? 2 : 1;
            }
        } 
        scanner.close();
    }
}
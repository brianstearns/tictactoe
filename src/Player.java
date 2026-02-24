package src;

/**
 * The Player class represents a player in the Connect 4 game.
 */
public class Player {
    String name;
    char symbol;
    boolean isCPU = false;

    public Player(String inputName, char symbol) {
        if (inputName == "CPU") {
            isCPU = true;
        } else {
            this.name = inputName;
        }
        this.symbol = symbol;
    }

    /**
     * Returns the name of the player. If the player is a CPU, it will return "CPU". Otherwise, it returns the custom name provided by the user.
     * @return The name of the player as a String. If the player is a CPU, it returns "CPU"; otherwise, it returns the custom name assigned to the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the symbol associated with the player, which is used to represent the player's pieces on the game board.
     * @return The character symbol representing the player's pieces (e.g., 'X' or 'O'). This symbol is used to identify the player's moves on the board during the game.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Overrides the toString() method to provide a string representation of the Player object, which includes the player's name and symbol.
     * @return A string in the format "PlayerName (Symbol)"
     */
    public String toString() {
        return name + " (" + symbol + ")";
    }
}

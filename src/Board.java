package src;

/**
 * The Board class represents the game board for Connect 4. 
 */
public class Board {

    private int size;
    private char[][] grid;

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = '-';
            }
        }
    }

    /**
     * Attempts to drop a piece with the given symbol into the specified column. 
     * @param column The index of the column where the piece should be dropped (0-based index).
     * @param symbol The character symbol representing the player's piece (e.g., 'X' or 'O').
     * @return true if the piece was successfully dropped into the column; false if the column is full and the piece cannot be dropped.
     */
    public boolean dropPiece(int column, char symbol) {

        for (int row = size - 1; row >= 0; row--) {
            if (grid[row][column] == '-') {
                grid[row][column] = symbol;
                return true;
            }
        }

        return false; 
    }

    /**
     * Returns the current state of the board as a 2D char array. 
     * @return A 2D char array representing the grid of the board
     */
    public char[][] getGrid() {
        return grid;
    }

    /**
     * Checks if the board is full by verifying if there are any empty cells (represented by '-') in the top row of the grid.
     * @return true if the board is full (no empty cells in the top row), indicating that no more moves can be made; false otherwise.
     */
    public boolean isFull() {
        for (int col = 0; col < size; col++) {
            if (grid[0][col] == '-') {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given symbol has won the game by having 4 in a row horizontally, vertically, or diagonally.
     * @param symbol The character symbol representing the player (e.g., 'X' or 'O') for whom we want to check the win condition.
     * @return true if the specified symbol has achieved 4 in a row in any direction, indicating a win; false otherwise.
     */
    public boolean checkWin(char symbol) {

        // Horizontal
        for (int r = 0; r < size; r++) {
            for (int c = 0; c <= size - 4; c++) {
                if (grid[r][c] == symbol &&
                    grid[r][c+1] == symbol &&
                    grid[r][c+2] == symbol &&
                    grid[r][c+3] == symbol) {
                    return true;
                }
            }
        }

        for (int c = 0; c < size; c++) {
            for (int r = 0; r <= size - 4; r++) {
                if (grid[r][c] == symbol &&
                    grid[r+1][c] == symbol &&
                    grid[r+2][c] == symbol &&
                    grid[r+3][c] == symbol) {
                    return true;
                }
            }
        }

        for (int r = 0; r <= size - 4; r++) {
            for (int c = 0; c <= size - 4; c++) {
                if (grid[r][c] == symbol &&
                    grid[r+1][c+1] == symbol &&
                    grid[r+2][c+2] == symbol &&
                    grid[r+3][c+3] == symbol) {
                    return true;
                }
            }
        }

        for (int r = 3; r < size; r++) {
            for (int c = 0; c <= size - 4; c++) {
                if (grid[r][c] == symbol &&
                    grid[r-1][c+1] == symbol &&
                    grid[r-2][c+2] == symbol &&
                    grid[r-3][c+3] == symbol) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns the character at the specified row and column in the grid. 
     * @param r The row index of the cell to retrieve (0-based index).
     * @param c The column index of the cell to retrieve (0-based index).
     * @return A char array containing the character at the specified cell. The character represents the current state of that cell on the board.
     */
    public char[] getCell(int r, int c) {
        return new char[]{grid[r][c]};
    }
}

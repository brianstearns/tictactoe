package src;

public class Board {
    int rows;
    char[][] boardArray;

    public Board(int rows) {
        fillBoard(rows);
    }


    public void fillBoard(int inputRows) {
        this.rows = inputRows - 1;
        boardArray = new char[rows][rows];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < rows; j++) {
                boardArray[i][j] = '-';
            }
        }
    }

    public Board() {
        this(3);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < rows; j++) {
                sb.append(boardArray[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public char[][] getBoardArray() {
        return boardArray;
    }

    public void makeMove(int col, char player) {
        boolean validMove = false;
        while(!validMove) {
            for (int row = rows - 1; row >= 0; row--) {
                if (boardArray[row][col] == '-') {
                    boardArray[row][col] = player;
                    validMove = true;
                    break;
                }
            }
            if (!validMove) {
                System.out.println("Column is full. Try a different column.");
                return;
            }
        }
        System.out.println(this);
    }

    public boolean hasWon() {
        // Check rows
        for (int i = 0; i < rows; i++) {
            char first = boardArray[i][0];
            if (first != '-' && allEqual(boardArray[i], first)) {
                System.out.println("Player " + first + " has won!");
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < rows; j++) {
            char first = boardArray[0][j];
            if (first != '-' && allEqual(getColumn(j), first)) {
                System.out.println("Player " + first + " has won!");
                return true;
            }
        }

        // Check diagonals
        char first = boardArray[0][0];
        if (first != '-' && allEqual(getDiagonal(0, 0, 1, 1), first)) {
            System.out.println("Player " + first + " has won!");
            return true;
        }

        first = boardArray[0][rows - 1];
        if (first != '-' && allEqual(getDiagonal(0, rows - 1, 1, -1), first)) {
            System.out.println("Player " + first + " has won!");
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (boardArray[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean allEqual(char[] row, char symbol) {
        for (char c : row) {
            if (c != symbol) {
                return false;
            }
        }
        return true;
    }

    private char[] getColumn(int col) {
        char[] column = new char[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = boardArray[i][col];
        }
        return column;
    }

    private char[] getDiagonal(int startRow, int startCol, int deltaRow, int deltaCol) {
        char[] diagonal = new char[rows];
        int row = startRow;
        int col = startCol;
        for (int i = 0; i < rows; i++) {
            diagonal[i] = boardArray[row][col];
            row += deltaRow;
            col += deltaCol;
        }
        return diagonal;
    }
}
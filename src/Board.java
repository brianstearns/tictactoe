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
        int winLength = 4;
        
        // Check rows
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= rows - winLength; j++) {
                char first = boardArray[i][j];
                if (first != '-' && checkConsecutive(boardArray[i], j, winLength, first)) {
                    System.out.println("Player " + first + " has won!");
                    return true;
                }
            }
        }

        // Check columns
        for (int j = 0; j < rows; j++) {
            char[] col = getColumn(j);
            for (int i = 0; i <= rows - winLength; i++) {
                char first = col[i];
                if (first != '-' && checkConsecutive(col, i, winLength, first)) {
                    System.out.println("Player " + first + " has won!");
                    return true;
                }
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int startRow = 0; startRow <= rows - winLength; startRow++) {
            for (int startCol = 0; startCol <= rows - winLength; startCol++) {
                char first = boardArray[startRow][startCol];
                if (first != '-' && checkDiagonalConsecutive(startRow, startCol, 1, 1, winLength, first)) {
                    System.out.println("Player " + first + " has won!");
                    return true;
                }
            }
        }

        // Check diagonals (top-right to bottom-left)
        for (int startRow = 0; startRow <= rows - winLength; startRow++) {
            for (int startCol = winLength - 1; startCol < rows; startCol++) {
                char first = boardArray[startRow][startCol];
                if (first != '-' && checkDiagonalConsecutive(startRow, startCol, 1, -1, winLength, first)) {
                    System.out.println("Player " + first + " has won!");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkConsecutive(char[] array, int startIndex, int length, char symbol) {
        for (int i = startIndex; i < startIndex + length; i++) {
            if (array[i] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonalConsecutive(int startRow, int startCol, int deltaRow, int deltaCol, int length, char symbol) {
        int row = startRow;
        int col = startCol;
        for (int i = 0; i < length; i++) {
            if (boardArray[row][col] != symbol) {
                return false;
            }
            row += deltaRow;
            col += deltaCol;
        }
        return true;
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

    private char[] getColumn(int col) {
        char[] column = new char[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = boardArray[i][col];
        }
        return column;
    }


    public int getCPUMove() {
        for (int col = 0; col < rows; col++) {
            for (int row = rows - 1; row >= 0; row--) {
                if (boardArray[row][col] == '-') {
                    return col;
                }
            }
        }
        return -1; // No valid moves
    }

}
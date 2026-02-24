package src;

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

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public String toString() {
        return name + " (" + symbol + ")";
    }
}

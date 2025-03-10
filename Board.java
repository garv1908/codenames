import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.IOException;

public class Board {
    int size;
    private String[][] board;
    private String[][] secretState;
    private String[][] revealedState;
    private String startingTeam;

    public Board(int size) {
        board = new String[size][size];
        this.size = size;

        // choose a random 25 words from the word list (given that the boardsize is 5) 
        // assign 8 to red, 8 to blue, 7 to neutral, 1 to assassin
        initializeBoard();
    }

    private void initializeBoard() {
        List<String> words = new ArrayList<>();
        try {
            words = Files.readAllLines(Paths.get("words.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(words);
        List<String> selectedWords = words.subList(0, 25);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = selectedWords.get(i * size + j);
            }
        }
        setSecretState();
    }

    private void setSecretState() {
        secretState = new String[size][size];
        List<String> colors = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            colors.add("red");
            colors.add("blue");
        }
        for (int i = 0; i < 8; i++) {
            if (Math.random() < 0.5) {
            colors.add("red");
            startingTeam = "red";
        } else {
            colors.add("blue");
            startingTeam = "blue";
        }
        }
        for (int i = 0; i < 7; i++) {
            colors.add("neutral");
        }
        colors.add("assassin");
        Collections.shuffle(colors);

        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.println(i + " " + j + " " + k);
                secretState[i][j] = colors.get(k);
                k++;
            }
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].length() < 8) res += board[i][j] + "\t\t";
                else res += board[i][j] + "\t";
            }
            res += "\n";
        }
        return res;
    }

    public String printSecretState() {
        String res = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (secretState[i][j].length() < 8) res += secretState[i][j] + "\t\t";
                else res += secretState[i][j] + "\t";
            }
            res += "\n";
        }
        return res;
    }

    public String[][] getBoard() {
        return board;
    }

    public String getStartingTeam() {
        return startingTeam;
    }
    
}

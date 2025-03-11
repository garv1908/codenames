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

    public Board(int size)
    {
        board = new String[size][size];
        revealedState = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            revealedState[i][j] = "x";
            }
        }
        this.size = size;

        // choose a random 25 words from the word list (given that the boardsize is 5) 
        // assign 8 to red, 8 to blue, 7 to neutral, 1 to assassin
        initializeBoard();
    }

    private void initializeBoard()
    {
        List<String> words = new ArrayList<>();
        try {
            words = Files.readAllLines(Paths.get("words.txt"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Collections.shuffle(words);
        List<String> selectedWords = words.subList(0, 25);

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                board[i][j] = selectedWords.get(i * size + j);
            }
        }
        setSecretState();
    }

    private void setSecretState()
    {
        secretState = new String[size][size];
        List<String> colors = new ArrayList<>();
        for (int i = 0; i < 8; i++)
        {
            colors.add("red");
            colors.add("blue");
        }
        for (int i = 0; i < 8; i++)
        {
            if (Math.random() < 0.5)
            {
                colors.add("red");
                startingTeam = "red";
            } else {
                colors.add("blue");
                startingTeam = "blue";
            }
        }
        for (int i = 0; i < 7; i++)
        {
            colors.add("neutral");
        }
        colors.add("assassin");
        Collections.shuffle(colors);

        int k = 0;
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                secretState[i][j] = colors.get(k);
                k++;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String word = String.format("%-15s | %s", board[i][j], revealedState[i][j]);
                res.append(word).append(revealedState[i][j] == "neutral" ? "\t" : "\t\t");
            }
            res.append("\n");
        }
        return res.toString();
    }

    public String printSecretState()
    {
        String res = "";
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (secretState[i][j].length() < 8) res += secretState[i][j] + "\t\t";
                else res += secretState[i][j] + "\t";
            }
            res += "\n";
        }
        return res;
    }

    public boolean makeGuess(int row, int col, Team currentTeam, Team enemyTeam)
    {
        boolean isTurnOver = true;
        System.out.println("You guessed: " + board[row][col]);
        if (secretState[row][col].equals("red"))
        {
            if (currentTeam.getTeamName() == "red")
            {
                System.out.println("Correct! Red team gets a point!");
                currentTeam.incrementScore();
                isTurnOver = false;
            } else {
                System.out.println("Incorrect! Blue team gets a point!");
                enemyTeam.incrementScore();
                isTurnOver = true;
            }
        } else if (secretState[row][col].equals("blue")) {
            if (currentTeam.getTeamName() == "blue")
            {
                System.out.println("Correct! Blue team gets a point!");
                currentTeam.incrementScore();
                isTurnOver = false;
            } else {
                System.out.println("Incorrect! Red team gets a point!");
                enemyTeam.incrementScore();
                isTurnOver = true;
            }
        } else if (secretState[row][col].equals("neutral")) {
            System.out.println("You hit a neutral word! Your turn is over!");
            isTurnOver = true;
        } else if (secretState[row][col].equals("assassin")) {
            System.out.println("You hit the assassin! Game over!");
            isTurnOver = true;
        }
        revealedState[row][col] = secretState[row][col];
        return isTurnOver;
    }

    public String[][] getBoard()
    {
        return board;
    }

    public String getStartingTeam()
    {
        return startingTeam;
    }

    public boolean isGameOver()
    {
        int redCount = 0;
        int blueCount = 0;

        if (startingTeam == "red") redCount++;
        else blueCount++;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (revealedState[i][j].equals("red")) redCount++;
                else if (revealedState[i][j].equals("blue")) blueCount++;
                else if (revealedState[i][j].equals("assassin")) return true;
            }
        }
        return redCount == 8 || blueCount == 8;
    }
    
}

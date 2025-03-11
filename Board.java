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

    /**
     * Constructor to initialize the board with the given size.
     * It sets up the revealed state and initializes the board with words.
     * @param size the size of the board
     */
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

    /**
     * Initializes the board with words from the word list.
     * It shuffles the words and assigns them to the board.
     */
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

    /**
     * Sets the secret state of the board with colors.
     * It assigns colors to the words and determines the starting team.
     */
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

    /**
     * Returns a string representation of the board with revealed states.
     * @return the string representation of the board
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String word = String.format("%-15s | %s", board[i][j], revealedState[i][j]);
                res.append(word).append((revealedState[i][j] == "neutral" || revealedState[i][j] == "assassin") ? "\t" : "\t\t");
            }
            res.append("\n");
        }
        return res.toString();
    }

    /**
     * Prints the secret state of the board.
     * @return the string representation of the secret state
     */
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

    /**
     * Makes a guess on the board and updates the game state.
     * @param row the row of the guessed word
     * @param col the column of the guessed word
     * @param currentTeam the team making the guess
     * @param enemyTeam the opposing team
     * @return true if the turn is over, false otherwise
     */
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

    /**
     * Returns the current state of the board.
     * @return the board
     */
    public String[][] getBoard()
    {
        return board;
    }

    /**
     * Returns the starting team of the game.
     * @return the starting team
     */
    public String getStartingTeam()
    {
        return startingTeam;
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise
     */
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

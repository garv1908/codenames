import java.util.Scanner;

public class Operative extends Player {

    /**
     * Constructor for the Operative class.
     * Initializes the Operative with a specific team.
     * 
     * @param team The team to which the operative belongs.
     */
    public Operative(Team team)
    {
        super(team);
    }
    
    /**
     * Prompts the operative to guess a location on the board.
     * Validates the input and makes a guess on the board.
     * 
     * @param board The game board.
     * @param scanner The scanner to read user input.
     * @param enemyTeam The enemy team.
     * @return true if the guess is correct, false otherwise.
     */
    public boolean guess(Board board, Scanner scanner, Team enemyTeam)
    {
        System.out.print("Enter your guess (format: row col): ");
        String guess = scanner.nextLine();
        while (!guess.matches("\\d+ \\d+"))
        {
            System.out.print("Invalid input. Enter your guess (example: 2 4): ");
            guess = scanner.nextLine();
        }
        System.out.println("You guessed: " + guess);
        int row = Integer.parseInt(guess.split(" ")[0]) + 1;
        int col = Integer.parseInt(guess.split(" ")[1]) + 1;
        return board.makeGuess(row, col, getTeam(), enemyTeam);
    }
}

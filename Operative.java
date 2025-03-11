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
        System.out.print("\nEnter your guess (format: row col) ranges 1-5: ");
        String guess = scanner.nextLine();

        int row = 0, col;

        while (true) {
            if (guess.matches("\\d+ \\d+"))
            {
                row = Integer.parseInt(guess.split(" ")[0]) - 1;
                col = Integer.parseInt(guess.split(" ")[1]) - 1;
                if (row >= 0 && row < 5 && col >= 0 && col < 5)
                {
                    break;
                }
            }
            System.out.print("Invalid input. Enter your guess (example: 1 5): ");
            guess = scanner.nextLine();
        }

        System.out.println("You guessed: " + guess);
        System.out.println("\n");

        return board.makeGuess(row, col, getTeam(), enemyTeam);
    }
}

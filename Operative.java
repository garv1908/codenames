import java.util.Scanner;
public class Operative extends Player {
    public Operative(Team team)
    {
        super(team);
    }
    
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

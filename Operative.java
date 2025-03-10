import java.util.Scanner;
public class Operative extends Player {
    public Operative(Team team)
    {
        super(team);
    }
    
    public void guess(Board board, Scanner scanner)
    {
        System.out.print("Enter your guess (format: row col): ");
        String guess = scanner.nextLine();
        while (!guess.matches("\\d+ \\d+"))
        {
            System.out.print("Invalid input. Enter your guess (example: 2 4): ");
            guess = scanner.nextLine();
        }
        System.out.println("You guessed: " + guess);
        board.makeGuess(Integer.parseInt(guess.split(" ")[0]), Integer.parseInt(guess.split(" ")[1]), getTeam().getTeamName());
    }
     
    public void endTurn()
    {
        System.out.println("I'm ending my turn");
    }

}

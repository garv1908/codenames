import java.util.Scanner;

public class Spymaster extends Player {

    public Spymaster(Team team) {
        super(team);
    }
    public void giveClue(Scanner scanner) {
        System.out.print("Enter your clue: ");
        String clue = scanner.nextLine();
        while (!clue.matches("\\w+ \\d+"))
        {
            System.out.print("Invalid input. Enter your clue (format: word number): ");
            clue = scanner.nextLine();
        }
        System.out.println("Clue given: " + clue);
    }
}

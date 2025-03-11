import java.util.Scanner;

public class Spymaster extends Player {

    /**
     * Constructor for Spymaster class.
     * @param team The team to which the spymaster belongs.
     */
    public Spymaster(Team team) {
        super(team);
    }

    /**
     * Prompts the spymaster to give a clue.
     * The clue must be in the format of a word followed by a number (e.g., "metal 2").
     * @param scanner The Scanner object to read input from the console.
     */
    public void giveClue(Scanner scanner) {
        System.out.print("Enter your clue (example: metal 2): ");
        String clue = scanner.nextLine();
        while (!clue.matches("\\w+ \\d+")) {
            System.out.print("Invalid input. Enter your clue (example: metal 2): ");
            clue = scanner.nextLine();
        }
        System.out.println("Clue given: " + clue);
    }
}

import java.util.Scanner;

public class ProjectOneTester {
    /**
     * The main method to start and run the Codenames game.
     * Initializes the teams, spymasters, operatives, and the board.
     * Manages the game flow and handles the main game loop.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        Team red = new Team("red");
        Team blue = new Team("blue");

        Spymaster redSpymaster = new Spymaster(red);
        Spymaster blueSpymaster = new Spymaster(blue);
        
        Operative redOperative = new Operative(red);
        Operative blueOperative = new Operative(blue);

        // possible integration for bigger sized boards, but would require some modification in
        // the initialization of the secretState within thee Board class
        Board board = new Board(5); // 5x5 board

        System.out.println("\n\nWelcome to Codenames!");
        System.out.println("Codenames is a game where two teams compete to uncover all their words on the board.");
        System.out.println("Each team has a Spymaster who gives one-word clues to help their team guess the words.");
        System.out.println("The Operatives on each team try to guess the words based on the Spymaster's clues.");
        System.out.println("Be careful! There are also neutral words and an assassin word that ends the game if guessed.");
        System.out.println("The first team to uncover all their words wins. Let's get started!");
        Game.sleep(5);
        System.out.println("\n\nOperatives, please close your eyes! The Spymasters will now obtain the state of the board!");

        Game.sleep(5);
        for (int i = 3; i > 0; i--)
        {
            System.out.println("Spymasters will obtain the state of the board in " + i + "...");
            Game.sleep(1);
        }

        System.out.println("\nSpymasters, be ready to record this! The board will be cleared in 10 seconds!");
        Game.sleep(1);
        System.out.println("Here is the state of the board:\n");
        System.out.println(board.printSecretState());

        Game.sleep(7);
        for (int i = 3; i > 0; i--)
        {
            System.out.println("Clearing the state in " + i + "...");
            Game.sleep(1);
        }

        // Clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Alright players, let's get started!");
        System.out.println("Here is the board:\n");
        System.out.println(board.toString());

        String startingTeam = board.getStartingTeam();
        String currentTeam = startingTeam;
        System.out.println("Starting team: " + startingTeam);

        Scanner scanner = new Scanner(System.in);

        // main game loop
        while (true)
        {
            boolean isTurnOver;
            System.out.println("\n\n");
            if (currentTeam == "red")
            {
                System.out.println("Red team's turn!");
                redSpymaster.giveClue(scanner);
                isTurnOver = redOperative.guess(board, scanner, blue);
                System.out.println("Here is the board:\n");
                System.out.println(board.toString());
                if (board.isGameOver())
                {
                    System.out.println("Game over!");
                    break;
                }
                if (isTurnOver) currentTeam = "blue";
            } else {
                System.out.println("Blue team's turn!");
                blueSpymaster.giveClue(scanner);
                isTurnOver = blueOperative.guess(board, scanner, red);
                System.out.println("Here is the board:\n");
                System.out.println(board.toString());
                if (board.isGameOver())
                {
                    System.out.println("Game over!");
                    break;
                }
                if (isTurnOver) currentTeam = "red";
            }
            Game.printScore(red, blue);
        }
    }
}

import java.util.Scanner;

public class Game
{

    public static void sleep(int seconds)
    {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
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

        System.out.println("Welcome to Codenames!");
        System.out.println("Operatives, please close your eyes! The Spymasters will now obtain the state of the board!");

        sleep(5);
        for (int i = 3; i > 0; i--)
        {
            System.out.println("Spymasters will obtain the state of the board in " + i + "...");
            sleep(1);
        }

        System.out.println("Spymasters, be ready to record this! The board will be cleared in 10 seconds!");
        sleep(1);
        System.out.println("Here is the state of the board:");
        System.out.println(board.printSecretState());

        sleep(7);
        for (int i = 3; i > 0; i--)
        {
            System.out.println("Clearing the state in " + i + "...");
            sleep(1);
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
            if (currentTeam == "red")
            {
                System.out.println("Red team's turn!");
                redSpymaster.giveClue(scanner);
                redOperative.guess(board, scanner);
                System.out.println("Here is the board:\n");
                System.out.println(board.toString());
                if (board.isGameOver())
                {
                    System.out.println("Game over!");
                    break;
                }
                currentTeam = "blue";
            } else {
                System.out.println("Blue team's turn!");
                blueSpymaster.giveClue(scanner);
                blueOperative.guess(board, scanner);
                System.out.println("Here is the board:\n");
                System.out.println(board.toString());
                if (board.isGameOver())
                {
                    System.out.println("Game over!");
                    break;
                }
                currentTeam = "red";
            }
        }
    }
}
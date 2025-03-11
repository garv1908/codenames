public class Game
{
    /**
     * Pauses the execution for a given number of seconds.
     * This method is used to create delays in the game flow.
     *
     * @param seconds the number of seconds to sleep
     */
    public static void sleep(int seconds)
    {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the current score of both teams.
     * This method is used to display the score after each turn.
     *
     * @param red the red team
     * @param blue the blue team
     */
    public static void printScore(Team red, Team blue)
    {
        System.out.println(red.toString());
        System.out.println(blue.toString());
    }

    
}
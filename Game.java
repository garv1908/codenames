public class Game
{
    public static void main(String[] args) {
        Team red = new Team("red");
        Team blue = new Team("blue");

        Spymaster redSpymaster = new Spymaster(red);
        Spymaster blueSpymaster = new Spymaster(blue);
        
        Operative redOperative = new Operative(red);
        Operative blueOperative = new Operative(blue);

        String[] words = 
        

        Board board = new Board(5); // 5x5 board

        
    }
}
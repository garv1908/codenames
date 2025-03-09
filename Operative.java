public class Operative extends Player {
    public Operative(Team team) {
        super(team);
    }
    public void guess() {
        System.out.println("I'm guessing");
    }
    public void endTurn() {
        System.out.println("I'm ending my turn");
    }
}

public class Team implements Scorable {
    private String teamName;
    private int score;

    public void incrementScore() {
        score++;
    }
    
    public Team(String teamName) {
        this.teamName = teamName;
        this.score = 0;
    }
}

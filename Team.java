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

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void updateScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Team: " + teamName + " Score: " + score;
    }

    public String getTeamName() {
        return teamName;
    }
}

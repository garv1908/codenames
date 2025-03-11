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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        
        if (!(obj instanceof Team)) return false;
        Team team = (Team) obj;
        return teamName.equals(team.teamName);
    }

    public String getTeamName() {
        return teamName;
    }
}

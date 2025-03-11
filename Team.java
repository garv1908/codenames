public class Team implements Scorable {
    private String teamName;
    private int score;

    /**
     * Increments the team's score by one.
     */
    @Override
    public void incrementScore() {
        score++;
    }

    /**
     * Returns the current score of the team.
     * @return the current score
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Updates the team's score to the specified value.
     * @param score the new score
     */
    @Override
    public void updateScore(int score) {
        this.score = score;
    }

    /**
     * Returns a string representation of the team, including the team name and score.
     * @return a string representation of the team
     */
    @Override
    public String toString() {
        return "Team: " + teamName + " Score: " + score;
    }

    /**
     * Constructs a new Team with the specified name and an initial score of 0.
     * @param teamName the name of the team
     */
    public Team(String teamName) {
        this.teamName = teamName;
        this.score = 0;
    }

    /**
     * Checks if this team is equal to another object.
     * Two teams are considered equal if they have the same team name.
     * @param obj the object to compare to
     * @return true if the teams are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        
        if (!(obj instanceof Team)) return false;
        Team team = (Team) obj;
        return teamName.equals(team.teamName);
    }

    /**
     * Returns the name of the team.
     * @return the team name
     */
    public String getTeamName() {
        return teamName;
    }
}

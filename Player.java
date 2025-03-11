public abstract class Player {
    private Team team;

    /**
     * Constructor for the Player class.
     * Initializes the player with a specific team.
     * 
     * @param team the team to which the player belongs
     */
    public Player(Team team) {
        this.team = team;
    }

    /**
     * Gets the team of the player.
     * 
     * @return the team of the player
     */
    public Team getTeam() {
        return team;
    }
}

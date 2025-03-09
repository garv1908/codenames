public abstract class Player {
    private Team team;

    public Player(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}

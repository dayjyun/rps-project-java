/*
The Player is an over-arching abstract class that is in charge of designating what is required for each player; whether that may be for a human player, or for the AI. Each
instance is required to have a name and keep track of its points value. There are getters for whenever the name is required or whenver the points are used to be displayed back
to the user, and there are setters for every time the name gets updated or a player's point value is changed.
 */

public abstract class Player {
    private String name;
    private int points;

    public Player(){}

    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints() {
        this.points += 1;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return "Points=" + points + " Name";
    }
}

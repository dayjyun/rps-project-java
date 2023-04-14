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
//    public String toString() {
//        return "Points=" + points + " Name";
//    }
    public String toString() {
        return name + " " + points;
    }
}

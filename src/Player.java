public class Player {
    private String name;
    private int points;
    Player cpu;

    public Player(String name, int points) {
        this.name = name;
        this.points = points;
        this.cpu = cpu;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}

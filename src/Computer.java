import java.util.Random;

public class Computer extends Player {
    public Computer(String name, int points) {
        super(name, points);
    }

    public static String computerMove() {
        String[] correctInput = {"r", "s", "p"};
        int randomIndex = new Random().nextInt(correctInput.length);
        return correctInput[randomIndex];
    }

}

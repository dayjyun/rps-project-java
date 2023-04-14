import java.util.Random;

public class Computer extends Player implements ComputerMove {
    public Computer(String name, int points) {
        super(name, points);
    }

    @Override
    public String computerMove() {
        String[] correctInput = {"r", "s", "p"};
        int randomIndex = new Random().nextInt(correctInput.length);
        return correctInput[randomIndex];
    }
}
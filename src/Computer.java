import java.util.Random;

/*
The Computer is in charge of creating a virtual playing experience if there is no other live-player available. It inherits the properties found in the parent to keep code
modular. The method it overwrites is the computerMove which is set to randomly select a value to use against playerOne.
 */

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
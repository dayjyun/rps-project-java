import java.io.IOException;
import java.util.Scanner;

/*
The GameAlgorithm class is in charge of determining whether the player would like to play against the in-game AI, or with another player. The if-statement for case 2 is required
as there is a session storage in play as all methods pass at least playerOne and playerTwo objects. The moment case 1 is selected, the entire game will persist playerTwo as the
computer. Case 2 handles that hidden bug since it will create a new instance for a HumanPlayer and reassigns the playerTwo Computer to simply be a human.

Case 3 handles the name change if the user desires to change any of the player's names. Lastly, the default case is in player to make sure the user does not enter any unwanted
inputs and using recursion to simulate the menu remaining unchanged.
 */

public class GameAlgorithm {
    public static void choosePlayer(Player playerOne, Player playerTwo, int ties, Scanner playerOneInput) throws IOException {
        Computer cpu = new Computer("CPU", 0);

        UI.choosePLayerText();

        String userInput = playerOneInput.nextLine();

        switch (userInput) {
            case "1" -> Main.playGame(playerOne, cpu, ties, playerOneInput);
            case "2" -> {
                if(playerTwo instanceof Computer) {
                    playerTwo = new HumanPlayer("Player Two", 0);
                }
                Main.playGame(playerOne, playerTwo, ties, playerOneInput);
            }
            case "3" -> Main.changePlayerName(playerOne, playerTwo, ties, playerOneInput);
            default -> {
                System.out.println("Invalid input");
                choosePlayer(playerOne, playerTwo, ties, playerOneInput);
            }
        }
    }
}
import java.util.Scanner;

public class GameAlgorithm {
    public static void choosePlayer(Player playerOne, Player playerTwo, int ties, Scanner playerOneInput) {
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

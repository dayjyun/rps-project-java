import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void menu() {
        Player playerOne = new Player("Player One", 0);
        Player playerTwo = new Player("Player Two", 0);
        int ties = 0;

        System.out.println("""
                Welcome to Rock Paper Scissors
                    What would you like to do?
                        Play (P)
                        History (H)
                        Quit (Q)""");

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        if (userInput.equalsIgnoreCase("play") || userInput.equalsIgnoreCase("p")) {
            playGame(playerOne, playerTwo, ties, input);
        } else if (userInput.equalsIgnoreCase("history") || userInput.equalsIgnoreCase("h")) {
            showHistory();
        } else if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("q")) {
            quitGame(input);
        }
    }

    // Player2 gets passed in as either player2 or cpu;

    public static void playGame(Player playerOne, Player playerTwo, int ties, Scanner input) {
//        playerOnePoints = 0;
//        playerTwoPoints = 0;
//        ties = 0;

        String[] correctInput = {"r", "s", "p"};
        String[] correctInputLong = {"rock", "scissors", "paper"};
        int randomIndex = new Random().nextInt(correctInput.length);
        String computerMove = correctInput[randomIndex].toUpperCase();

//        Scanner input = new Scanner(System.in);

        // Winning Conditions
    /*
        Rock v Paper (Paper)
        Rock v Scissors (Rock)
        Paper v Rock (Paper)
        Paper v Scissors (Scissors)
        Scissors v Rock (Rock)
        Scissors v Paper (Scissors)
     */

        System.out.println("""
                Choose your move
                    Rock (R)
                    Paper (P)
                    Scissors (S)
                    Quit (Q)""");

        String playerOneInput = input.nextLine().toLowerCase();
        if ((Arrays.asList(correctInput).contains(playerOneInput) || Arrays.asList(correctInputLong).contains(playerOneInput)) && Arrays.asList(correctInput).contains(computerMove)) {
            if ((playerOneInput.equalsIgnoreCase("r") || playerOneInput.equalsIgnoreCase("rock")) && computerMove.equalsIgnoreCase("s")) {
                System.out.println("Player One picked " + playerOneInput + '\n' + "Player Two picked " + computerMove + '\n' + "Player One Wins!" + '\n');
//                player1wins++;
                playGame(playerOne, playerTwo, ties, input);
            } else if ((playerOneInput.equalsIgnoreCase("r") || playerOneInput.equalsIgnoreCase("rock")) && computerMove.equalsIgnoreCase("p")) {
                System.out.println("Player One picked " + playerOneInput + '\n' + "Player Two picked " + computerMove + '\n' + "Player Two Wins!" + '\n');
//                player2wins++;
                playGame(playerOne, playerTwo, ties, input);
            } else if ((playerOneInput.equalsIgnoreCase("p") || playerOneInput.equalsIgnoreCase("paper")) && computerMove.equalsIgnoreCase("r")) {
                System.out.println("Player One picked " + playerOneInput + '\n' + "Player Two picked " + computerMove + '\n' + "Player One Wins!" + '\n');
//                player1wins++;
                playGame(playerOne, playerTwo, ties, input);
            } else if ((playerOneInput.equalsIgnoreCase("p") || playerOneInput.equalsIgnoreCase("paper")) && computerMove.equalsIgnoreCase("s")) {
                System.out.println("Player One picked " + playerOneInput + '\n' + "Player Two picked " + computerMove + '\n' + "Player Two Wins!" + '\n');
//                player2wins++;
                playGame(playerOne, playerTwo, ties, input);
            } else if ((playerOneInput.equalsIgnoreCase("s") || playerOneInput.equalsIgnoreCase("scissors")) && computerMove.equalsIgnoreCase("p")) {
                System.out.println("Player One picked " + playerOneInput + '\n' + "Player Two picked " + computerMove + '\n' + "Player One Wins!" + '\n');
//                player1wins++;
                playGame(playerOne, playerTwo, ties, input);
            } else if ((playerOneInput.equalsIgnoreCase("s") || playerOneInput.equalsIgnoreCase("scissors")) && computerMove.equalsIgnoreCase("r")) {
                System.out.println("Player One picked " + playerOneInput + '\n' + "Player Two picked " + computerMove + '\n' + "Player Two Wins!" + '\n');
//                player2wins++;
                playGame(playerOne, playerTwo, ties, input);
            } else {
                System.out.println("Player One picked " + playerOneInput + '\n' + "Player Two picked " + computerMove + '\n' + "Draw!" + '\n');
                ties++;
                playGame(playerOne, playerTwo, ties, input);
            }
        } else if (playerOneInput.equalsIgnoreCase("q") || playerOneInput.equalsIgnoreCase("quit")){
            System.out.println("Goodbye");
            input.close();
        } else {
            System.out.println("Incorrect Input");
            playGame(playerOne, playerTwo, ties, input);
        }
        System.out.print("Player One: " + playerOne.getPoints() + " Player Two: " + playerTwo.getPoints() + " Ties: " + ties);
    }
    // sends game data to a gameList


    public static void showHistory() {
        System.out.println("Shows history");
        // reads data from gameList
    }

    public static void quitGame(Scanner input) {
        System.out.println("Goodbye");
        input.close();
        // TODO reopen the terminal
    }

    public static void main(String[] args) {
        menu();
    }

    public static void writeFile(String name) {
        Path pathToFile = Paths.get("rps-project-java/src/output.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(pathToFile.toUri()).toFile(), true));
            writer.write(name);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

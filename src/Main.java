import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void menu() {
        System.out.println("""
                Welcome to Rock Paper Scissors
                    What would you like to do?
                        Play (P)
                        History (H)
                        Quit (Q)""");

        Scanner playerOneInput = new Scanner(System.in);
        String userInput = playerOneInput.nextLine();

        if (userInput.equalsIgnoreCase("play") || userInput.equalsIgnoreCase("p")) {
            choosePlayer(playerOneInput);
        } else if (userInput.equalsIgnoreCase("history") || userInput.equalsIgnoreCase("h")) {
            showHistory();
        } else if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("q")) {
            quitGame(playerOneInput);
        } else {
            System.out.println("Incorrect Input" + '\n');
            menu();
        }
    }

    public static void choosePlayer(Scanner playerOneInput) {
        Player playerOne = new Player("Player One", 0);
        int ties = 0;

        System.out.println("Choose your player");
        System.out.println("1. Single player");
        System.out.println("2. Two Players");
        String userInput = playerOneInput.nextLine();

        if (userInput.equals("1")) {
            playGame(playerOne, new Computer("Cpu", 0), ties, playerOneInput);
        } else {
            playGame(playerOne, new Player("Player Two", 0), ties, playerOneInput);
        }
    }

    public static void playGame(Player playerOne, Player playerTwo, int ties, Scanner playerOneInput) {
        // TODO integrate switch player
        String[] correctInput = {"r", "s", "p"};
        String[] correctInputLong = {"rock", "scissors", "paper"};
        Scanner playerTwoInput = new Scanner(System.in);

        System.out.println("""
                                
                GAME
                =====
                Choose your move
                    Rock (R)
                    Paper (P)
                    Scissors (S)
                    Quit (Q)""");

        String playerOneMove = playerOneInput.nextLine().toLowerCase();
        String playerTwoMove;

        if (playerTwo.getName().equalsIgnoreCase("cpu")) {
            playerTwoMove = Computer.computerMove();
        } else {
            playerTwoMove = playerTwoInput.nextLine().toLowerCase();
        }

        if ((Arrays.asList(correctInput).contains(playerOneMove) || Arrays.asList(correctInputLong).contains(playerOneMove)) && (Arrays.asList(correctInput).contains(playerTwoMove) || Arrays.asList(correctInputLong).contains(playerTwoMove))) {
            if ((playerOneMove.equalsIgnoreCase("r") || playerOneMove.equalsIgnoreCase("rock")) && playerTwoMove.equals("s")) {
                System.out.println("""
                        Player One picked ROCK
                        Player Two picked SCISSORS
                        Player One Wins!
                        """);
//                player1wins++;
                playGame(playerOne, playerTwo, ties, playerOneInput);
            } else if ((playerOneMove.equalsIgnoreCase("r") || playerOneMove.equalsIgnoreCase("rock")) && playerTwoMove.equals("p")) {
                System.out.println("""
                        Player One picked ROCK
                        Player Two picked PAPER
                        Player Two Wins!
                        """);
//                player2wins++;
                playGame(playerOne, playerTwo, ties, playerOneInput);
            } else if ((playerOneMove.equalsIgnoreCase("p") || playerOneMove.equalsIgnoreCase("paper")) && playerTwoMove.equals("r")) {
                System.out.println("""
                        Player One picked PAPER
                        Player Two picked ROCK
                        Player One Wins!
                        """);
//                player1wins++;
                playGame(playerOne, playerTwo, ties, playerOneInput);
            } else if ((playerOneMove.equalsIgnoreCase("p") || playerOneMove.equalsIgnoreCase("paper")) && playerTwoMove.equals("s")) {
                System.out.println("""
                        Player One picked PAPER
                        Player Two picked SCISSORS
                        Player Two Wins!
                        """);
//                player2wins++;
                playGame(playerOne, playerTwo, ties, playerOneInput);
            } else if ((playerOneMove.equalsIgnoreCase("s") || playerOneMove.equalsIgnoreCase("scissors")) && playerTwoMove.equals("p")) {
                System.out.println("""
                        Player One picked SCISSORS
                        Player Two picked PAPER
                        Player One Wins!
                        """);
//                player1wins++;
                playGame(playerOne, playerTwo, ties, playerOneInput);
            } else if ((playerOneMove.equalsIgnoreCase("s") || playerOneMove.equalsIgnoreCase("scissors")) && playerTwoMove.equals("r")) {
                System.out.println("""
                        Player One picked SCISSORS
                        Player Two picked ROCK
                        Player Two Wins!
                        """);
//                player2wins++;
                playGame(playerOne, playerTwo, ties, playerOneInput);
            } else {
                System.out.println("Draw!" + '\n');
                ties++;
                playGame(playerOne, playerTwo, ties, playerOneInput);
            }
        } else if (playerOneMove.equalsIgnoreCase("q") || playerOneMove.equalsIgnoreCase("quit")) {
            System.out.println('\n' + "***Results***" + '\n' + "Player One: " + playerOne.getPoints() + '\n' + "Player Two: " + playerTwo.getPoints() + '\n' + "Ties: " + ties + '\n');
            System.out.println("Goodbye :)");
            playerOneInput.close();
            playerTwoInput.close();
        } else {
            System.out.println("Incorrect Input" + '\n');
            playGame(playerOne, playerTwo, ties, playerOneInput);
        }
        // sends game data to a gameList
    }

    public static void showHistory() {
        System.out.println("Shows history");
        // reads data from gameList
    }

    public static void quitGame(Scanner input) {
        System.out.println("Goodbye");
        input.close();
    }

    public static void main(String[] args) {
        menu();
    }

    // ReadFile to read history.

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

import java.io.*;
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

        System.out.println("***Choose players***");
        System.out.println("1. Single player");
        System.out.println("2. Two Players");
        String userInput = playerOneInput.nextLine();

        if (userInput.equals("1")) {
            playGame(playerOne, new Computer("CPU", 0), ties, playerOneInput);
        } else {
            Player playerTwo = new Player("Player Two", 0);
            Scanner playerTwoInput = new Scanner(System.in);
            String name = "Player Two";
            System.out.println("""
                    Change Player Two's name?
                    Yes (Y)
                    No (N)""");
            String playerTwoChoice = playerTwoInput.nextLine();

            if(playerTwoChoice.equalsIgnoreCase("Yes") || playerTwoChoice.equalsIgnoreCase("Y")){
                System.out.println("Enter Player Two's name: ");
                name = playerTwoInput.nextLine();
                name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                playerTwo.setName(name);
                playGame(playerOne, playerTwo, ties, playerOneInput);
            } else if (playerTwoChoice.equalsIgnoreCase("No") || playerTwoChoice.equalsIgnoreCase("N")) {
                playGame(playerOne, playerTwo, ties, playerOneInput);
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public static void playGame(Player playerOne, Player playerTwo, int ties, Scanner playerOneInput) {
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
                    
                    Menu (M)
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
                System.out.println(playerOne.getName() + " picked ROCK" + '\n' + playerTwo.getName() + " picked SCISSORS" + '\n' + playerOne.getName() + " Wins!");
//                System.out.println("""
//                        Player One picked ROCK
//                        Player Two picked SCISSORS
//                        Player One Wins!
//                        """);
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
                System.out.println(playerOne.getName() + " picked ROCK" + '\n' + playerTwo.getName() + " picked SCISSORS" + '\n' + playerTwo.getName() + " Wins!");
//                System.out.println("""
//                        Player One picked PAPER
//                        Player Two picked ROCK
//                        Player One Wins!
//                        """);
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

        } else if ((playerOneMove.equalsIgnoreCase("m") || playerOneMove.equalsIgnoreCase("menu")) || (playerTwoMove.equalsIgnoreCase("m") || playerTwoMove.equalsIgnoreCase(
                "menu"))) {
            menu();
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
        try {
            readFile("rps-project-java/src/history.txt");
        } catch (IOException e) {
            System.out.println("Error while reading the file: " + e.getMessage());
        }
        menu();
    }

    // ReadFile to read history.
    public static void readFile(String fileName) throws IOException {
        // fileName is a String input
        // Path converts it to a machine-readable path.
        Path pathToFile = Paths.get(fileName); // return absolute path of file location

        // BufferReader helps us read the file
        BufferedReader reader;
        try {
            // Reads the file content with the given URI (meaning the local path to the file)
            reader = new BufferedReader(new FileReader(Paths.get(pathToFile.toUri()).toFile()));

            // Read one line at a time from the BufferReader
            String currentLine = reader.readLine();

            while (currentLine != null) { // check until the end of file
//                SuperHero superHero = new SuperHero(); // Create a new SuperHero object for each iteration
//                String[] data = currentLine.split(",");
//                superHero.setSuperHeroName(data[0]);
//                superHero.setRealName(data[1]);
//                superHero.setPlaceOfBirth(data[2]);

//                superHeroList.add(superHero);
                currentLine = reader.readLine(); // read the next line. This is the incrementor
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void writeFile(String name) {
        Path pathToFile = Paths.get("rps-project-java/src/history.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(pathToFile.toUri()).toFile(), true));
            writer.write(name);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

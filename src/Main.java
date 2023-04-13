import java.io.*;
import java.util.Arrays;
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
                        To play game: Play (P)
                        To view game history: History (H)
                        To exit: Quit (Q)""");

        Scanner playerOneInput = new Scanner(System.in);
        String userInput = playerOneInput.nextLine();

        if (userInput.equalsIgnoreCase("play") || userInput.equalsIgnoreCase("p")) {
            choosePlayer(playerOne, playerTwo, ties, playerOneInput);
        } else if (userInput.equalsIgnoreCase("history") || userInput.equalsIgnoreCase("h")) {
            showHistory();
        } else if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("q")) {
            quitGame(playerOne, playerTwo, ties, playerOneInput);
        } else {
            System.out.println("Incorrect Input" + '\n');
            menu();
        }
    }

    public static void choosePlayer(Player playerOne, Player playerTwo, int ties, Scanner playerOneInput) {
        Computer cpu = new Computer("CPU", 0);

        System.out.println("""

                *** Choose players ***
                1. Single player
                2. Two Players
                3. Change Player Names
                """);
        String userInput = playerOneInput.nextLine();

        switch (userInput) {
            case "1" -> playGame(playerOne, cpu, ties, playerOneInput);
            case "2" -> playGame(playerOne, playerTwo, ties, playerOneInput);
            case "3" -> changePlayerName(playerOne, playerTwo, ties, playerOneInput);
            default -> System.out.println("Invalid input");
        }
    }

    public static void changePlayerName(Player playerOne, Player playerTwo, int ties, Scanner input){
//        Scanner input = new Scanner(System.in);
        System.out.println('\n' + "*** Choose Player Name ***" + '\n' + "1. " + playerOne.getName()
                + '\n' + "2. " + playerTwo.getName()
                + '\n'
                + '\n' + "Menu (M)"
                + '\n' + "Quit (Q)"
                + '\n');

        String menuChoice = input.nextLine();
        if(menuChoice.equals("1")) {
            System.out.println('\n' + "Enter new name");
            String name = input.nextLine();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            playerOne.setName(name);
            changePlayerName(playerOne, playerTwo, ties, input);
        } else if(menuChoice.equals("2")) {
            System.out.println('\n' + "Enter new name");
            String name = input.nextLine();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            playerTwo.setName(name);
            changePlayerName(playerOne, playerTwo, ties, input);
        } else if (menuChoice.equalsIgnoreCase("m") || menuChoice.equalsIgnoreCase("menu")) {
            menu();
        } else if (menuChoice.equalsIgnoreCase("q") || menuChoice.equalsIgnoreCase("quit")) {
//            input.close();
            quitGame(playerOne, playerTwo, ties, input);
        } else {
            System.out.println("Invalid input");
            changePlayerName(playerOne, playerTwo, ties, input);
        }
    }

    public static void playGame(Player playerOne, Player playerTwo, int ties, Scanner playerOneInput) {
        String[] correctInput = {"r", "s", "p"};
        String[] correctInputLong = {"rock", "scissors", "paper"};
        Scanner playerTwoInput = new Scanner(System.in);

        System.out.println('\n' + "*** " + playerOne.getName() + " vs " + playerTwo.getName() + " ***"
                + '\n'
                + '\n' + "* Points *"
                + '\n' + playerOne.getName() + ": " + playerOne.getPoints() + " wins"
                + '\n' + playerTwo.getName() + ": " + playerTwo.getPoints() + " wins"
                + '\n' + "Ties: " + ties
                + '\n'
                + '\n' + "====="
                + '\n' + "Choose your move"
                + '\n' + "Rock (R)"
                + '\n' + "Paper (P)"
                + '\n' + "Scissors (S)"
                + '\n'
                + '\n' + "Menu (M)"
                + '\n' + "Quit (Q)"
                + '\n');

        String playerOneMove = playerOneInput.nextLine().toLowerCase();
        String playerTwoMove;

        if (playerTwo.getName().equalsIgnoreCase("cpu")) {
            playerTwoMove = Computer.computerMove();
        } else {
            playerTwoMove = playerTwoInput.nextLine().toLowerCase();
        }

        boolean playerOneChoseRock = playerOneMove.equalsIgnoreCase("r") || playerOneMove.equalsIgnoreCase("rock");
        boolean playerOneChosePaper = playerOneMove.equalsIgnoreCase("p") || playerOneMove.equalsIgnoreCase("paper");
        boolean playerOneChoseScissors = playerOneMove.equalsIgnoreCase("s") || playerOneMove.equalsIgnoreCase("scissors");

        boolean playerTwoChoseRock = playerTwoMove.equalsIgnoreCase("r") || playerTwoMove.equalsIgnoreCase("rock");
        boolean playerTwoChosePaper = playerTwoMove.equalsIgnoreCase("p") || playerTwoMove.equalsIgnoreCase("paper");
        boolean playerTwoChoseScissors = playerTwoMove.equalsIgnoreCase("s") || playerTwoMove.equalsIgnoreCase("scissors");

        boolean playerChoseMenu = (playerOneMove.equalsIgnoreCase("m") || playerOneMove.equalsIgnoreCase("menu")) ||
                (playerTwoMove.equalsIgnoreCase("m") || playerTwoMove.equalsIgnoreCase("menu"));

        boolean playerOneChoseMenu =  playerOneMove.equalsIgnoreCase("m") || playerOneMove.equalsIgnoreCase("menu");
        boolean playerTwoChoseMenu = playerTwoMove.equalsIgnoreCase("m") || playerTwoMove.equalsIgnoreCase("menu");

        boolean playerChoseQuit = (playerOneMove.equalsIgnoreCase("q") || playerOneMove.equalsIgnoreCase("quit")) ||
                (playerTwoMove.equalsIgnoreCase("q") || playerTwoMove.equalsIgnoreCase("quit"));

        boolean playerOneEnteredValidKey = Arrays.asList(correctInput).contains(playerOneMove) || Arrays.asList(correctInputLong).contains(playerOneMove);
        boolean playerTwoEnteredValidKey = Arrays.asList(correctInput).contains(playerTwoMove) || Arrays.asList(correctInputLong).contains(playerTwoMove);

        if (playerChoseMenu) {
            menu();
        } else if (playerChoseQuit) {
            quitGame(playerOne, playerTwo, ties, playerOneInput);

        } else if (playerOneEnteredValidKey && playerTwoEnteredValidKey) {
            if (playerOneChoseRock && playerTwoChoseScissors) {
                System.out.println('\n' +
                        playerOne.getName() + " picked ROCK"
                        + '\n' + playerTwo.getName() + " picked SCISSORS"
                        + '\n' + playerOne.getName() + " Wins!");
                playerOne.setPoints();
                playGame(playerOne, playerTwo, ties, playerOneInput);

            } else if (playerOneChoseRock && playerTwoChosePaper) {
                System.out.println('\n' +
                        playerOne.getName() + " picked ROCK"
                        + '\n' + playerTwo.getName() + " picked PAPER"
                        + '\n' + playerTwo.getName() + " Wins!");
                playerTwo.setPoints();
                playGame(playerOne, playerTwo, ties, playerOneInput);

            } else if (playerOneChosePaper && playerTwoChoseRock) {
                System.out.println('\n' +
                        playerOne.getName() + " picked PAPER"
                        + '\n' + playerTwo.getName() + " picked ROCK"
                        + '\n' + playerOne.getName() + " Wins!");
                playerOne.setPoints();
                playGame(playerOne, playerTwo, ties, playerOneInput);

            } else if (playerOneChosePaper && playerTwoChoseScissors) {
                System.out.println('\n' +
                        playerOne.getName() + " picked PAPER"
                        + '\n' + playerTwo.getName() + " picked SCISSORS"
                        + '\n' + playerTwo.getName() + " Wins!");
                playerTwo.setPoints();
                playGame(playerOne, playerTwo, ties, playerOneInput);

            } else if (playerOneChoseScissors && playerTwoChosePaper) {
                System.out.println('\n' +
                        playerOne.getName() + " picked SCISSORS"
                        + '\n' + playerTwo.getName() + " picked PAPER"
                        + '\n' + playerOne.getName() + " Wins!");
                playerOne.setPoints();
                playGame(playerOne, playerTwo, ties, playerOneInput);

            } else if (playerOneChoseScissors && playerTwoChoseRock) {
                System.out.println('\n' +
                        playerOne.getName() + " picked SCISSORS"
                        + '\n' + playerTwo.getName() + " picked ROCK"
                        + '\n' + playerTwo.getName() + " Wins!");
                playerTwo.setPoints();
                playGame(playerOne, playerTwo, ties, playerOneInput);

            } else {
                System.out.println("Draw!" + '\n');
                ties++;
                playGame(playerOne, playerTwo, ties, playerOneInput);
            }

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

    public static void quitGame(Player playerOne, Player playerTwo, int ties, Scanner input) {
        System.out.println('\n' +
                "*** Results ***" + '\n' +
                "Player One: " + playerOne.getPoints() + '\n' +
                "Player Two: " + playerTwo.getPoints() + '\n' +
                "Ties: " + ties + '\n');
        System.out.println("Goodbye :)");
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






    // TODO ReadFile to read history.txt
    public static void readFile(String fileName) throws IOException {
        Path pathToFile = Paths.get(fileName);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(Paths.get(pathToFile.toUri()).toFile()));
            String currentLine = reader.readLine();

            while (currentLine != null) { // check until the end of file
//                SuperHero superHero = new SuperHero(); // Create a new SuperHero object for each iteration
//                String[] data = currentLine.split(",");
//                superHero.setSuperHeroName(data[0]);
//                superHero.setRealName(data[1]);
//                superHero.setPlaceOfBirth(data[2]);

//                superHeroList.add(superHero);
                currentLine = reader.readLine();
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

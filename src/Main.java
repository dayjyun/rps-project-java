import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void menu(Player playerOne, Player playerTwo, int ties) throws IOException {
        UI.menuText();

        Scanner playerOneInput = new Scanner(System.in);
        String userInput = playerOneInput.nextLine();

        if (userInput.equalsIgnoreCase("play") || userInput.equalsIgnoreCase("p")) {
            GameAlgorithm.choosePlayer(playerOne, playerTwo, ties, playerOneInput);
        } else if (userInput.equalsIgnoreCase("history") || userInput.equalsIgnoreCase("h")) {
            try {
                readFile("src/gameHistory.txt");
            } catch (IOException e) {
                System.out.println("Error while reading the file: " + e.getMessage());
            }

        } else if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("q")) {
            quitGame(playerOne, playerTwo, ties, playerOneInput);
        } else {
            System.out.println("Incorrect Input" + '\n');
            menu(playerOne, playerTwo, ties);
        }
    }

    public static void changePlayerName(Player playerOne, Player playerTwo, int ties, Scanner input) throws IOException {
        UI.changePlayerNameText(playerOne, playerTwo);

        String menuChoice = input.nextLine();
        if (menuChoice.equals("1")) {
            System.out.println('\n' + "Enter new name");
            String name = input.nextLine();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            playerOne.setName(name);
            changePlayerName(playerOne, playerTwo, ties, input);
        } else if (menuChoice.equals("2")) {
            System.out.println('\n' + "Enter new name");
            String name = input.nextLine();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            playerTwo.setName(name);
            changePlayerName(playerOne, playerTwo, ties, input);
        } else if (menuChoice.equalsIgnoreCase("m") || menuChoice.equalsIgnoreCase("menu")) {
            menu(playerOne, playerTwo, ties);
        } else if (menuChoice.equalsIgnoreCase("q") || menuChoice.equalsIgnoreCase("quit")) {
            quitGame(playerOne, playerTwo, ties, input);
        } else if (menuChoice.equalsIgnoreCase("p") || menuChoice.equalsIgnoreCase("play")) {
            playGame(playerOne, playerTwo, ties, input);
        } else {
            System.out.println("Invalid input");
            changePlayerName(playerOne, playerTwo, ties, input);
        }
    }


    public static void playGame(Player playerOne, Player playerTwo, int ties, Scanner playerOneInput) throws IOException {
        String[] correctInput = {"r", "s", "p"};
        String[] correctInputLong = {"rock", "scissors", "paper"};
        Scanner playerTwoInput = new Scanner(System.in);

        UI.playGameText(playerOne, playerTwo, ties);

        String playerOneMove = playerOneInput.nextLine().toLowerCase();
        String playerTwoMove;

        if (playerTwo instanceof Computer) {
            playerTwoMove = ((Computer) playerTwo).computerMove();
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

        boolean playerChoseQuit = (playerOneMove.equalsIgnoreCase("q") || playerOneMove.equalsIgnoreCase("quit")) ||
                (playerTwoMove.equalsIgnoreCase("q") || playerTwoMove.equalsIgnoreCase("quit"));

        boolean playerOneEnteredValidKey = Arrays.asList(correctInput).contains(playerOneMove) || Arrays.asList(correctInputLong).contains(playerOneMove);
        boolean playerTwoEnteredValidKey = Arrays.asList(correctInput).contains(playerTwoMove) || Arrays.asList(correctInputLong).contains(playerTwoMove);

        if (playerChoseMenu) {
            menu(playerOne, playerTwo, ties);
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

    }

    public static void quitGame(Player playerOne, Player playerTwo, int ties, Scanner input) {
        UI.quitGameText(playerOne, playerTwo, ties);
        // sends game data to a gameList
        HashMap<Player, String> gameData = new HashMap<>();
        gameData.put(playerOne, playerOne.getName());
        gameData.put(playerTwo, playerTwo.getName());

        writeFile(gameData, ties);

        input.close();
    }

    public static void main(String[] args) throws IOException {

        HumanPlayer playerOne = new HumanPlayer("Player One", 0);
        HumanPlayer playerTwo = new HumanPlayer("Player Two", 0);
        int ties = 0;

        menu(playerOne, playerTwo, ties);

    }


    // TODO ReadFile to read gameHistory.txt
    // showHistory
    public static void readFile(String fileName) throws IOException {
        Path pathToFile = Paths.get(fileName);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(Paths.get(pathToFile.toUri()).toFile()));
            String currentLine = reader.readLine();

            while (currentLine.length() > 5) {
                HumanPlayer player = new HumanPlayer();
                String[] playerData = currentLine.split(" ");
                System.out.println(Arrays.toString(playerData));
                    currentLine = reader.readLine();

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeFile(Object gameData, int ties) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
//        System.out.println(formattedDate);


        Path pathToFile = Paths.get("src/gameHistory.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(pathToFile.toUri()).toFile(), true));
            writer.write(formattedDate + '\n');
            writer.write(gameData.toString() + '\n');
            writer.write("Ties: " + ties + '\n' + '\n');
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

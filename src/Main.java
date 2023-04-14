import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    /**
     * This is the first heavy driver of the game. This checks what the user would like to do once the user is in the menu. They can choose to start the game, view the game
     * history from an external file, or simply exit the game.
     * A feature that will be a common occurrence throughout the game is that most menu options can be selected with the starting letter for each word rather than typing out the
     * entire word. This creates a faster experience of the user rather than requiring to type the entire word. The entire word is still optional but not necessary.
     * @param playerOne The entire Player One object including name and points. This preserves Player One session state
     * @param playerTwo The entire Player Two object including name and points. This preserves Player Two session state
     * @param ties Integer value for the number of ties two players hold. This preserves ties session state
     * @throws IOException Input/Output Exception if logic returns false
     */
    public static void menu(Player playerOne, Player playerTwo, int ties) throws IOException {
        UI.menuText();

        Scanner playerOneInput = new Scanner(System.in);

        /*
        The trim method in the following code will remove any white space present. It provides a positive user experience since, by human nature, we may add a few spaces every
        once in a while that may not require it. Prevents invisible user error.
         */

        String userInput = playerOneInput.nextLine().trim();

        /*
        Every time the equalsIgnoreCase method is implemented, it verifies that the word itself is written without worrying about typing the exact case lettering. Once again,
        better for user experience.
         */

        if (userInput.equalsIgnoreCase("play") || userInput.equalsIgnoreCase("p")) {
            GameAlgorithm.choosePlayer(playerOne, playerTwo, ties, playerOneInput);
        } else if (userInput.equalsIgnoreCase("history") || userInput.equalsIgnoreCase("h")) {
            try {
                readFile(playerOne, playerTwo, ties);
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

    /**
     * The changePlayerName updates the name for the specific player chosen. A player can update the name for Player One, Player Two, and even CPU!
     * @param playerOne The entire Player One object including name and points. This preserves Player One session state
     * @param playerTwo The entire Player Two object including name and points. This preserves Player Two session state
     * @param ties Integer value for the number of ties two players hold. This preserves ties session state
     * @param input Passed in from the first instance the System is called.
     * @throws IOException Input/Output Exception if logic returns false
     */
    public static void changePlayerName(Player playerOne, Player playerTwo, int ties, Scanner input) throws IOException {
        UI.changePlayerNameText(playerOne, playerTwo);

        String menuChoice = input.nextLine();
        if (menuChoice.equals("1")) {
            System.out.println('\n' + "Enter new name");
            String name = input.nextLine();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase().trim();
            playerOne.setName(name);
            changePlayerName(playerOne, playerTwo, ties, input);
        } else if (menuChoice.equals("2")) {
            System.out.println('\n' + "Enter new name");
            String name = input.nextLine();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase().trim();
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

    /**
     * This is that determines the game logic that we all know and love for the Rock Paper Scissors game.
     * The method creates a new Scanner
     * @param playerOne The entire Player One object including name and points. This preserves Player One session state
     * @param playerTwo The entire Player Two object including name and points. This preserves Player Two session state
     * @param ties Integer value for the number of ties two players hold. This preserves ties session state
     * @param playerOneInput Passed in from the first instance the System is called.
     * @throws IOException Input/Output Exception if logic returns false
     */
    public static void playGame(Player playerOne, Player playerTwo, int ties, Scanner playerOneInput) throws IOException {
        String[] correctInput = {"r", "s", "p"};
        String[] correctInputLong = {"rock", "scissors", "paper"};
        Scanner playerTwoInput = new Scanner(System.in);


        UI.playGameText(playerOne, playerTwo, ties);
        System.out.println(playerOne.getName() + "'s turn");

        String playerOneMove = playerOneInput.nextLine().toLowerCase().trim();
        String playerTwoMove;

        /*
        The following lines of code check whether our player two happens to be an AI, or if it's another person. Depending on the result will determine whether the inputs for
        player two entered automatically, or if we will need a second player to add their turn input.
         */
        if (playerTwo instanceof Computer) {
            playerTwoMove = ((Computer) playerTwo).computerMove();
            System.out.println('\n' + playerTwo.getName() + "'s turn" + '\n');
        } else {
            System.out.println('\n' + playerTwo.getName() + "'s turn");
            playerTwoMove = playerTwoInput.nextLine().toLowerCase().trim();
        }

        /*
            To make code more readable, I assigned boolean values to handle the verification of what each player chooses. There are two checks for the same player simply since
            we compare if the player typed the full word or the first letter of the desired word.
         */

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


        /*
            The writeFile is called whenever a user returns to the menu. When the user returns to the menu, it introduces the possibility for the player to leave the game
            without ever recording the final results. Therefore, it acts as a data recorder and sends the information for the game to an external file that records the players
            during that session, each of their points, and their ties.
         */
        if (playerChoseMenu) {
            HashMap<Player, String> gameData = new HashMap<>();
            gameData.put(playerOne, playerOne.getName());
            gameData.put(playerTwo, playerTwo.getName());

            writeFile(gameData, ties);
            menu(playerOne, playerTwo, ties);
        } else if (playerChoseQuit) {
            quitGame(playerOne, playerTwo, ties, playerOneInput);

            /*
            Winning conditions. Depending on which player wins, their points will increment.
             */
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
            /*
            This helps check that only allowable characters are inserted into the terminal. If a character that is not allowed to be used it inserted, the game notifies the user
             that the key is not a correct value, and uses recursion to start the game again, passing in the playerOne and playerTwo objects, ties count, and Scanner input to
             persist within the session. From a player's perspective, it only appears as if the game reset to its previous display.
             */
        } else {
            System.out.println("Incorrect Input" + '\n');
            playGame(playerOne, playerTwo, ties, playerOneInput);
        }
    }

    /**
     * The quitGame game method is called a handful amount of times throughout the code. It will display the final results of the session and will send the data to our writeFile
     * as a HashMap with its key-value pairs. The writeFile method is called here mainly to capture the total values at the end of the game, rather than recording every single
     * move made by each player and then storing the results.
     * @param playerOne The entire Player One object including name and points. This preserves Player One session state
     * @param playerTwo The entire Player Two object including name and points. This preserves Player Two session state
     * @param ties Integer value for the number of ties two players hold. This preserves ties session state
     * @param input Passed in from the first instance the System is called. It can finally rest.
     */
    public static void quitGame(Player playerOne, Player playerTwo, int ties, Scanner input) {
        UI.quitGameText(playerOne, playerTwo, ties);
        HashMap<Player, String> gameData = new HashMap<>();
        gameData.put(playerOne, playerOne.getName());
        gameData.put(playerTwo, playerTwo.getName());

        writeFile(gameData, ties);

        input.close();
    }

    /**
     * This is where the game begins. It instantly creates our Players and passes them to the next method to preserve session state
     * @param args The standard main method which requires an array of args
     * @throws IOException Input/Output Exception if logic returns false
     */
    public static void main(String[] args) throws IOException {
        HumanPlayer playerOne = new HumanPlayer("Player One", 0);
        HumanPlayer playerTwo = new HumanPlayer("Player Two", 0);
        int ties = 0;

        menu(playerOne, playerTwo, ties);
    }

    /**
     * The readFile method grabs the data allocated in the file destination and returns the data back to the user when requested. The data represent the information passed in
     * through the writeFile method.
     * @param playerOne The entire Player One object including name and points. This preserves Player One session state
     * @param playerTwo The entire Player Two object including name and points. This preserves Player Two session state
     * @param ties Integer value for the number of ties two players hold. This preserves ties session state
     * @throws IOException Input/Output Exception if logic returns false
     */
    public static void readFile(Player playerOne, Player playerTwo, int ties) throws IOException {
        Path pathToFile = Paths.get("src/gameHistory.txt");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(Paths.get(pathToFile.toUri()).toFile()));
            String currentLine = reader.readLine();

            while (reader.ready()) {
//                HumanPlayer player = new HumanPlayer();
                // TODO Figure out how to loop through the ENTIRE gameHistory.txt return max 3 (latest)
                String[] playerData = currentLine.split(" ");
                System.out.println(Arrays.toString(playerData));
                    currentLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
         menu(playerOne, playerTwo, ties);
    }

    /**
     * writeFile accepts gameData loaded
     * The method then will calculate the time it was called and submit the gameData together to the file as its destination.
     * @param gameData is loaded with both Player One object and Player Two object, whether Player Two happens to be CPU or a live person, and wins, and accepts the ties
     *      * value.
     * @param ties Integer value for the number of ties two players hold. This preserves ties session state
     */
    public static void writeFile(Object gameData, int ties) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

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
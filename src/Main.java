import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private String rock = "rock";
    private String paper = "paper";
    private String scissors = "scissors";
    private char r = 'r';
    private char p = 'p';
    private char s = 's';

    public static void menu(){
//        System.out.println("Executes the game");
        Scanner input = new Scanner(System.in);
        System.out.println("""
                Welcome to Rock Paper Scissors
                    What would you like to do?
                        Play (P)
                        History (H)
                        Quit (Q)""");
        String userInput = input.nextLine();

        if (userInput.equalsIgnoreCase("play") || userInput.equalsIgnoreCase("p")) {
            // play game
        } else if (userInput.equalsIgnoreCase("history") || userInput.equalsIgnoreCase("h")) {
            // show history
        } else if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("q")) {
            // quit game
            System.out.println("Goodbye");
            input.close();
        }
    }

    public static void playGame() {
        System.out.println("Play game");
        // sends game data to a gameList
    }

    public static void history(){
        System.out.println("Shows history");
        // reads data from gameList
    }

    public static void quit(){
        System.out.println("Quits the game");
    }

    public static void main(String[] args) {
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");

        menu();
    }

    public static void writeFile(String name) {
        Path pathtoFile = Paths.get("C:\\code\\sei\\java313\\projects\\rps-project-java\\src\\output.txt");
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(pathtoFile.toUri()).toFile(), true));
            writer.write(name);
            writer.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }

//    @Override
//    public String toString() {
//        return "RETURN";
//    }
}

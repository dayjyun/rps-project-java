import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public void playGame(){
        System.out.println("Executes the game");
        Scanner input = new Scanner(System.in);
    }

    public void history(){
        System.out.println("Shows history");
    }

    public void quit(){
        System.out.println("Quits the game");
    }

    public static void main(String[] args) {
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
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
}

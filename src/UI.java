public class UI {
    public static void menuText(){
        // Main menu screen
        System.out.println("""
                *** Welcome to Rock Paper Scissors ***
                    Type to begin:
                        Play (P)
                        History (H)
                        Quit (Q)""");
    }

    // Choose to play against an AI or against another person
    public static void choosePLayerText() {
        System.out.println("""

                *** Choose players ***
                Single player (1)
                Two Players (2)
                Change Player Names (3)
                """);
    }

    // Section if option 3 is selected for choosePlayerText. The player can select which player's name to update. It calls the getName method as the name can change throughout
    // the game, therefore, we want to make sure we have the current name at all times.
    public static void changePlayerNameText(Player playerOne, Player playerTwo) {
        System.out.println('\n' +
                "*** Choose Player Name ***"
                + '\n' + "1. " + playerOne.getName()
                + '\n' + "2. " + playerTwo.getName()
                + '\n'
                + '\n' + "Menu (M)"
                + '\n' + "Quit (Q)"
                + '\n');
    }

    // This text appears during each round of the game. It displays the amount of points for the designated player along with the ties, and the options required to play the game
    // . Ultimately, the user has access to the menu or to exit the game at any time.
    public static void playGameText(Player playerOne, Player playerTwo, int ties) {
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
    }

    // The final text a user sees before the game ends. This collects the total results accumulated throughout the game for each player, including the ties count.
    public static void quitGameText(Player playerOne, Player playerTwo, int ties) {
        System.out.println('\n' +
                "*** Results ***" + '\n' +
                "Player One: " + playerOne.getPoints() + '\n' +
                "Player Two: " + playerTwo.getPoints() + '\n' +
                "Ties: " + ties + '\n' + '\n' +
                "Goodbye :)"
        );
    }
}

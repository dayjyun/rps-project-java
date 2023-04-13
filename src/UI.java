public class UI {
    public static void menuText(){
        System.out.println("""
                *** Welcome to Rock Paper Scissors ***
                    Type to begin:
                        Play (P)
                        History (H)
                        Quit (Q)""");
    }

    public static void choosePLayerText() {
        System.out.println("""

                *** Choose players ***
                Single player (1)
                Two Players (2)
                Change Player Names (3)
                """);
    }

    public static void changePlayerNameText(Player playerOne, Player playerTwo) {
        System.out.println('\n' + "*** Choose Player Name ***" + '\n' + "1. " + playerOne.getName()
                + '\n' + "2. " + playerTwo.getName()
                + '\n'
                + '\n' + "Play (P)"
                + '\n' + "Menu (M)"
                + '\n' + "Quit (Q)"
                + '\n');
    }

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

    public static void quitGameText(Player playerOne, Player playerTwo, int ties) {
        System.out.println('\n' +
                "*** Results ***" + '\n' +
                "Player One: " + playerOne.getPoints() + '\n' +
                "Player Two: " + playerTwo.getPoints() + '\n' +
                "Ties: " + ties + '\n' +
                "Goodbye :)"
        );
    }

}

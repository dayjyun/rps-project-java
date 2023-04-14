/*
The HumanPlayer class is inheriting its properties and methods from the Player class. Not much is occurring here since the heavy lifting is mainly being taken care of from the
parent class, but is still required to distinguish the type of player we have in our hands (pun intended).
 */

public class HumanPlayer extends Player{

    public HumanPlayer(String name, int points) {
        super(name, points);
    }
}

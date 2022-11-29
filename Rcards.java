import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Rcards {
    public Stack<Rcard> RDeck; //the stack of routes cards to be drawn by players
    public Stack<Rcard> Garbage; //the stack of routes cards that have already been used by players
    public ArrayList<Rcard> Floor; //5 Cards on the floor

    public Rcards() {
        RDeck = new Stack<Rcard>();
        Garbage = new Stack<Rcard>();

        //need to initialize all Rcards...
        Collections.shuffle(RDeck);
    }

    public static Rcard getTopCard(){
        return RDeck.pop();
    }
    public void reShuffleDeck(){
        Collections.shuffle(Garbage);
        RDeck = Garbage;
    }

}

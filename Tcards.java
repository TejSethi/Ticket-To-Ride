import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Tcards {

    public Stack<Tcard> TDeck; //the stack of train cards to be drawn by players
    public Stack<Tcard> Garbage; //the stack of train cards that have already been used by players
    public ArrayList<Tcard> Floor; //5 Cards on the floor

    public Tcards() {
        TDeck = new Stack<Tcard>();
        Garbage = new Stack<Tcard>();
        for (int i = 0; i < COLOR.length; i++) {
            int x = 0;
            while (x < 12) {
                TDeck.add(Tcard(COLOR[i]));
            }

        }
        int x = 0;
        while (x < 14){
            TDeck.add(Tcard(Rainbow));
        }
        Collections.shuffle(TDeck);
    }

    public static Tcard getTopCard(){
        return TDeck.pop();
    }
    public void reShuffleDeck(){
        Collections.shuffle(Garbage);
        TDeck = Garbage;
    }
    public ArrayList<Tcard> placeCard(){
        while (Floor.size() < 5){
            Floor.add(TDeck.pop());
        }
        return Floor;
    }
}


package background;

import java.util.ArrayList;

/**
 * Abstract Player class
 */

public class Player {
    /**
     * A player that can have turns, each player also has a number of trains, train cards and route cards
     */
    public int numTrains;
    public int numTCards;
    public int numRCards;
    private ArrayList<TCard> hand;
    private ArrayList<RCard> routes;

    public ArrayList<TCard> getHand() {return this.hand;}

    public ArrayList<RCard> getRoutes() {return this.routes;}
}
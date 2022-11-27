/**
 * Abstract Player class
 */

abstract class Player {
    /**
     * A player that can have turns, each player also has a number of trains, train cards and route cards
     */
    public int numTrains;
    public int numTCards;
    public int numRCards;
    private List<TCard> hand;
    private List<RCard> routes;

    public List<TCard> getHand() {return this.hand;}

    public List<RCard> getRoutes() {return this.routes;}
}
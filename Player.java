import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private Map<COLOR, Integer> playerHand; //players train cards in inventory, key is colour and value is how many of that colour player has.
    private List<Rcard> playerRoutes; //players route cards in inventory
    private Integer score; //player score
    private Integer numTrains; //number of trains player has left

    public Player(){
        numTrains = 45;
        playerhand = new HashMap<COLOR, Integer>();
        playerroutes = new ArrayList<Rcard>();
        score = 0;
        for (int i = 0; i < COLOR.length; i++){
            playerhand.put(COLOR[i], 0);
        }

    }
    public HashMap<COLOR, Integer> getHand() {return playerhand;}

    public ArrayList<Rcard> getRoutes() {return playerroutes;}

    public void calculatescore(){
        for (int i = 0; i <= playerroads.size(); i++){
            if (playerroads.get(i).isCompleted == true){
                score += playerroads.get(i).getscore();
            }else{
                score -= playerroads.get(i).getscore();
            }
        }
    }
    public void setStartingCards(){
        int x = 0;
        while (x < 4){
            Tcard curr = Tcards.getTopCard();
            playerhand.put(curr.color, playerhand.get(curr.color) + 1);
        }
        int y = 0;
        while (y < 3){
            playerroutes.add(Rcards.getTopCard());
            //need to choose which cards player wants to remove...
        }
    }
}
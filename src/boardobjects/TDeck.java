package boardobjects;

import background.*;

import java.util.ArrayList;

/**
 * The deck plus the 5 spread out cards next to it
 */

public class TDeck {

    private COLOR[] colors;
    public ArrayList<TCard> revealed;
    private ArrayList<TCard> hidden;
    private Game game;

    public void TDeck(Game game) {
        this.game = game;
        this.colors = COLOR.values();
        this.revealed = new ArrayList<TCard>();
        this.hidden = new ArrayList<TCard>();
        // TODO maybe I can replace below with a card factory
        // Now create a bunch of cards and add them to the deck:
            //Of the normal colors
        for (int i = 0; i < this.colors.length - 1; i++) {
            for (int j = 0; j < (int) game.set.numCardsEachColor; j++) {
                this.hidden.add(new TCard(this.colors[i]));
            }
        }
            //Of the Rainbow cards
        for (int i = 0; i < (int) game.set.numCardsRainbow; i++) {
            this.hidden.add(new TCard(COLOR.RAINBOW));
        }
    }
}
package background;

/**
 * A Train car card.
 */
public class TCard extends Card {
    private COLOR color;

    public TCard(String color) {
        this.color = COLOR.valueOf(color);
    }
    public TCard(COLOR color) {
        this.color = color;
    }
    public COLOR getColor() {
        return this.color;
    }
    public String getImageName() {
        return null;   //TODO get an image from another file using a hashmap of color -> card.
    }
}
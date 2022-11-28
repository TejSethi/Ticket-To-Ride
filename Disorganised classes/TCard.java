/**
 * A Train car card.
 */
public class TCard extends Card{
    private COLOR color;

    public void TCard(String color) {
        this.color = COLOR.color;
    }
    public void TCard(COLOR color) {
        this.color = COLOR;
    }
    public COLOR getColor() {
        return this.color;
    }
    public String getImage() {
        return //TODO get an image from another file using a hashmap of color -> card.
    }
}
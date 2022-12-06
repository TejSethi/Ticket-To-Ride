package boardobjects;
import background.TRAIN_COLOR;
import background.Player;
/**
 A train piece.
 */
public class trainPiece {

    public TRAIN_COLOR color;

    public boolean filled;

    public Player filledby;

    public trainPiece() {
        this.filled = false;
    }
    public void fillPiece(Player player) {
        this.filled = true;
        this.filledby = player;
    }
    public void setColor(TRAIN_COLOR color) {
        this.color =  color;
    }
}
